package org.openCage.lindwurm.file;

import org.openCage.kleinod.io.FileUtils;
import org.openCage.kleinod.type.Null;
import org.openCage.lindwurm.Ignore;
import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
import org.openCage.lindwurm.LindwurmBuilder;
import org.openCage.lindwurm.SimpleTreeNode;
import org.openCage.lindwurm.content.ReducedContent;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/21/12
 * Time: 6:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileLindwurmBuilder implements LindwurmBuilder {

    @Override
    public LindenNode build(Ignore ignore, File one) {


        if ( ignore.match( "/" + one.getName() )) {
            throw new Error( "top level dir is in ignore pattern" );
        }

        return add( ignore, "", one );

    }


    private LindenNode add( Ignore ignore, String pathPart, File file ) {

        pathPart += "/" + file.getName();

        if ( ignore.match( pathPart )) { // || ignore.ignoreByPath( file.getPath() )) {
            return null;
        }

        if ( file.isDirectory() ) {
            return addDir( ignore, pathPart, file );
        } else {
            return addLeaf( file);
        }
    }

    private LindenDirNode addDir( Ignore ignore, String pathPart, File file ) {

        LindenDirNode dir = SimpleTreeNode.makeDir(new ReducedContent(file.getName(), FileUtils.getExtension(file)));

        for ( File child : file.listFiles() ) {

            LindenNode kid = add( ignore, pathPart, child);

            if ( Null.isNot(kid)) {
                dir.addChild( kid );
            }
        }

        return dir;
    }

    private LindenNode addLeaf( File file) {

        assert( !file.isDirectory() );

        return new SimpleTreeNode( new FileContentImpl( file ));
    }

}
