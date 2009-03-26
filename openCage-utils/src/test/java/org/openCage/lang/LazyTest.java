package org.openCage.lang;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/


public class LazyTest {

    interface Fct extends E1<String, String> {
    }



    @Test
    public void testL1Derive() {

        String s = new L1<String, String>( new E1<String, String>() {
            public String c(String s) throws Exception {
                return s+"-foo";
            }
        }).c( new E0<String>() {
            public String c() throws Exception {
                return "duh";
            }
        });


        assertEquals( "duh-foo", s );

        String s2 = new L1<String, String>( new Fct() {
            public String c(String s) throws Exception {
                return s+"-fct";
            }
        }).c( new E0<String>() {
            public String c() throws Exception {
                return "duh";
            }
        });


        assertEquals( "duh-fct", s2 );
    }
}
