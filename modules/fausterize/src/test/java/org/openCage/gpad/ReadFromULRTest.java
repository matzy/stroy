package org.openCage.gpad;

import org.junit.Test;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.protocol.F1;
import org.openCage.withResource.impl.WithImpl;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: May 3, 2010
 * Time: 5:30:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReadFromULRTest {
    private static final int BUFSIZE = 500000;

    @Test
    public void testRead() throws IOException, URISyntaxException {


        foo();

        URI path =  new URL("http://www.miglayout.com/QuickStart.pdf").toURI();
//        URI path =  new URL("http://www.math.harvard.edu/computing/perl/oneliners.txt").toURI();
//        URI path =  new URL("http://farm5.static.flickr.com/4069/4571468943_934977f123_o.jpg").toURI();



        final byte[] uncompressedPad = new byte[BUFSIZE];
        int len = readChunk( path.toURL(), uncompressedPad, BUFSIZE );

        java.io.FileOutputStream fos = new java.io.FileOutputStream("/Users/stephan/tmp/t1.pdf");
        java.io.BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
        fos.write(uncompressedPad, 0, len);


        final byte[] uncompressedPad2 = new byte[BUFSIZE];
        len = readChunk2( path.toURL(), uncompressedPad2, BUFSIZE );

        java.io.FileOutputStream fos2 = new java.io.FileOutputStream("/Users/stephan/tmp/t2.pdf");
        java.io.BufferedOutputStream bout2 = new BufferedOutputStream(fos2,1024);
        bout2.write(uncompressedPad2, 0, len);


        for ( int i = 0; i < BUFSIZE; i++ ) {
            assertEquals( "i = " + i, uncompressedPad[i], uncompressedPad2[i] );
        }




    }


    private int readChunk( URL url, byte[] buf, int size) throws IOException {

//      BufferedInputStream in = new BufferedInputStream( url.openStream());
        InputStream in = url.openStream();

    int bytesRead = 0;
    int offset = 0;
    while (offset < size) {
      bytesRead = in.read(buf, offset, buf.length - offset);
      if (bytesRead == -1)
        break;
      offset += bytesRead;
    }

        in.close();
        return offset;


//        byte data[] = new byte[1024];
//
//
//        int count;
//        int top = 0;
//
//        while( (count = in.read(data,0,1024)) != -1 ) {
//
//            System.out.println( "--- " + count);
//
//            for ( int i = top; i < count; i++, top++ ) {
//                buf[top] = data[i];
//
//                if ( top + 1 == size ) {
//                    in.close();
//                    return top + 1;
//                }
//            }
//
//        }
//        in.close();
//
//        return top+1;
    }

    private int readChunk2( URL url, byte[] buf, int size) throws IOException {

//      BufferedInputStream in = new BufferedInputStream( url.openStream());
        InputStream in = url.openStream();

        byte data[] = new byte[1024];


        int count;
        int top = 0;

        while( (count = in.read(data,0,1024)) != -1 ) {

            System.out.println( "--- " + count);

            for ( int i = 0; i < count; i++, top++ ) {
                buf[top] = data[i];

                if ( top + 1 == size ) {
                    in.close();
                    return top + 1;
                }
            }

        }
        in.close();

        return top+1;
    }

    private void foo() throws IOException {
        java.io.BufferedInputStream in = new java.io.BufferedInputStream(new

                java.net.URL("http://www.miglayout.com/QuickStart.pdf").openStream());
        java.io.FileOutputStream fos = new java.io.FileOutputStream("/Users/stephan/testplans.pdf");
        java.io.BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
        byte data[] = new byte[1024];
        int count = 0;
        while( (count = in.read(data,0,1024))>=0)
        {
            //System.out.println( "--- " + count);
            bout.write(data, 0, count);
        }
        bout.close();
        in.close();
    }
}
