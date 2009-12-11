package org.openCage.gpad;

import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 8, 2009
 * Time: 3:28:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class FaustStringTest {

    @Test
    public void testRoundTrip() throws URISyntaxException {
        FaustString ds = new FaustString();
        ds.setPad( getClass().getResource("FaustStringTest.class").toURI());

        String txt = "\n"; //hi\nwooo";

        String enc = ds.encode( txt, 0 );
        String dec = ds.decode( enc, 0 );

        assertEquals( txt, dec );
    }
}
