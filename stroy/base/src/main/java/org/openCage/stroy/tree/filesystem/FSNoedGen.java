package org.openCage.stroy.tree.filesystem;

import org.openCage.stroy.tree.NoedGen;
import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.NoedImpl;
import org.openCage.stroy.tree.StdFiel;
import org.openCage.stroy.tree.filter.Ignore;
import org.openCage.stroy.tree.filter.NullIgnore;
import org.openCage.lang.F0;
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
            return NoedImpl.makeLeafNoed(
                    file.getName(),
                    new StdFiel( new F0<InputStream>(){
                        public InputStream c() {
                            try {
                                return new FileInputStream( file );
                            } catch ( FileNotFoundException e ) {
                                // TODO
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                return null;
                            }
                        }
                    },
                    null ));
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
