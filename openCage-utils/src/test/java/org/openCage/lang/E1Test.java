package org.openCage.lang;

import org.junit.Test;
import org.openCage.lang.E1;
import junit.framework.Assert;

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

public class E1Test {


    @Test
    public void testComp() {
        E1<String,Integer> e = new E1<String,Integer>(){
            public String c(Integer integer) throws Exception {
                return integer.toString();
            }
        };

        try {
            Assert.assertEquals( "42", e.c(42) );
        } catch (Exception e1) {
            Assert.fail( "huh" );
        }
    }
}
