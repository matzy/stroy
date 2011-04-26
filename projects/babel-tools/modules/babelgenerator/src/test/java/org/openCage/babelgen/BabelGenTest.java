package org.openCage.babelgen;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class BabelGenTest {

    @Test
    public void testSimple() {
        new BabelGen( "org.openCage.babelgen.testbundle").generate( "org.openCage.testing", "TestBundle" );
    }

    @Test
    public void testFromFile() throws Exception {


        InputStream is = this.getClass().getResourceAsStream( "testbundle_en.properties");

        assertNotNull( is );

        String res = new BabelGen( "org.openCage.babelgen.testbundle",
                new PropertyResourceBundle( is )). //new FileInputStream( "/Users/stephan/projects/opencage-libs/modules/babelgen/src/test/resources/org/openCage/babelgen/testbundle_en.properties" ))).
                generate( "org.openCage.testing", "TestBundle" );

        // class name
        assertTrue( -1 < res.indexOf( "public class TestBundle" ) );

        // getter without args
        assertTrue( -1 < res.indexOf( "public String aaa(  ){" ) );

        // getter with args
        assertTrue( -1 < res.indexOf( "String ccc( String arg0, String arg1 ){" ) );
    }

    @Test
    public void testMakeName() {
        assertEquals( "org_oo", BabelGen.makeName("org.oo"));
    }


}
