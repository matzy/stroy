package org.openCage.kleinod.collection;

import org.junit.Test;
import org.openCage.kleinod.type.Null;

import static junit.framework.Assert.*;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ***** END LICENSE BLOCK *****/

public class NullTest {

    public static interface Foo {
        void foo( int i );
        Foo again();
    }

    public static interface NotFoo {
    }

    @Test
    public void simple() {
        Null.of(Foo.class).foo(5);
    }

    @Test
    public void withReturn() {
        Null.of(Foo.class).again().again().again();
    }

    @Test
    public void is() {
        assertTrue( Null.is( Null.of(Foo.class)));
        assertTrue( Null.is( null ));
    }

    @Test
    public void isNot() {
        assertFalse( Null.is( "duh"));
    }

    @Test
    public void testEquals() {
        assertEquals(Null.of(Foo.class), Null.of(Foo.class));
        assertNotSame(Null.of(NotFoo.class), Null.of(Foo.class));

        // TODO generics ?
//        assertFalse( Null.of(Comparable.class).equals( Null.of(Foo.class)));
    }


}
