package org.openCage.io.fspath;

import com.muchsoft.util.Sys;
import org.junit.Before;
import org.junit.Test;

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
