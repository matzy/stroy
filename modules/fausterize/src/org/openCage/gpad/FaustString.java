package org.openCage.gpad;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.openCage.lang.errors.Unchecked;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

public class FaustString implements TextEncoderIdx<String>{

    private static Charset UTF8 = Charset.forName("utf8");
    private TextEncoderIdx<Byte> encoder = new FaustByteNum();

    public void setPad( URI uri ) {
        encoder.setPad( uri );
    }

    public boolean isSet() {
        return encoder.isSet();
    }

    public String encode(String ch, int ix ) {
        byte[] bytes = null;
        try {
            bytes = ArrayUtils.addAll( createPrefix(), ch.getBytes( "utf8" ));
        } catch (UnsupportedEncodingException e) {
            throw new Unchecked( e );
        }

        String ret = "";
        int idx = 0;
        for ( byte by : bytes ) {
            ret += encoder.encode(by,idx) + "\n";
            ++idx;
        }
        return ret;
    }

    public String decode(String lines, int ix ) {
        List<Byte> bytes = new ArrayList<Byte>();

        boolean prefix = true;
        int idx = 0;
        for ( String line : lines.split("\n")) {
            byte dec = encoder.decode(line,idx);
            ++idx;
            
            if ( prefix ) {
                if ( dec == Byte.MAX_VALUE ) {
                    prefix = false;
                }
                continue;
            }

            bytes.add( dec );
        }

        byte[] byteArr = new byte[ bytes.size()];
        int i = 0;
        for ( Byte by : bytes ) {
            byteArr[i] = by;
            ++i;
        }

        try {
            String ret =  new String( byteArr, "utf8");
            return ret.substring( ret.indexOf("\n") + 1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }

    private byte[] createPrefix() {
        int strlen = RandomUtils.nextInt() % 80;
        byte[] prefix = new byte[ strlen + 1 ];

        for ( int i = 0; i < strlen; ++i ) {
            prefix[i] = (byte)((RandomUtils.nextInt() % 255 ) + Byte.MIN_VALUE);
        }

        prefix[ strlen] = Byte.MAX_VALUE;

        return prefix;
    }
}
