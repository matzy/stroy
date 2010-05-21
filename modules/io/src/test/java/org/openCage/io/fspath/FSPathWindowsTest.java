package org.openCage.io.fspath;

import com.muchsoft.util.Sys;
import org.junit.Before;
import org.junit.Test;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;

import java.util.Properties;
import java.util.prefs.Preferences;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class FSPathWindowsTest {

    @Before
    public void beforeMethod() {
        org.junit.Assume.assumeTrue( Sys.isWindows() );
    }



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
        if ( !Sys.isWindows() ) { throw new IllegalArgumentException("wtf"); }
        FSPathBuilder.getPath( "" );
    }

    @Test
    public void testAbsolute() {
        FSPath path = FSPathBuilder.getPath( "D:\\foo" );
        assertEquals( 1, path.size());

        assertEquals( "D:\\foo", path.toString() );
    }


    @Test
    public void testEnv() {
        System.out.println( System.getProperty("%APPDATA%"));

        Properties prop = System.getProperties();

        int i = 0;

        System.out.println( Preferences.systemRoot().absolutePath());

        System.out.println( System.getenv("APPDATA"));

        javax.swing.JFileChooser fr = new javax.swing.JFileChooser();
        javax.swing.filechooser.FileSystemView fw=fr.getFileSystemView();
        System.out.println(fw.getDefaultDirectory());



    }

    // TODO
    @Test
    public void testPreferences() {
        assertTrue( FSPathBuilder.getPreferences().toString().endsWith("/Library/Preferences"));
    }


}
