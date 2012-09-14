package org.openCage.fausterize;

import eu.medsea.util.EncodingGuesser;
import org.openCage.fausterize.FaustByteNum;
import org.openCage.fausterize.TextEncoderIdx;
import org.openCage.lang.iterators.ArrayCount;
import org.openCage.lang.iterators.ByteCount;
import org.openCage.kleinod.errors.Unchecked;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;

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
 * Implement the fausterize specific text encryption
 * i.e. text <-> utf8 <-> encode with FaustByteNum
 */
public class FaustString implements TextEncoderIdx<String,String>{

    private static final String UTF8 = Charset.forName("utf8").name();
    private TextEncoderIdx<Byte,String> encoder = new FaustByteNum();

    public void setPad( URI uri ) {
        encoder.setPad( uri );
    }

    public boolean isSet() {
        return encoder.isSet();
    }

    public String encode(String txt, int ix ) {
        byte[] bytes;
        try {        
            bytes = txt.getBytes( UTF8 );
        } catch (UnsupportedEncodingException e) {
            throw new Unchecked( e );
        }

        StringBuilder str = new StringBuilder();
        for ( ByteCount by : ByteCount.count(bytes)) {
            str.append( encoder.encode( by.obj(), by.idx()) + "\n");
        }

        return str.toString();
    }

    public String decode(String lines, int ix ) {

        String[] linesArr = lines.split("\n");
        byte[]   byteArr  = new byte[ linesArr.length];

        for ( ArrayCount<String> line : ArrayCount.count(linesArr)) {
            byteArr[ line.idx()] = encoder.decode(line.obj(),line.idx());
        }

        EncodingGuesser.setSupportedEncodings( Arrays.asList( UTF8 ));
        Collection<String> coll = EncodingGuesser.getPossibleEncodings( byteArr );

        if ( !coll.contains( UTF8)) {
            throw new IllegalArgumentException( "not a correct pad" );
        }

        try {
            return new String( byteArr, UTF8 );
        } catch (UnsupportedEncodingException e) {
            // should be unlikely after the check just above
            throw new IllegalArgumentException( "not a correct pad" );
        }
    }

    // idea of a random prefix to enhance security
//    private byte[] createPrefix() {
//        int strlen = RandomUtils.nextInt() % 80;
//        byte[] prefix = new byte[ strlen + 1 ];
//
//        for ( int i = 0; i < strlen; ++i ) {
//            prefix[i] = (byte)((RandomUtils.nextInt() % 255 ) + Byte.MIN_VALUE);
//        }
//
//        prefix[ strlen] = Byte.MAX_VALUE;
//
//        return prefix;
//    }
}
