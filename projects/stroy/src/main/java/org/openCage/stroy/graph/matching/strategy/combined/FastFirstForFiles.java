package org.openCage.stroy.graph.matching.strategy.combined;

import com.google.inject.Inject;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.matching.TreeLeafNodeFuzzyLeafDistance;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/8/12
 * Time: 8:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class FastFirstForFiles extends FastFirst<FileContent>{
    @Inject
    public FastFirstForFiles(final TreeLeafNodeFuzzyLeafDistance<FileContent> fileContentTreeLeafNodeFuzzyLeafDistance) {
        super(fileContentTreeLeafNodeFuzzyLeafDistance);
    }
}
