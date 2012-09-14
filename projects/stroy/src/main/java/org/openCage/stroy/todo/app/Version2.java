package org.openCage.stroy.todo.app;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/
public class Version2 implements Comparable<Version2>{
    private final int major;
    private final int minor;
    private final int patch;
    private final int build;

    private final boolean prePatch;


    public Version2( int major, int minor, int build ) {
        this.major = major;
        this.minor = minor;
        patch = 0;
        this.build = build;

        prePatch = true;
    }

    public Version2( int major, int minor, int patch, int build ) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.build = build;

        prePatch = false;
    }


    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getBuildNum() {
        return build;
    }


    public String toString() {
        if ( prePatch ) {
            return "" + major + "." + minor + "." + build;
        } else {
            return "" + major + "." + minor + "." + patch + "." + build;
        }

    }

    public int compareTo( Version2 version) {

        if ( prePatch && !version.prePatch ) {
            return -1;
        } else if ( !prePatch && version.prePatch ) {
            return 1;
        }

        if ( major !=  version.major  ) {
            return major - version.major;
        }

        if ( minor != version.minor ) {
            return minor - version.minor;
        }

        if ( patch != version.patch ) {
            return patch - version.patch;
        }

        return build - version.getBuildNum();
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Version2 version = (Version2) o;

        if (build != version.build) return false;

        return true;
    }

    public int hashCode() {
        return build;
    }

    public static Version2 parseVersion( String str ) {
        String[] parts = str.split( "\\." );

        if ( parts.length == 3 ) {
            return new Version2( Integer.parseInt( parts[0] ), Integer.parseInt( parts[1] ), Integer.parseInt( parts[2] ) );

        }

        if ( parts.length == 4 ) {
            return new Version2( Integer.parseInt( parts[0] ), Integer.parseInt( parts[1] ), Integer.parseInt( parts[2] ), Integer.parseInt( parts[3] ));
        }

        throw new IllegalArgumentException( "not a version" );
    }
}
