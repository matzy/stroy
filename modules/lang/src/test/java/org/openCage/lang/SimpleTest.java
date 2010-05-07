package org.openCage.lang;

import org.junit.Test;
import static junit.framework.Assert.assertNotNull;

public class SimpleTest {

    @Test
    public void testSimple() {
        LangArtifact la = new LangArtifact();
        assertNotNull(la.getArtifact());
        assertNotNull(la.getProject());
    }
}
