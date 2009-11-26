package org.openCage.gpad;

import org.jetbrains.annotations.NotNull;
import org.openCage.huffman.DynamicBitArray;
import org.openCage.huffman.Huffman;
import org.openCage.lang.protocol.FE1;
import org.openCage.withResource.impl.WithImpl;
import org.openCage.withResource.protocol.FileLineIterable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.*;
import java.util.zip.Deflater;


/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 17, 2009
 * Time: 9:03:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class FaustByteNum implements TextEncoderIdx<Byte> {

    List<String>[] num2line;
    Set<String> knownLines = new HashSet();
    Map<String,Integer> line2num = new HashMap<String,Integer>();
    private DynamicBitArray pad;



    public void setPad( @NotNull URI path ) {
        final byte[] uncompressedPad = new byte[10000];
        new WithImpl().withInputStream( path, new FE1<Integer, InputStream>() {
            public Integer call(InputStream inputStream) throws Exception {
                return inputStream.read( uncompressedPad );
            }
        });

        pad = new Huffman().encode( uncompressedPad );// compress( uncompressedPad );
        int i = 0;
    }

    public boolean isSet() {
        return pad != null;
    }

    public FaustByteNum() {
        num2line = new List[256];
        for ( int i = 0; i < 256; ++i ) {
            num2line[i] = new ArrayList<String>();
        }

        File ff = new File( getClass().getResource( "faust.txt" ).getPath());

        int idx = 0;
        FileLineIterable it = new WithImpl().getLineIteratorCloseInFinal( ff );
        try {
            for ( String str : it ) {
                if ( str.contains(":") || str.contains("(") || str.trim().equals( "" )) {
                    continue;
                }

                if ( knownLines.contains(str )) {
                    continue;
                }

                ++idx;
                knownLines.add( str );
                num2line[idx % 256].add(str);
                line2num.put( str, idx % 256 );
            }
        } finally {
            it.close();
        }
    }

    public String encode( Byte ch, int idx ) {
        if ( pad == null ) {
            throw new IllegalStateException("no pad yet");
        }

        List<String> posi =  num2line[xor(ch, getByte(idx)) - Byte.MIN_VALUE];
        return posi.get(((int)(Math.random() * posi.size())));
    }

    public Byte decode( String line, int idx ) {
        if ( pad == null ) {
            throw new IllegalStateException("no pad yet");
        }

        return xor((byte)((line2num.get( line ).intValue()) + Byte.MIN_VALUE), getByte(idx));
    }

    public static byte xor( byte a, byte b ) {
        return (byte)((int)a ^ (int)b);
    }



//    private byte[] compress( byte[] input ) {
//
//        // Create the compressor with highest level of compression
//        Deflater compressor = new Deflater();
//        compressor.setLevel(Deflater.BEST_COMPRESSION);
//
//        // Give the compressor the data to compress
//        compressor.setInput(input);
//        compressor.finish();
//
//        // Create an expandable byte array to hold the compressed data.
//        // You cannot use an array that's the same size as the orginal because
//        // there is no guarantee that the compressed data will be smaller than
//        // the uncompressed data.
//        ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);
//
//        // Compress the data
//        byte[] buf = new byte[1024];
//        while (!compressor.finished()) {
//            int count = compressor.deflate(buf);
//            bos.write(buf, 0, count);
//        }
//        try {
//            bos.close();
//        } catch (IOException e) {
//        }
//
//        // Get the compressed data
//        return bos.toByteArray();
//    }


    private byte getByte( int idx) {
        return pad.getByteModulo( idx );
    }




}
