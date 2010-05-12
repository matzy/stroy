package org.openCage.property;

import org.junit.Test;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.F1;
import org.openCage.property.impl.PropertyImpl;
import org.openCage.property.protocol.Property;

import static junit.framework.Assert.assertEquals;

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

public class PropTesting {


    @Test
    public void testThrowDuringModify() {
        Property<String> prop = new PropertyImpl<String>( null, "default", "");

//        prop.set( "foo");
//        assertEquals( "foo", prop.get());

        try {
            prop.modify( new F1<String, String>() {
                @Override
                public String call(String s) {
                    throw new IllegalStateException( "i am not here" ); 
                }
            });
        } catch ( Unchecked exp ) {
            // expected
        }

        assertEquals( "default", prop.get());

    }
}
