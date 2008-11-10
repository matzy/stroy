package org.openCage.stroy.tree.zip;

import org.openCage.stroy.tree.NoedGenerator;
import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.Fiel;
import org.openCage.stroy.tree.FielStream;
import org.openCage.util.io.FileUtils;

import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Enumeration;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;

public class ZipNoedGenerator implements NoedGenerator {

    public Noed build( String path ) {

        Noed root = null;
        ZipEntry tt = null;

        Map<String, Noed> noeds = new HashMap<String, Noed>();

        ZipFile zf = null;
        try {
            zf = new ZipFile( path );
        } catch( IOException e ) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        for ( Enumeration<? extends ZipEntry> e = zf.entries(); e.hasMoreElements(); )
        {
            ZipEntry entry = e.nextElement();
            String   elemPath  = FileUtils.normalizePath( entry.getName() );
            System.out.println( entry.getName() );

            String   parentPath = new File( elemPath ).getParent();
            String   name = new File( elemPath ).getName();

            Noed noed = null;

            if ( parentPath.equals( "/" )) {
                if ( root != null ) {
                    throw new Error( "starnge zip" );
                }
                root = NoedImpl.makeDirNoed( name );
                noed = root;
            } else {
                Noed parent = noeds.get( parentPath );

                if ( parent == null ) {
                    throw new Error( "strange order zip not supported yet" );
                }

                if ( entry.isDirectory() ) {
                    noed = NoedImpl.makeDirNoed( name);
                } else {

                    tt = entry;
                    Fiel fiel = null;
                    fiel = new ZipFiel( path, entry );

                    noed = NoedImpl.makeLeafNoed( name, fiel );
                }

                parent.addChild( noed );

            }

            noeds.put( elemPath, noed );


            // to get at the file
//            try {
//                zf.getInputStream( entry );
//            } catch( IOException e1 ) {
//                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
        }


        try {
            zf.close();
        } catch( IOException e ) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


//        try {
//            ZipFile zf2 = new ZipFile( path );
//            InputStream is = zf2.getInputStream( tt );
//
//            byte[] buf = new byte[1024];
//
//            is.read( buf);
//
//            String str = new String( buf );
//            int i= 0;
//        } catch( IOException e ) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }


        return root;
    }
}
