package org.openCage.gpad;


import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static junit.framework.Assert.assertEquals;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

@Ignore
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

//            System.out.println( "--- " + count);

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
