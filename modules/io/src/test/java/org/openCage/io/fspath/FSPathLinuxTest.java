package org.openCage.io.fspath;

import com.muchsoft.util.Sys;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class FSPathLinuxTest {

    @Before
    public void beforeMethod() {
        org.junit.Assume.assumeTrue( Sys.isLinux() );
    }

    // TODO
    @Test
    public void testPreferences() {
        assertTrue( FSPathBuilder.getPreferences().toString().endsWith("/Library/Preferences"));
    }
    


}
