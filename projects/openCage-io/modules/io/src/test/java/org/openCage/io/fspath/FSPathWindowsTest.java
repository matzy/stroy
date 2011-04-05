package org.openCage.io.fspath;

import org.junit.Before;
import org.junit.Test;
import org.openCage.lang.OS;

import java.util.Properties;
import java.util.prefs.Preferences;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class FSPathWindowsTest {

    @Before
    public void beforeMethod() {
        org.junit.Assume.assumeTrue( OS.isWindows() );
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

    @Test
    public void testPreferences() {
        if ( OS.isWindowsVistaPlus() ) {
            assertTrue( FSPathBuilder.getPreferences().toString().endsWith("\\AppData\\Roaming"));
        } else {
            assertTrue( FSPathBuilder.getPreferences().toString().endsWith("\\Application Data" ));
        }
    }


}
