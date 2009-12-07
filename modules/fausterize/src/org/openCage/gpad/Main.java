package org.openCage.gpad;

import org.openCage.withResource.impl.WithImpl;
import org.openCage.withResource.protocol.FileLineIterable;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.*;

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
public class Main {




    public static void main(String[] args) {

        //System.out.println( new StringToStory().encode( "openCage@gmail.com ttMo\\/es!" ));

//        FaustNum faust = new FaustNum();
//
//        System.out.println( faust.encode('s'));
//        System.out.println( faust.encode('c'));
//        System.out.println( faust.encode('h'));
//        System.out.println( faust.encode('o'));
//        System.out.println( faust.encode('k'));
//        System.out.println( faust.encode('o'));
//        System.out.println( faust.encode('l'));
//        System.out.println( faust.encode('a'));
//        System.out.println( faust.encode('d'));
//        System.out.println( faust.encode('e'));

        String foo = "abcü\u81FF??¨ß";
        FaustString fs = new FaustString();
        try {
            fs.setPad( new URI("file://" + Main.class.getResource("Main.class").getPath()));
            String code = fs.encode(foo,0);
            System.out.println(code);

            String foo2 = fs.decode(code,0);
            int i = 0;
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }



        URI pad = null;
        try {
            String path = "file://" + Main.class.getResource("Main.class").getPath();
            pad = new URI( path );
            TextEncoder<String> tts = new TextToStory( pad );
            String enc = tts.encode("schocolade\nist\ngut");
            System.out.println( enc );

            System.out.println( tts.decode(enc));

        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
