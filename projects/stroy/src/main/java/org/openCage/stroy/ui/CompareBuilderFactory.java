package org.openCage.stroy.ui;

import com.google.inject.Inject;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.dir.FileTreeMatchingTaskBuilder;
import org.openCage.stroy.graph.matching.strategy.combined.WatchFull;
import org.openCage.stroy.ui.difftree.NWayDiffPaneGenerator;
import org.openCage.stroy.ui.menu.PortableMenuFactory;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/4/12
 * Time: 11:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompareBuilderFactory  {

    private final FileTreeMatchingTaskBuilder taskBuilder;
    private final WatchFull watchFull;
    private final NWayDiffPaneGenerator diffPaneGen;
    private final PortableMenuFactory menuFactory;


    @Inject
    public CompareBuilderFactory( final PortableMenuFactory menuFactory,
                                  final NWayDiffPaneGenerator<FileContent> diffPaneGen,
                                  final WatchFull<FileContent> strat,
                                  final FileTreeMatchingTaskBuilder taskBuilder) {
        this.taskBuilder = taskBuilder;
        this.watchFull = strat;
        this.diffPaneGen = diffPaneGen;
        this.menuFactory = menuFactory;
    }

    public CompareBuilder get( String ... dirs ) {
        return new CompareBuilder( menuFactory, diffPaneGen, watchFull, taskBuilder, dirs );
    }
}
