package org.openCage.stroy.algo.tree.zip;

import org.openCage.stroy.tree.zip.ZipFielFactory;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHashGen;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
import org.openCage.util.lang.Lazy;
import org.openCage.util.lang.F0;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.InputStream;
import java.io.IOException;

import com.google.inject.Inject;

public class ZipFielFactoryImpl implements ZipFielFactory  {

    private final FuzzyHashGen<InputStream> fuzzyHashGen;

    @Inject
    public ZipFielFactoryImpl( final FuzzyHashGen<InputStream> fuzzyHashGen ) {
        this.fuzzyHashGen = fuzzyHashGen;
    }


    public ZipFiel create( final String path, final ZipEntry entry, final String type, FuzzyHashGen<InputStream> fg ) {
        return null;
//        return new ZipFiel( path, entry, type,
//                new Lazy<FuzzyHash>( new F0<FuzzyHash>() {
//                    public FuzzyHash call() {
//
//                        ZipFile zf = null;
//                        try {
//                            zf = new ZipFile( path );
//                            InputStream is = zf.getInputStream( entry );
//                            return fuzzyHashGen.create( is, type );
//                        } catch ( IOException e ) {
//                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                            return  null;
//                        }
//                    }
//                } ));
    }
}
