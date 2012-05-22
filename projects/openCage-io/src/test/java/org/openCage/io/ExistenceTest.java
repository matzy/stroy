package org.openCage.io;

import org.junit.Test;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExistenceTest {

    @Test
    public void testEnsure() {
        FSPath tmp = FSPathBuilder.getTmpDir().add( "woo", "txt.txt" );

        assertFalse( tmp.parent().toFile().exists() );

        IOUtils.ensurePath( tmp.toFile() );

        assertTrue( tmp.parent().toFile().exists() );
    }
}
