package org.openCage.fspath.clazz;

import org.junit.Test;
import org.openCage.fspath.impl.FSPathUnix;
import org.openCage.fspath.protocol.FSPath;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 30, 2009
 * Time: 6:27:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class FSPathTest {

    @Test
    public void testSimple() {
        FSPath path = FSPathBuilder.getPath("/");

        assertEquals( 0, path.size());
        assertEquals( "/", path.toString());
    }

    @Test
    public void testAdd() {
        FSPath path = FSPathBuilder.getPath("/").add("foo", "bar");

        assertEquals( 2, path.size());
        assertEquals( "/foo/bar", path.toString());
    }

    @Test( expected = IllegalArgumentException.class )
    public void testEmptyPath() {
        FSPathBuilder.getPath( "" );
    }
}
