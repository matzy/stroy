package org.openCage.io.fspath;

import org.junit.Before;
import org.junit.Test;
import org.openCage.lang.OS;

import static junit.framework.Assert.assertTrue;

public class FSPathLinuxTest {

    @Before
    public void beforeMethod() {
        org.junit.Assume.assumeTrue( OS.isLinux() );
    }

    // TODO
    @Test
    public void testPreferences() {
        assertTrue( FSPathBuilder.getPreferences().toString().endsWith("/Library/Preferences"));
    }
    


}
