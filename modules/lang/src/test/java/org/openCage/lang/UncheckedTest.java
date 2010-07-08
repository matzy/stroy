package org.openCage.lang;

import org.junit.Test;
import org.openCage.lang.errors.Unchecked;

import java.net.MalformedURLException;
import java.net.URL;

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
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 *
 * Contributor(s):
 ***** END LICENSE BLOCK *****/

public class UncheckedTest {

    @Test
    public void testing() {
        IllegalArgumentException ill = new IllegalArgumentException("foo");
        Unchecked uc = Unchecked.wrap( ill  );

        assertEquals( "Unchecked Exception, Caused by: java.lang.IllegalArgumentException: foo", uc.toString());
        assertEquals( ill, uc.getSource());
    }


    public void foo() {
        try {
            new URL( "foo" );
        } catch (MalformedURLException e) {
            throw Unchecked.wrap(e);
        }
    }

    @Test( expected = Unchecked.class )
    public void testExecptionInMethodNotDeclaringThrows() {
        foo();
    }
}
