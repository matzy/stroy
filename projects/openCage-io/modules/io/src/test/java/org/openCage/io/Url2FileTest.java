package org.openCage.io;

import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.Assert.assertEquals;

public class Url2FileTest {


    // TODO, fails on osx
//    @Test
//    public void testUrl2File() throws MalformedURLException {
//        File file = IOUtils.url2File( new URL( "file://C:\\Program Files"));
//
//        assertEquals( "C:\\Program Files", file.getAbsolutePath() );
//    }

    @Test( expected = IllegalArgumentException.class )
    public void testNoFile() throws MalformedURLException {
        URL noFile = new URL( "http://slashdot.org");

        IOUtils.url2File( noFile );
    }


}
