package org.openCage.stroy.tree.filesystem;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.openCage.lang.Maybe;
import org.openCage.stroy.tree.NoedGen;

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
public class FsNoedGenSelectorTest {

    @Test
    public void testYesOnDir() {
        Maybe<? extends NoedGen> gen = new FsNoedGenSelector().get( "", false );

        assertTrue( gen.is );
    }

    @Test
    public void testNoOnFile() {
        Maybe<? extends NoedGen> gen = new FsNoedGenSelector().get( "", false );

        assertTrue( gen.is );
    }

    @Test
    public void testNoOnSvn() {
        Maybe<? extends NoedGen> gen = new FsNoedGenSelector().get( "svn://http:/foo", false );

        assertFalse( gen.is );
    }
}
