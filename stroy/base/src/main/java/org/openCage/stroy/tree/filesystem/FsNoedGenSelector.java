package org.openCage.stroy.tree.filesystem;

import org.openCage.stroy.tree.NoedGenSelector;
import org.openCage.stroy.tree.NoedGen;
import org.openCage.lang.Maybe;

import java.io.File;

public class FsNoedGenSelector implements NoedGenSelector {
    public Maybe<? extends NoedGen> get( String path, boolean single ) {

        if ( single ) {
            return Maybe.no();
        }

        File file = new File( path );

        if ( !file.isDirectory() ) {
            return Maybe.no();
        }

        return Maybe.yes( new FSNoedGen() );
    }
}
