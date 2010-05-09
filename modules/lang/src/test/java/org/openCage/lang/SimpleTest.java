package org.openCage.lang;

import org.junit.Test;
import org.openCage.lang.artifact.Scope;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

public class SimpleTest {

    @Test
    public void testSimple() {
        LangArtifact la = new LangArtifact();
        assertNotNull(la.getArtifact());
        assertNotNull(la.getProject());

        assertNotSame( Scope.COMPILE, Scope.TEST );
        assertNotSame( Scope.COMPILE, Scope.KNOWHOW );
    }
}
