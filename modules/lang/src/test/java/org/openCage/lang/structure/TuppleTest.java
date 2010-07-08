package org.openCage.lang.structure;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

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

public class TuppleTest {

    @Test
    public void test() {
        T2<String, Integer> p = Tu.c("aaa",4);
        assertEquals( "aaa", p.i0);
        assertEquals( new Integer(4), p.i1);
    }

    @Test
    public void testEquals() {
        assertEquals( Tu.c(1,"foo"), new T2<Integer,String>(1,"foo"));
        assertEquals( Tu.c(1,"foo"), T2.valueOf(1,"foo"));
        assertNotSame( Tu.c(1,"foo"), T2.valueOf(2,"foo"));
    }

    @Test
    public void testHash() {
        assertEquals( Tu.c(7,"foo").hashCode(), T2.valueOf(7,"foo").hashCode());
        assertNotSame( Tu.c(8,"foo").hashCode(), T2.valueOf(7,"foo").hashCode());
    }



}
