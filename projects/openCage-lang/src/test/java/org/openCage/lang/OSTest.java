package org.openCage.lang;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class OSTest {

    // values from
    //http://mindprod.com/jgloss/properties.html#OSNAME

    @Test
    public void testOSX() {

        OS.setOsName( "Mac OS X");

        assertFalse( OS.isLinux() );
        assertFalse( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertTrue( OS.isUnix() );
        assertTrue( OS.isOSX() );
    }

    @Test
    public void testWindows2000() {

        OS.setOsName( "Windows 2000");

        assertFalse( OS.isLinux() );
        assertTrue( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertFalse( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testWindows7() {

        OS.setOsName( "Windows 7");

        assertFalse( OS.isLinux() );
        assertTrue( OS.isWindows() );
        assertTrue( OS.isWindowsVistaPlus() );
        assertFalse( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testWindows95() {

        OS.setOsName( "Windows 95");

        assertFalse( OS.isLinux() );
        assertTrue( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertFalse( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testWindows98() {

        OS.setOsName( "Windows 98");

        assertFalse( OS.isLinux() );
        assertTrue( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertFalse( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testWindowsNT() {

        OS.setOsName( "Windows NT");

        assertFalse( OS.isLinux() );
        assertTrue( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertFalse( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testWindowsXP() {

        OS.setOsName( "Windows XP");

        assertFalse( OS.isLinux() );
        assertTrue( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertFalse( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testWindowsVista() {

        OS.setOsName( "Windows Vista");

        assertFalse( OS.isLinux() );
        assertTrue( OS.isWindows() );
        assertTrue( OS.isWindowsVistaPlus() );
        assertFalse( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testAIX() {

        OS.setOsName( "AIX");

        assertFalse( OS.isLinux() );
        assertFalse( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertTrue( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testDigitalUnix() {

        OS.setOsName( "Digital Unix");

        assertFalse( OS.isLinux() );
        assertFalse( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertTrue( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testFreeBSD() {

        OS.setOsName( "FreeBSD");

        assertFalse( OS.isLinux() );
        assertFalse( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertTrue( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testHPUX() {

        OS.setOsName( "HP UX");

        assertFalse( OS.isLinux() );
        assertFalse( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertTrue( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testIrix() {

        OS.setOsName( "Irix");

        assertFalse( OS.isLinux() );
        assertFalse( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertTrue( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testSolaris() {

        OS.setOsName( "Solaris");

        assertFalse( OS.isLinux() );
        assertFalse( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertTrue( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testLinux() {

        OS.setOsName( "Linux");

        assertTrue( OS.isLinux() );
        assertFalse( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertTrue( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testMacOS() {

        OS.setOsName( "Mac OS");

        assertFalse( OS.isLinux() );
        assertFalse( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertFalse( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }


    @Test
    public void testMPEiX() {

        OS.setOsName( "MPE/iX");

        assertFalse( OS.isLinux() );
        assertFalse( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertFalse( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testNetware411() {

        OS.setOsName( "Netware 4.11");

        assertFalse( OS.isLinux() );
        assertFalse( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertFalse( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }

    @Test
    public void testOS2() {

        OS.setOsName( "OS/2");

        assertFalse( OS.isLinux() );
        assertFalse( OS.isWindows() );
        assertFalse( OS.isWindowsVistaPlus() );
        assertFalse( OS.isUnix() );
        assertFalse( OS.isOSX() );
    }


}
