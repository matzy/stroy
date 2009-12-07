package org.openCage.gpad;

import org.openCage.withResource.impl.WithImpl;
import org.openCage.withResource.protocol.FileLineIterable;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

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
public class FausterizeCLT {

    public static void main(String[] args) {

        try {
            TextEncoder<String> tts = new TextToStory( new URI(args[1]) );

            String text = "";
            FileLineIterable it =  new WithImpl().getLineIteratorCloseInFinal( new File(args[2]));
            try {
                for ( String str : it ) {
                    text += str + "\n";
                }
            } finally {
                it.close();
            }


            if ( args[0].startsWith("e")) {
                System.out.println( tts.encode(text));
            } else {
                System.out.println( tts.decode(text));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
