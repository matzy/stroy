package org.openCage.comphy;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/23/12
 * Time: 8:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class KeyTest {

    @Test
    public void testGood() {
        new Key( "thisIsATest");
        new Key( "thisIs-also-A_Test");
        new Key( "and.more");
    }

    @Test( expected = IllegalArgumentException.class )
    public void testBad() {
        new Key("<");
    }
}
