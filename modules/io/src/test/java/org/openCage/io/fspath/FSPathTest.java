package org.openCage.io.fspath;

import org.junit.Test;
import org.openCage.lang.count.Count;

import static junit.framework.Assert.assertEquals;

public class FSPathTest {

    @Test
    public void testSimpleUnixRoot() {
        FSPath path = new FSPathUnix( "/");

        assertEquals( 1, path.size());
       assertEquals( "/", path.toString());
    }

    @Test
    public void testSimpleWin() {

        FSPath path = new FSPathWindows( "C:");

        assertEquals( 1, path.size());
        assertEquals( "C:\\", path.toString());
    }

    @Test
    public void testAddUnix() {
        FSPath path = new FSPathUnix( "/").add("foo", "bar");

        assertEquals( 3, path.size());
        assertEquals( "/foo/bar", path.toString());
    }

    @Test( expected = IllegalArgumentException.class )
    public void testEmptyPathUnix() {
        new FSPathUnix( "" );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testEmptyPathWindows() {
        new FSPathWindows( "" );
    }

    @Test
    public void testAbsoluteUnix() {
        FSPath path = new FSPathUnix( "/foo" );
        assertEquals( 2, path.size());

        assertEquals( "/foo", path.toString() );
    }

    @Test
    public void testXDG(){

        // TODO

        System.out.println( System.getenv("$XDG_CONFIG"));
        System.out.println( System.getenv("XDG_CONFIG"));
    }

    @Test
    public void testFromFileUnix() {

        FSPath path = new FSPathUnix( "/foo/woo/duuuuuu/test.txt" );
        assertEquals( path, new FSPathUnix( path.toFile()));
    }

    @Test
    public void testRootUnix() {
        assertEquals( 1, new FSPathUnix("/").size() );
    }


    @Test
    public void testIteratorUnix() {
        int tested = 0;
        for ( Count<String> elem : Count.count(new FSPathUnix("/a/b/c"))) {
            if ( elem.idx() == 0 ) {
                assertEquals( "/", elem.obj());
                tested++;
            }
            if ( elem.idx() == 1 ) {
                assertEquals( "a", elem.obj());
                tested++;
            }
            if ( elem.idx() == 2 ) {
                assertEquals( "b", elem.obj());
                tested++;
            }
            if ( elem.idx() == 3 ) {
                assertEquals( "c", elem.obj());
                tested++;
            }
        }

        assertEquals(4, tested );
    }

    @Test
    public void testIteratorWindows() {
        int tested = 0;
        for ( Count<String> elem : Count.count(new FSPathWindows("C:\\a\\b\\c"))) {
            if ( elem.idx() == 0 ) {
                assertEquals( "C:", elem.obj());
                tested++;
            }
            if ( elem.idx() == 1 ) {
                assertEquals( "a", elem.obj());
                tested++;
            }
            if ( elem.idx() == 2 ) {
                assertEquals( "b", elem.obj());
                tested++;
            }
            if ( elem.idx() == 3 ) {
                assertEquals( "c", elem.obj());
                tested++;
            }
        }

        assertEquals(4, tested );
    }


    @Test
    public void testSimpleLetterWin() {
        FSPath path = new FSPathWindows("G:");

        assertEquals( 1, path.size());
        assertEquals( "G:\\", path.toString());
    }

    @Test
    public void testAddWindows() {
        FSPath path = new FSPathWindows( "X:").add("foo", "bar");

        assertEquals( 3, path.size());
        assertEquals( "X:\\foo\\bar", path.toString());
    }


    @Test
    public void testAbsoluteWindows() {
        FSPath path = new FSPathWindows( "D:\\foo" );
        assertEquals( 2, path.size());

        assertEquals( "D:\\foo", path.toString() );
    }


}
