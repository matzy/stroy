package org.openCage.gpad;

import org.openCage.lang.protocol.FE1;
import org.openCage.withResource.impl.WithImpl;

import java.io.File;
import java.io.InputStream;

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
public class StringToStory implements TextEncoder<String>{

    private FaustNum faust = new FaustNum();

    public StringToStory() {
    }

    public String encode( final String str ) {
        return new WithImpl().withInputStream( new File( getClass().getResource("FaustNum.class").getPath()),
                new FE1<String, InputStream>() {
                    public String call(InputStream inputStream) throws Exception {
                        String ret = "";
                        for ( int i = 0; i < str.length(); ++i ) {
                            byte pad = (byte)(inputStream.read() % 256);
                            byte code = xor( (byte)str.charAt(i), pad );
                            char ch = (char)(code + 128);
                            System.out.println("" + ch );
                            ret += faust.encode((char)ch) + "\n";
                        }
                        return ret;
                    }
                });
    }

    public String decode(String line) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static byte xor( byte a, byte b ) {
        return (byte)((int)a ^ (int)b);
    }

}
