package org.openCage.lindwurm.single;

import org.openCage.lindwurm.*;
import org.openCage.lindwurm.file.FileContentImpl;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/24/12
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class SingleLindwurmBuilder implements LindwurmBuilder {
    @Override
    public LindenNode build(Ignore ignore, File root) {
        LindenDirNode node =  SimpleTreeNode.makeDir( "root" );
        node.addChild( new SimpleTreeNode( new FileContentImpl(root)));
        return node;
    }
}
