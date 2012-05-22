package org.openCage.io;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;


public class IOUtilsTest {

    @Test
    public void testCloseQuietlyNull() {
        IOUtils.closeQuietly( (InputStream)null );
        IOUtils.closeQuietly( (FileWriter) null);
        IOUtils.closeQuietly( (FileOutputStream) null);
    }

    @Test
    public void testCloseQuietly() {
        InputStream is = getClass().getResourceAsStream( "any.txt" );
        assertNotNull( is );

        IOUtils.closeQuietly( is );

        try {
            is.read();
            fail( "should be closed already" );
        } catch (IOException e) {
        }

    }

}
