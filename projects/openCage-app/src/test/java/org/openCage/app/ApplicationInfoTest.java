package org.openCage.app;

import org.junit.Test;
import org.openCage.comphy.XMLtoReadable;

import java.io.InputStream;
import java.net.URL;

import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/30/12
 * Time: 8:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationInfoTest {

    @Test
    public void testLoad() {
        XMLtoReadable xmLtoReadable = new XMLtoReadable();

//        InputStream is = getClass().getResourceAsStream( "/org/openCage/app/sillyapp.appinfo");
        URL is = getClass().getResource("/org/openCage/app/sillyapp.appinfo");

        assertNotNull( is );
    }
}
