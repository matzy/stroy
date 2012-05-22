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
