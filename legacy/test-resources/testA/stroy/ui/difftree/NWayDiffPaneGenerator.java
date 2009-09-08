package org.openCage.stroy.ui.difftree;

import org.openCage.stroy.ui.difftree.NWayDiffPane;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.matching.TreeMatchingTask;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Nov 13, 2007
 * Time: 1:46:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface NWayDiffPaneGenerator {

    public NWayDiffPane getDiffPane( final List<TreeMatchingTask<FileContent>> tasks,
                                     final List<DefaultMutableTreeNode>        roots );
}
