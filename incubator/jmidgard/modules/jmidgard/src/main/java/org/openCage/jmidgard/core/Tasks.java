package org.openCage.jmidgard.core;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 22.06.11
 * Time: 14:09
 * To change this template use File | Settings | File Templates.
 */
public class Tasks {

    public static List<Task> getLinearPrereqs( Task task ) {
        return linearise( getTransitivePrereqs( task ));
    }

    private static Collection<Task> getTransitivePrereqs( Task task ) {
        Set<Task> done = new HashSet<Task>();
        Set<Task> todo = new HashSet<Task>();

        todo.add( task );

        while ( todo.size() > 0 ) {
            Task now = todo.iterator().next();
            done.add( now );
            todo.remove(now);

            for ( Task pre : now.getPrerequisites() ) {
                if ( !done.contains(pre)) {
                    todo.add( pre ) ;
                }
            }
        }

        return done;
    }

    private static List<Task> linearise( Collection<Task> tasks ) {
        List<Task> ret = new ArrayList<Task>();

        while( tasks.size() > 0 ) {
            Task found = null;
            for ( Task task : tasks ) {
                if ( hasPreqs( task, ret )) {
                    found = task;
                    break;
                }
            }

            if ( found == null ) {
                throw new IllegalStateException("task graph is disconnected");
            }

            ret.add( found );
            tasks.remove( found );

        }


        return ret;
    }

    private static boolean hasPreqs(Task task, List<Task> prereqs ) {

        for ( Task pre : task.getPrerequisites() ) {
            if ( !prereqs.contains( pre )) {
                return false;
            }
        }

        return true;
    }

}
