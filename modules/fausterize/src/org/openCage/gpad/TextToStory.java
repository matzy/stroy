package org.openCage.gpad;

import org.jetbrains.annotations.NotNull;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.protocol.F1;
import org.openCage.withResource.impl.WithImpl;
import org.openCage.withResource.protocol.Iterators;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.zip.Deflater;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

/**
 * earlier encoding idea
 * compress the pad qith zip
 * flaw: depends on the exact bit by bit output of the zip algorithm
 *       i.e. a change of this algorithm destroys this code
 */
@Deprecated
public class TextToStory implements TextEncoder<String>{

    private byte[] pad;
//    private int          padlen;
    private int cursor = 0;
    private TextEncoder<Character> charEncoder = new FaustWordNum();

    public TextToStory( @NotNull URI path ) {


        final byte[] uncompressedPad = new byte[10000];
        new WithImpl().withInputStream( path, new F1<Integer, InputStream>() {
            public Integer call(InputStream inputStream)  {
                try {
                    return inputStream.read( uncompressedPad );
                } catch (IOException e) {
                    throw Unchecked.wrap(e);
                }
            }
        });

        pad = compress( uncompressedPad );

    }

    public String encode(String str) {

        String ret = "";

        cursor = -1;

        int i =0;

        for ( Character ch : Iterators.chars(str )) {
            System.out.println( ++i );
            ret += charEncoder.encode( chxor(ch,getNext()) ) + "\n";
        }

        return ret;
    }

    public String decode( String lines ) {
        String ret = "";
        cursor = -1;
        for ( String line : Iterators.lines(lines)) {   // TODO handle empty lines
            char ch = charEncoder.decode( line.trim());
            ret += chxor(ch, getNext());
        }
        return ret;
    }


    private byte getNext() {
        cursor++;
        if ( cursor >= pad.length ) {
            cursor = 0;
        }
        return pad[cursor];
    }

    private char chxor( char ch, byte b ) {
        char ret = (char)(((int)ch) ^ ((int)b) + 128);
//        byte chb = (byte)(ch & 0xff);
////        if ( ch > 255 ) {
////            throw new UnsupportedOperationException( ">255" );
////        }
////        if ( ch < 0 ) {
////            throw new UnsupportedOperationException( "<0" );
////        }
//
////        char ret = (char)(xor( (byte)(ch-127), b ) + 127);
//        byte reta = xor( chb, b );
//        char ret = (char)xor( chb, b );
//
        if ( ret < 0 || ret > 255 ) {
//            char gg = (char)f;
//            int f2 = ((int)gg) ^ ((int)b) + 127;
//            char gg2 = (char)f2;
            int i = 0;
        }

        return ret;
    }

    private char xor( char ch, byte b1, byte b2 ) {

        byte[] bb= new String(""+ch).getBytes();
        char cc = new String( bb ).charAt(0);

        return cc;
//        byte ch1 = (byte)(ch & 0xff);
//        byte ch2 = (byte)((ch & 0xff00) >> 8);
//
//        return (char)(ch1 | (ch2 << 8));
    }

    public static byte xor( byte a, byte b ) {
        return (byte)((int)a ^ (int)b);
    }

    private byte[] compress( byte[] input ) {

        // Create the compressor with highest level of compression
        Deflater compressor = new Deflater();
        compressor.setLevel(Deflater.BEST_COMPRESSION);

        // Give the compressor the data to compress
        compressor.setInput(input);
        compressor.finish();

        // Create an expandable byte array to hold the compressed data.
        // You cannot use an array that's the same size as the orginal because
        // there is no guarantee that the compressed data will be smaller than
        // the uncompressed data.
        ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);

        // Compress the data
        byte[] buf = new byte[1024];
        while (!compressor.finished()) {
            int count = compressor.deflate(buf);
            bos.write(buf, 0, count);
        }
        try {
            bos.close();
        } catch (IOException e) {
        }

        // Get the compressed data
        return bos.toByteArray();
    }

}
