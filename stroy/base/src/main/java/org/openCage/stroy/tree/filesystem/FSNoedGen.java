package org.openCage.stroy.tree.filesystem;

import org.openCage.stroy.tree.NoedGen;
import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.NoedImpl;
import org.openCage.stroy.tree.StdFiel;
import org.openCage.stroy.tree.filter.Ignore;
import org.openCage.stroy.tree.filter.NullIgnore;
import org.openCage.stroy.hash.FuzzyHash;
import org.openCage.lang.F0;
import org.openCage.lang.E0;
import org.openCage.lang.E1;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FSNoedGen implements NoedGen {
    private Ignore ignore = new NullIgnore();

    public Noed build( @NotNull String path ) {

        return build( ignore, new File( path ) );
    }

    public void setIgnore( Ignore ignore ) {
        this.ignore = ignore;
    }


    private Noed build( Ignore ignore, final File file ) {

        if ( ignore.match( file.getPath() )) {
            return null;
        }

        if ( !file.isDirectory()) {

            E0<InputStream> is = new E0<InputStream>() {
                public InputStream c() throws Exception {
                    return new FileInputStream( file );
                }
            };

            E1<String, InputStream> hashGen = new E1<String, InputStream>() {
                public String c(InputStream inputStream) throws Exception {
                    return null;
                }
            };

            E1<FuzzyHash, InputStream> fuzzyHashGen = new E1<FuzzyHash, InputStream>() {
                public FuzzyHash c(InputStream inputStream) throws Exception {
                    return null;
                }
            };

            StdFiel fiel = new StdFiel( is, null, 42, hashGen, fuzzyHashGen );


            return NoedImpl.makeLeafNoed(
                    file.getName(),
                    fiel );
        }

        Noed parent = NoedImpl.makeDirNoed( file.getName() );

        for ( File child : file.listFiles() ) {
            Noed noed = build( ignore, child );
            if ( noed != null ) {
                parent.addChild( noed );
            }
        }

        return parent;
    }
}
