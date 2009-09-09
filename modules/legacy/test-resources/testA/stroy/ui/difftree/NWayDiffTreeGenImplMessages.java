package org.openCage.stroy.ui.difftree;

import org.openCage.stroy.ui.difftree.NWayDiffPane;
import org.openCage.stroy.ui.difftree.ShowChangeTreeCellRenderer;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import com.google.inject.Inject;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Nov 14, 2007
 * Time: 8:36:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class NWayDiffTreeGenImplMessages implements NWayDiffPaneGenerator {

    private final ShowChangeTreeCellRenderer showChangeTreeCellRenderer;

    @Inject
    public NWayDiffTreeGenImplMessages( final ShowChangeTreeCellRenderer showChangeTreeCellRenderer ) {
        this.showChangeTreeCellRenderer  = showChangeTreeCellRenderer;
    }


    public NWayDiffPane getDiffPane(  final List<TreeMatchingTask<FileContent>> tasks,
                                      final List<DefaultMutableTreeNode>        roots ) {
        return new NWayDiffPaneMessages( tasks, roots, showChangeTreeCellRenderer );
    }
}
