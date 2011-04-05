package org.openCage.lang;

import java.util.regex.Pattern;


/***** BEGIN LICENSE BLOCK *****
 * New BSD License
 * Copyright (c) 2006 - 2010, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Stephan Pfab nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

public class OS {

    private OS() {}

    private static String osName = System.getProperty( "os.name" );

    private final static Pattern unix = Pattern.compile( "Linux|Mac OS X|AIX|Digital Unix|FreeBSD|HP UX|Irix|Solaris");

    protected static void setOsName( String osname) {
        osName = osname;
    }

    /**
     * Is this Windows ?
     * @return true iff this system is windows
     */
    public static boolean isWindows() {
        return osName.startsWith("Windows");
    }


    /**
     * Is this Windows Vista or later
     * @return true iff this system is windows vista or later
     */
    public static boolean isWindowsVistaPlus() {
        return osName.equals( "Windows Vista") || osName.equals( "Windows 7");
    }
    /**
     * Is this Linux ?
     * @return true iff this system is Linux
     */
    public static boolean isLinux() {
        return osName.startsWith("Linux");
    }

    /**
     * Is this OSX ?
     * @return true iff this system is OSX
     */
    public static boolean isOSX() {
        return osName.equals( "Mac OS X" );
    }

    /**
     * Is this Unix ?
     * @return true iff this system is Unix
     */
    public static boolean isUnix() {
        return unix.matcher( osName ).matches();
    }

}
