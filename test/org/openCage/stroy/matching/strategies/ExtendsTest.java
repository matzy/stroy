package org.openCage.stroy.matching.strategies;

import junit.framework.TestCase;
import org.openCage.stroy.matching.TreeTask;
import org.openCage.stroy.matching.Task;
import org.openCage.stroy.tree.Noed;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 15.11.2008
 * Time: 17:51:18
 * To change this template use File | Settings | File Templates.
 */
public class ExtendsTest extends TestCase {

    public class TS implements TreeStrategy {

        public void match( TreeTask task ) {
            System.out.println( "tree" );
        }

        public void match( Task<Noed> task ) {
            System.out.println( "base" );
        }
    }


    public void tt() {
        
    }
}
