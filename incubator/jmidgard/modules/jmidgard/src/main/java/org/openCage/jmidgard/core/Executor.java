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

    private static final Log log = Log.getLogger( Executor.class.getName() );

    public void execute( Task task ) {
        List<Task> todo = Tasks.getLinearPrereqs( task );

        for ( Task now : todo ) {

            log.one.info( "[" + now.getName() + "]" );
            if ( now.needsToRun() ) {
                log.two.info( "running ... " );
                if ( !now.run()) {
                    log.two.severe( "failed" );
                    return;
                }
            }
        }
    }
}
