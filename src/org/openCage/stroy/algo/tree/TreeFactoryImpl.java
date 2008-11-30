package org.openCage.stroy.algo.tree;

import com.google.inject.Inject;
import org.openCage.stroy.algo.tree.singleFile.SingleFile;
import org.openCage.stroy.algo.tree.zip.ZipArchive;
import org.openCage.stroy.algo.tree.filesystem.FileSystem;
import org.openCage.util.io.FileUtils;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 30.11.2008
 * Time: 20:45:36
 * To change this template use File | Settings | File Templates.
 */
public class TreeFactoryImpl implements TreeFactory{

    final NoedGenerator singleNoed;
    final NoedGenerator zipNoed;
    final NoedGenerator fsNoed;

    @Inject
    public TreeFactoryImpl(
            @FileSystem final NoedGenerator fsNoed,
            @SingleFile final NoedGenerator singleNoed,
            @ZipArchive final NoedGenerator zipNoed ) {

        this.singleNoed = singleNoed;
        this.zipNoed = zipNoed;
        this.fsNoed = fsNoed;
    }


    public NoedGenerator create( String path, boolean single ) {

        if ( single ) {
            return singleNoed;
        }

        String ext = FileUtils.getExtension( path );

        if ( ext.equals( "zip" )) {
            return zipNoed;
        }

        if ( ext.equals( "" )) {
            return fsNoed;
        }

        throw new IllegalArgumentException( "unknown type:" + path );
    }
}
