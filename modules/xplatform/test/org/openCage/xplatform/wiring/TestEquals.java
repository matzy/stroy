/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.xplatform.wiring;

import org.junit.Test;
import org.openCage.xplatform.wriring.XPlatformWiring;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author stephan
 */
public class TestEquals {

    @Test
    public void testEquals() {
        // Guice modules need to override equals
        // otherwise you get duplicate binding erros when 2 modules install it and then are both installed
        //     N
        //  A    B
        //     this
        assertEquals( new XPlatformWiring(), new XPlatformWiring());
    }
}
