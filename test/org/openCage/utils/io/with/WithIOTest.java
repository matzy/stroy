package org.openCage.utils.io.with;

import org.junit.Test;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNull;
import static org.openCage.utils.io.with.WithIO.withReader;
import org.openCage.utils.func.F1;
import org.openCage.utils.lang.unchecked.FileNotFoundExceptionUC;

import java.io.Reader;

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

public class WithIOTest {

    @Test
    public void testFileNotFound() {

        Integer res = null;
        try {
            withReader("no such file", new ReaderFunctor<Integer, Reader>() {
                public Integer c( Reader reader ) {
                    return 5;
                }
            } );

            fail( "throw?" );
        } catch ( FileNotFoundExceptionUC exp ) {
            // expected
        }

        assertNull( res );
    }
}
