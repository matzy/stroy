package org.openCage.osashosa;

import com.google.inject.*;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertTrue;

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

public class LoopDetection {

    public static class A {
        @Inject public A( B b) {}
    }
    public static class B {
        @Inject public B( C c ) {}

    }
    public static class C {
        @Inject public C( A a ) {}
    }

    @Test( expected = ConfigurationException.class )
    public void test3Loop() {
        Injector inj = Guice.createInjector( Stage.DEVELOPMENT, new Module() {

            @Override
            public void configure(Binder binder) {
            }
        });

        A a = inj.getInstance( A.class);

        assertTrue( a instanceof Object);

    }
    
    public static interface I{}
    
    public static class One implements I {
        @Inject public One( @Named("Foo") I i ) {}
    }
    
    public static class Two implements I {}

    @Test
    public void testNoLoop() {
        Injector inj = Guice.createInjector( Stage.DEVELOPMENT, new Module() {

            @Override
            public void configure(Binder binder) {
                binder.bind( I.class ).to( One.class );
                binder.bind( I.class ).annotatedWith( Names.named("Foo")).to( Two.class );

            }
        });

        I a = inj.getInstance( I.class);

        assertTrue( a instanceof One );

    }

}
