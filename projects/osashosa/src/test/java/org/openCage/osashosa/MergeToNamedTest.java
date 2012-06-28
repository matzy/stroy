package org.openCage.osashosa;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

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

/**
 * demonstrates howto bind one class to to different named injects
 */
public class MergeToNamedTest {

    public static class A {
        @Inject
        public A( @Named("A") String str ) {
            System.out.println( str );
        }
    }

    public static class B {
        @Inject
        public B( @Named("B") String str ) {
            System.out.println( str );
        }
    }

    public static class Merge implements Provider<String>  {

        public String merged;

        @Inject
        public Merge( String str ) { merged = str; }

        @Override
        public String get() {
            return "foo";
        }
    }

    public static class Sing implements Provider<String> {

        public static int count = 0;

        @Inject public Sing() {
            System.out.println("singggggggg");
            count++;
        }

        @Override
        public String get() {
            return "foo";
        }
    }


    @Test
    public void test() {

        Injector inj = Guice.createInjector( new Module() {

            @Override
            public void configure(Binder binder) {
                binder.bind( A.class ).to( A.class );
                binder.bind( B.class ).to( B.class );
                binder.bind( String.class ).annotatedWith( Names.named( "A" )).toProvider( Merge.class);
                binder.bind( String.class ).annotatedWith( Names.named( "B" )).toProvider( Merge.class);
                binder.bind( String.class ).toProvider( Sing.class ).in(Singleton.class );
            }
        });

        inj.getInstance( A.class );
        inj.getInstance( B.class );

        assertEquals( 1, Sing.count );

    }


}
