package org.openCage.stroy.tree.filesystem;

import org.openCage.stroy.tree.NoedGenerator;
import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.zip.NoedImpl;

import java.io.File;

public class FSNoedGenerator implements NoedGenerator {
    public Noed build( String path ) {

        File rootFile = new File( path );

        String rootPrefix = null;

        return build( rootFile, rootPrefix );
    }


    private Noed build( File file, String prefix ) {
        if ( !file.isDirectory()) {
            return NoedImpl.makeLeafNoed( file.getName(), new FSFiel());
        }

        Noed parent = NoedImpl.makeDirNoed( file.getName() );

        for ( File child : file.listFiles() ) {
            parent.addChild( build( child, prefix ));
        }

        return parent;
    }
}
