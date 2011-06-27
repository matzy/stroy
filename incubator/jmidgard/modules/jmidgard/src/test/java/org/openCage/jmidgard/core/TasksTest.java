package org.openCage.jmidgard.core;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 22.06.11
 * Time: 13:48
 * To change this template use File | Settings | File Templates.
 */
public class TasksTest {


    @Test
    public void testTransitiveTasks() {
        Task a = new SimpleTask( "A" );
        Task b = new SimpleTask( "B", a );
        Task c = new SimpleTask( "C", b, a );
        Task d = new SimpleTask( "D", c );

        List<Task> pres = Tasks.getLinearPrereqs( d );

        assertEquals( 4, pres.size()  );

        new Executor().execute( d );
    }

}
