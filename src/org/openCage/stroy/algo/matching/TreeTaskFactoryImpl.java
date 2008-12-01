package org.openCage.stroy.algo.matching;

import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.matching.TreeTaskImpl;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 01.12.2008
 * Time: 07:29:51
 * To change this template use File | Settings | File Templates.
 */
public class TreeTaskFactoryImpl implements TreeTaskFactory {
    public TreeTask create( Noed left, Noed right ) {
        return new TreeTaskImpl( left, right );
    }
}
