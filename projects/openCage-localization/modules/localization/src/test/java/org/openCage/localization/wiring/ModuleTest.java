/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.localization.wiring;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author stephan
 */
public class ModuleTest {

    @Test
    public void testEquals() {
        // Guice modules need to override equals
        // otherwise you get duplicate binding erros when 2 modules install it and then are both installed
        //     N
        //  A    B
        //     this
        assertEquals( new LocalizeWiring(), new LocalizeWiring());
    }
}
