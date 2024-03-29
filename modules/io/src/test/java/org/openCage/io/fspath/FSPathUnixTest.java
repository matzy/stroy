package org.openCage.io.fspath;

import org.junit.Before;
import org.junit.Test;
import org.openCage.lang.OS;

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

public class FSPathUnixTest {

    @Before
    public void beforeMethod() {
        org.junit.Assume.assumeTrue( !OS.isWindows() );
    }


    @Test
    public void testXDG(){

        // TODO

        System.out.println( System.getenv("$XDG_CONFIG"));
        System.out.println( System.getenv("XDG_CONFIG"));
    }

}
