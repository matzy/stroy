package org.openCage.utils.prop.poc;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import com.google.inject.Injector;
import com.google.inject.Guice;

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
public class PersiPropTest {



    @Test
    public void testUniqueAndInit() {
        Injector injector = Guice.createInjector( new TestModule() );

        UsingProp up = injector.getInstance( UsingProp.class );

        assertNotNull( up );
        assertNotNull( up.getProp() );
        assertNotNull( up.getProp().get() );
        assertEquals( "bar", up.getProp().get());

        up.getProp().set( "duda" );
        assertEquals( "duda", up.getProp().get());

        UsingProp up2 = injector.getInstance( UsingProp.class );

        assertEquals( "duda", up2.getProp().get());

    }

    @Test
    public void test2Props() {
        Injector injector = Guice.createInjector( new TestModule() );

        Using2Props up2 = injector.getInstance( Using2Props.class );

        assertEquals( "bar", up2.getFoo().get());
        assertEquals( "123", up2.getWoo().get());
    }

    @Test
    public void testMixedProps() {
        Injector injector = Guice.createInjector( new TestModule() );

        UsingMixedProps mix = injector.getInstance( UsingMixedProps.class  );
        assertEquals( "bar", mix.getFoo().get());
        assertEquals( new Double(3.14159), mix.getPi().get() );
    }
}
