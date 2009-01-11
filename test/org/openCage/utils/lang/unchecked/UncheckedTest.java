package org.openCage.utils.lang.unchecked;

import static org.openCage.utils.lang.unchecked.Unchecked.unchecked;
import org.junit.Test;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

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

public class UncheckedTest {

    public abstract class Foo {
        public abstract void nothrow();
    }

    /**
     * the test is that this compiles
     * i.e. make a exception unchecked
     */
    @Test
    public void testUnchecked() {

        try {
            new Foo() {
                public void nothrow() {
                    throw unchecked( new FileNotFoundException( "duda" ));
                }
            }.nothrow();

            fail( "should have thrown" );

        } catch ( FileNotFoundExceptionUC exp ) {
            // expected
        }

    }
}
