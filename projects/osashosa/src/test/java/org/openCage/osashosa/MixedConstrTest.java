package org.openCage.osashosa;

import com.google.inject.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

public class MixedConstrTest {

    public class Duh {
        @Inject
        public Duh() {}
    }

    public static class Foo {
        public Integer a;

        @Inject
        public Foo( @Direct Integer a, Duh duh ) {
            this.a = a;
        }

    }

    public static class Bar {
        public Foo foo;

        @Inject
        public Bar( Factory<Foo,Integer> foo ) {
            this.foo = foo.get(5);
        }
    }

    @Test
    public void testFactory() {
        Injector inj = Guice.createInjector(Stage.DEVELOPMENT, new Module() {

            @Override
            public void configure(Binder binder) {
            }
        });

        Bar b = inj.getInstance( Bar.class );

        assertNotNull(b);
        assertNotNull( b.foo );
        assertEquals( new Integer(5), b.foo.a );

    }

    public static class BarFail {
        public Foo foo;

        @Inject
        public BarFail( Factory<Foo,String> foo ) {
            this.foo = foo.get("5");
        }
    }

    @Test( expected = Unchecked.class )
    public void testFactoryFail() {
        Injector inj = Guice.createInjector(Stage.DEVELOPMENT, new Module() {

            @Override
            public void configure(Binder binder) {
            }
        });

        BarFail b = inj.getInstance( BarFail.class );

    }


}
