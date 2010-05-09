package org.openCage.lang.artifact;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: May 6, 2010
 * Time: 4:00:21 PM
 * To change this template use File | Settings | File Templates.
 */
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


        assertEquals( "JavaVersion.V6P", JavaVersion.V6P.quine());
    }



}
