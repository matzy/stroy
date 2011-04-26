package org.openCage.babelgen;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;

import static junit.framework.Assert.assertEquals;

public class BabelGenTest {

    @Test
    public void testSimple() {
        new BabelGen( "org.openCage.babelgen.testbundle").generate( "org.openCage.testing", "TestBundle" );
    }

    @Test
    public void testFromFile() {
        try {
            new BabelGen( "org.openCage.babelgen.testbundle",
                    new PropertyResourceBundle( new FileInputStream( "/Users/stephan/projects/opencage-libs/modules/babelgen/src/test/resources/org/openCage/babelgen/testbundle_en.properties" ))).
                    generate( "org.openCage.testing", "TestBundle" );
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testMakeName() {
        assertEquals( "org_oo", BabelGen.makeName("org.oo"));
    }


}
