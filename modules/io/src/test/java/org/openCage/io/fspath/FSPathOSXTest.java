package org.openCage.io.fspath;

import com.muchsoft.util.Sys;
import org.junit.Before;
import org.junit.Test;
import org.openCage.io.fspath.FSPathBuilder;

import static junit.framework.Assert.assertTrue;

public class FSPathOSXTest {

    @Before
    public void beforeMethod() {
        org.junit.Assume.assumeTrue( !Sys.isWindows() );
    }

    @Test
    public void testPreferences() {
        assertTrue( FSPathBuilder.getPreferences().toString().endsWith("/Library/Preferences"));
    }

    @Test
    public void testDocuments() {
        assertTrue( FSPathBuilder.getDocuments().toString().endsWith("/Documents"));
    }


}
