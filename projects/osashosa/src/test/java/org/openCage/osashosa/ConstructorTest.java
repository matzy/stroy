package org.openCage.osashosa;

import com.google.inject.Binder;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Provider;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

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

public class ConstructorTest {

    public static class Dflt {
        public Dflt() {}
    }

//    public static class DfltModule implements Module {
//
//        @Override
//        public void configure(Binder binder) {
//        }
//    }

    @Test
    public void defaultCnstr() {
        Guice.createInjector().getInstance( Dflt.class );
    }

    public static class One {
        @Inject
        public One() {}

        public One( int i ) {}
    }

    @Test
    public void oneInject() {
        Guice.createInjector().getInstance( One.class );        
    }


    public static class Arguments {
        public Arguments( One i ) {}
    }

    @Test( expected = ConfigurationException.class )
    public void arguments() {
        Guice.createInjector().getInstance( Arguments.class );        
    }


    public static class InjectedContrs {
        @Inject public InjectedContrs() {}
        @Inject public InjectedContrs( One one ) {}
    }
    
    @Test( expected = ConfigurationException.class )
    public void injectedContrs() {
        Guice.createInjector().getInstance( InjectedContrs.class );
    }


    public static class MultipleInjects {
        @Inject
        public String foo;

        @Inject public MultipleInjects() {}
    }

    @Test( expected = UnsupportedOperationException.class )
    public void multipleInjects() {
        Guice.createInjector().getInstance( MultipleInjects.class );
    }

    public static class MultipleInjects2 {
        @Inject
        public One foo;

        @Inject public MultipleInjects2( One two) {}
    }

    @Test( expected = UnsupportedOperationException.class )
    public void multipleInjects2() {
        Guice.createInjector().getInstance( MultipleInjects2.class );
    }


    public static class MethodInject {
        public One foo;

        public MethodInject() {}

        @Inject public void setFoo( One one ) { foo = one;}
    }

    @Test( expected = UnsupportedOperationException.class )
    public void methodInject() {
        MethodInject mi = Guice.createInjector().getInstance( MethodInject.class );
        assertNotNull( mi.foo );
    }


    


//    public static interface IFace {}
//
//    public static class PIF implements Provider<IFace> {
//
//        @Override
//        public IFace get() {
//            return new IFace(){};
//        }
//    }
//
//    public static class Mod implements Module {
//
//        @Override
//        public void configure(Binder binder) {
//            binder.bind( IFace.class ).to( PIF.class );
//        }
//    }

    public static class BaseMod implements Module {

        public static int count = 0;

        @Override public void configure(Binder binder) { count++; }

        @Override
        public boolean equals(Object o) {
            return o != null && (o instanceof BaseMod);
        }

        @Override
        public int hashCode() {
            return getClass().hashCode();
        }
    }

    public static class AMod implements Module {

        @Override
        public void configure(Binder binder) {
            binder.install( new BaseMod() );
        }
    }

    public static class BMod implements Module {

        @Override
        public void configure(Binder binder) {
            binder.install( new BaseMod() );
        }
    }

    @Test
    public void testSharedModule() {
        Guice.createInjector( new AMod(), new BMod() );

        assertEquals( 1, BaseMod.count );
    }

    



}
