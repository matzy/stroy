package org.openCage.stroy.algo.matching;

import org.openCage.stroy.algo.tree.Noed;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 01.12.2008
 * Time: 07:26:54
 * To change this template use File | Settings | File Templates.
 */
public interface TreeTaskFactory {

    public TreeTask create( Noed left, Noed right );
}
