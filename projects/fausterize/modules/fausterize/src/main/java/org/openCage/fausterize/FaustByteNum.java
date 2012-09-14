package org.openCage.fausterize;

import org.jetbrains.annotations.NotNull;
import org.openCage.huffman.BitField;
import org.openCage.huffman.BitList;
import org.openCage.huffman.Huffman;
import org.openCage.huffman.HuffmanN;
import org.openCage.io.Resource;
import org.openCage.kleinod.errors.Unchecked;
import org.openCage.lang.functions.F1;
import org.openCage.lang.functions.FE1;
import org.openCage.lang.iterators.Iterators;
import org.openCage.io.With;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import static org.openCage.lang.MathOps.xor;


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

/**
 * Encode bytes into lines of the poem faust
 */
public class FaustByteNum implements TextEncoderIdx<Byte,String> {

    private static final int PAD_SIZE = 10000;
    private static final int UNSIGNED_BYTE_MAX = 256;
    private static final Logger LOG = Logger.getLogger( FaustByteNum.class.getName());

    private List<String>[]          num2line;
    private Set<String>             knownLines = new HashSet<String>();
    private Map<String,Integer>     line2num = new HashMap<String,Integer>();
    private BitField pad;

    @Override
    public void setPad( @NotNull URI path ) {

        final byte[] uncompressedPad = new byte[PAD_SIZE];
        new With().withInputStream( path, new F1<Integer, InputStream>() {
            public Integer call(InputStream in) {
                try {
                    int bytesRead = 0;
                    int offset = 0;
                    while (offset < PAD_SIZE) {
                        bytesRead = in.read(uncompressedPad, offset, uncompressedPad.length - offset);
                        if (bytesRead == -1) {
                            break;
                        }
                        offset += bytesRead;
                    }

                    return offset;


                } catch (IOException e) {
                    throw Unchecked.wrap(e);
                }
            }
        });

        HuffmanN hn = new HuffmanN(BitList.valueOf( uncompressedPad ));

        pad  = hn.encode( hn.getCode( 8 ));
    }

    @Override public boolean isSet() {
        return pad != null;
    }

    public FaustByteNum() {

        // read faust.txt line by line and put them in a array size 256 (max unsigned byte)
        // i.e. several entries per char

        num2line = new List[UNSIGNED_BYTE_MAX];
        for ( int i = 0; i < UNSIGNED_BYTE_MAX; ++i ) {
            num2line[i] = new ArrayList<String>();
        }

        Resource.tryWith( new FE1<Object, Resource>() {
            @Override
            public Object call(Resource resource) throws Exception {
                BufferedReader reader = resource.add(
                    new BufferedReader( new InputStreamReader( getClass().getResource( "faust.txt" ).openStream()) ));

                int idx = 0;
                for ( String str : Iterators.lines( reader ) ) {
                    if ( str.contains(":") || str.contains("(") || str.trim().equals( "" )) {
                        continue;
                    }

                    if ( knownLines.contains(str )) {
                        continue;
                    }

                    ++idx;
                    knownLines.add( str );
                    num2line[idx % UNSIGNED_BYTE_MAX].add(str);
                    line2num.put( str, idx % UNSIGNED_BYTE_MAX);
                }
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });


//        } catch (IOException e) {
//            LOG.severe( "can't read internal resource" );
//            throw new Unchecked(e);
//        } finally {
//            IOUtils.closeQuietly( reader );
//        }
    }

    @Override public String encode( Byte ch, int idx ) {
        if ( pad == null ) {
            throw new IllegalStateException("no pad yet");
        }

        List<String> posi =  num2line[xor(ch, getByte(idx)) - Byte.MIN_VALUE];
        return posi.get(((int)(Math.random() * posi.size())));
    }

    @Override public Byte decode( String line, int idx ) {
        if ( pad == null ) {
            throw new IllegalStateException("no pad yet");
        }

        try {
        return xor((byte)((line2num.get(line)) + Byte.MIN_VALUE), getByte(idx));
        } catch ( NullPointerException exp ) {
            byte by = getByte(idx);
            xor((byte)((line2num.get(line)) + Byte.MIN_VALUE), by );
            return  null;
        }
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
