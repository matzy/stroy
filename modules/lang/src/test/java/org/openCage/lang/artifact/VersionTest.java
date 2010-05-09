package org.openCage.lang.artifact;

import org.junit.Test;
import org.openCage.lang.artifact.Version;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VersionTest {


    @Test
    public void testNums() {

        assertEquals( Version.valueOf( "1.0.0" ), Version.valueOf( "1.0.0" ));
        assertEquals( "1.0.0", Version.valueOf( "1.0.0" ).getShort());
        assertEquals( "1.0.0", Version.valueOf( "1.0.0" ).build(42).getShort());
        assertEquals( 42, Version.valueOf( "1.0.0" ).build(42).getBuildNumber());

        assertEquals( "foo", Version.valueOf("foo").getShort());

        assertEquals( "Beta", Version.valueOf("1.0").tag( "Beta" ).getTag());
        assertEquals( "beta", Version.valueOf("1.0").beta().getTag());
        assertEquals( "alpha", Version.valueOf("1.0").alpha().getTag());


//        assertTrue( Version.valueOf( "1.2.3").compareTo( Version.valueOf("2.1.1")) < 0 );
//        assertTrue( Version.valueOf( "1.2.3").compareTo( Version.valueOf("10.1.1")) < 0 );
    }

    @Test
    public void test2() {
        assertEquals( "1.0", Version.valueOf( "1.0" ).getShort());
    }


    @Test
    public void testSelf() {
        assertEquals( "Version.valueOf( \"1.0.2\" )", Version.valueOf( "1.0.2" ).self());
    }
}
