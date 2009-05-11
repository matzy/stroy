package org.openCage.stroy.find;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.io.File;

public class FindCltTest {

    @Test
    public void testSimple() {

        String[] args = { "--help" };

        FindClt.main( args );

        System.out.println( System.getProperty("user.dir"));

        File what = new File( "stroy/find/src/test/resources/org/openCage/find/testSimple/What.java" );
        System.out.println( what.getAbsolutePath());

        assertTrue( what.exists() );

        String[] simpleArgs = { what.getAbsolutePath(), what.getParent()};
        FindClt.main( simpleArgs );
    }
}
