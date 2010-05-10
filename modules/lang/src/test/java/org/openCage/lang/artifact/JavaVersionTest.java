package org.openCage.lang.artifact;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class JavaVersionTest {

    @Test
    public void testCompare() {
        assertTrue( JavaVersion.V5.compareTo( JavaVersion.V6) < 0 );
        assertTrue( JavaVersion.V5.compareTo( JavaVersion.V5P) < 0 );
        assertTrue( JavaVersion.V5.compareTo( JavaVersion.V5) <= 0 );

        assertTrue( JavaVersion.V6.compareTo( JavaVersion.V7) <= 0 );
        assertTrue( JavaVersion.V6P.compareTo( JavaVersion.V7) <= 0 );
        assertTrue( JavaVersion.V6P.compareTo( JavaVersion.V7P) <= 0 );

        assertEquals( "6+", JavaVersion.V6P.toString() );

        assertEquals( JavaVersion.V7, JavaVersion.V7 );


        assertEquals( "6+", JavaVersion.valueOf( "6P" ).toString());
    }



}
