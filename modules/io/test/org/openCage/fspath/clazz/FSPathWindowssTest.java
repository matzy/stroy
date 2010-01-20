package org.openCage.fspath.clazz;

import org.junit.Test;
import org.openCage.fspath.protocol.FSPath;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Jan 20, 2010
 * Time: 9:05:45 AM                  
 * To change this template use File | Settings | File Templates.
 */
public class FSPathWindowssTest {


    @Test
    public void testSimple() {
        FSPath path = FSPathBuilder.getPath("C:");

        assertEquals( 0, path.size());
        assertEquals( "C:\\", path.toString());
    }

    @Test
    public void testSimpleLetter() {
        FSPath path = FSPathBuilder.getPath("G:");

        assertEquals( 0, path.size());
        assertEquals( "G:\\", path.toString());
    }

    @Test
    public void testAdd() {
        FSPath path = FSPathBuilder.getPath("X:").add("foo", "bar");

        assertEquals( 2, path.size());
        assertEquals( "X:\\foo\\bar", path.toString());
    }

    @Test( expected = IllegalArgumentException.class )
    public void testEmptyPath() {
        FSPathBuilder.getPath( "" );
    }

    @Test
    public void testAbsolute() {
        FSPath path = FSPathBuilder.getPath( "D:\\foo" );
        assertEquals( 1, path.size());

        assertEquals( "D:\\foo", path.toString() );
    }
}
