package org.openCage.io.fspath;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 7/29/11
 * Time: 9:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class FSRelPathTest {

    @Test
    public void testToString() {
        assertEquals("a/b/c", new FSRelPath("a", "b", "c").toString());
    }

    @Test
    public void testAdd() {
        assertEquals( new FSRelPath("a", "b", "c"), new FSRelPath("a").add( "b", "c"));
    }

    @Test
    public void testAddToPath(){
        FSPathUNC unc = new FSPathUNC("\\\\duh\\blub");
        assertEquals( unc.add( "a", "c"), unc.add( new FSRelPath("a", "c")));

        FSPathUnix unix = new FSPathUnix("/root/is/root");
        assertEquals( unix.add( "a", "c"), unix.add( new FSRelPath("a", "c")));

        FSPathWindows win = new FSPathWindows("C:\\bulb");
        assertEquals(win.add("a", "c"), win.add(new FSRelPath("a", "c")));
    }
}
