package org.openCage.stroy.tree.zip;

import org.openCage.util.lang.Method1;
import org.openCage.util.lang.VoidMethod1;
import org.openCage.util.string.Strings;

import java.io.InputStream;
import java.io.IOException;

import com.twmacinta.util.MD5;
import com.JavaExchange.www.RandomGUID;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/
public class FielUtils {

    public static void getCheckSumFromStream( InputStream is, VoidMethod1 onsuccess, VoidMethod1 onfailure ) {

//        try {
//            String checkSum = Strings.asHex( MD5.getHash( is ));
//            onsuccess.call( checkSum );
//        } catch ( IOException e ) {
//            onfailure.call( new RandomGUID().toString() );
//        }

    }
}
