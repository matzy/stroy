package org.openCage.fausterize;

import eu.medsea.util.EncodingGuesser;
import org.openCage.fausterize.FaustByteNum;
import org.openCage.fausterize.TextEncoderIdx;
import org.openCage.lang.iterators.ArrayCount;
import org.openCage.lang.iterators.ByteCount;
import org.openCage.lang.errors.Unchecked;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;

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
