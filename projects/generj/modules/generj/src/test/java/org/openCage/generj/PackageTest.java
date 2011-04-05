package org.openCage.generj;

import org.junit.Test;

import javax.naming.ldap.StartTlsRequest;

import static junit.framework.Assert.assertEquals;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.Package.PACKAGE;
import static org.openCage.generj.Str.STR;

public class PackageTest {

    @Test
    public void testSimple() {

        PACKAGE( "org" );

        Class_ cl = PACKAGE( "org" ).dot( "openCage").dot( "generj" ).class_( "TheTest" );

        cl.implements_( "Foo" );


        Block body = cl.public_().method( "hello world" ).body();

        // System.out.println( "hello world" );
        body.call( DOT("System", "out", "println"), STR("hello world"));

        body._( NAME("System").dot("out").dot("println").call(STR("hello world")));


        System.out.println( cl );
    }

    @Test
    public void testValueOf() {
        assertEquals( "org.openCage.foo", Package.valueOf( "org.openCage.foo").toString());
    }
}
