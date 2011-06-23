package org.openCage.jmidgard.core;

import com.sun.org.apache.xpath.internal.FoundIndex;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 22.06.11
 * Time: 11:12
 * To change this template use File | Settings | File Templates.
 */
public class Executor {

    public void execute( Task task ) {
        List<Task> todo = Tasks.getLinearPrereqs( task );

        for ( Task now : todo ) {
            if ( now.needsToRun() ) {
                now.run();
            }
        }
    }
}
