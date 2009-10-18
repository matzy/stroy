package org.openCage.gpad;

import org.jetbrains.annotations.NotNull;
import org.openCage.lang.protocol.FE1;
import org.openCage.withResource.impl.WithImpl;
import org.openCage.withResource.protocol.Iterators;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.zip.Deflater;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 18, 2009
 * Time: 10:16:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class TextToStory implements TextEncoder<String>{

    private byte[] pad;
//    private int          padlen;
    private int cursor = 0;
    private TextEncoder<Character> charEncoder = new FaustWordNum();

    public TextToStory( @NotNull URI path ) {

        // TODO zip

        final byte[] uncompressedPad = new byte[10000];
        new WithImpl().withInputStream( path, new FE1<Integer, InputStream>() {
            @Override
            public Integer call(InputStream inputStream) throws Exception {
                return inputStream.read( uncompressedPad );
            }
        });

        pad = compress( uncompressedPad );

//        for ( char ch = 0; ch < 256; ch++ ) {
//            for ( byte b = -127; b < 127; b++ ) {
//                System.out.println("" + ch + " " + b);
//                if ( chxor( chxor(ch,b),b) != ch ) {
//                    int i = 0;
//                }
//            }
//        }

//        for ( char ch = 0; ch < 256; ch++ ) {
//            for ( byte b = -127; b < 127; b++ ) {
//                for ( byte c = -127; c < 127; c++ ) {
//                    System.out.println("" + ch + " " + b + " " + c);
//                    char u = xor( ch, b, c);
//                    char v = xor(u, b, c );
//                    if (xor( xor( ch, b, c), b, c) != ch ) {
//                        int i = 0;
//                        xor(ch,b,c);
//                    }
//                }
//            }
//
//        }

        
    }

    @Override
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

    @Override
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
