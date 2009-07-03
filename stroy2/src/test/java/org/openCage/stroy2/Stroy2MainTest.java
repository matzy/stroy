package org.openCage.stroy2;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jun 30, 2009
 * Time: 9:56:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stroy2MainTest {

    @Test
    public void testMainSimple() {
        Main.main( new String[]{"/Users/stephan", "/Users/stephan/Documents"});


        for ( int i = 0; i < 10; ++i ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            
        }
    }
}
