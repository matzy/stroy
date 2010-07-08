package org.openCage.lang.structure;

import org.junit.Test;
import org.openCage.lang.structure.MRU;

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
public class MRUTest {

    @Test
    public void testAdding() {
        MRU<String> mru = new MRU<String>();

        mru.use( "foo" );
        assertEquals( "foo", mru.getAll().iterator().next() );

        mru.use( "duda" );
        assertEquals( "duda", mru.getAll().iterator().next() );

        mru.use( "foo" );
        assertEquals( "foo", mru.getAll().iterator().next() );
        assertEquals( 2, mru.getAll().size());

        mru.use( "333" );
        assertEquals( "333", mru.getAll().iterator().next() );
        assertEquals( 3, mru.getAll().size());
        mru.setMaxSize( 2 );
        assertEquals( 2, mru.getAll().size());

        mru.use( "444" );
        assertEquals( "444", mru.getAll().iterator().next() );
        assertEquals( 2, mru.getAll().size());

        mru.clear();
        assertEquals( 0, mru.getAll().size() );
    }


    @Test( expected = IllegalArgumentException.class )
    public void setMaxSize() {
        new MRU<String>().setMaxSize(0);
    }
}
