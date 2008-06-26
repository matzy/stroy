package org.openCage.util.swing;

import junit.framework.TestCase;

public class CollectingInvokerTest extends TestCase  {

    public void testSimple() {

        CollectingInvoker collecting = new CollectingInvoker( new Runnable() {
            public void run() {
                System.out.println( "hi " + System.currentTimeMillis() );
            }
        } );


        collecting.invokeFull();
        collecting.invokeFull();
        collecting.invokeFull();
        collecting.invokeFull();
    }


}
