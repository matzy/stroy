package org.openCage.stroy2;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jun 28, 2009
 * Time: 5:12:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class StroyCmdTest {

    @Test
    public void testArgsLength() {
        assertTrue( new CmdBuilder(new String[]{"1", "2"}).isOk() );
        assertFalse( new CmdBuilder(new String[]{"1"}).isOk() );
    }
}
