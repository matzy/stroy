package org.openCage.stroy.tree.singleFile;

import org.openCage.stroy.tree.NoedGenerator;
import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.filesystem.FSFiel;
import org.openCage.stroy.tree.zip.NoedImpl;

import java.io.File;

public class SingleFileGenerator implements NoedGenerator {
    public Noed build( String path ) {

        File file = new File( path );

        Noed root = NoedImpl.makeDirNoed( file.getParentFile().getName() );
        root.addChild( NoedImpl.makeLeafNoed( file.getName(), new FSFiel()));

        return root;
    }
}
