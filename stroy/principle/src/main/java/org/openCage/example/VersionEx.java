package org.openCage.example;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

/**
 * Hold a version number
 * format: major.minor.path.build
 * major: new feature set
 * minor: small feture updates
 * path: bug fixed without feature
 * build: build number, increasing independent of branch or label or module
 *
 * Version are linear ordered by build number 
 */

public class VersionEx implements Comparable<VersionEx>{
    private final int major;
    private final int minor;
    private final int patch;
    private final int build;

    /* mark version types before patch: e.g. 1.0.1 */
    private final boolean prePatch;

    /**
     * Version without patch
     * @param major Major number
     * @param minor Minor number
     * @param build Build number
     */
    public VersionEx( int major, int minor, int build ) {
        this.major = major;
        this.minor = minor;
        patch = 0;
        this.build = build;

        prePatch = true;
    }

    /**
     * Version without patch
     * @param major Major number
     * @param minor Minor number
     * @param patch Patch number
     * @param build Build number
     */
    public VersionEx( int major, int minor, int patch, int build ) {
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

    public int compareTo( VersionEx VersionEx) {

        if ( prePatch && !VersionEx.prePatch ) {
            return -1;
        } else if ( !prePatch && VersionEx.prePatch ) {
            return 1;
        }

        if ( major !=  VersionEx.major  ) {
            return major - VersionEx.major;
        }

        if ( minor != VersionEx.minor ) {
            return minor - VersionEx.minor;
        }

        if ( patch != VersionEx.patch ) {
            return patch - VersionEx.patch;
        }

        return build - VersionEx.getBuildNum();
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VersionEx VersionEx = (VersionEx) o;

        if (build != VersionEx.build) return false;

        return true;
    }

    public int hashCode() {
        return build;
    }

    public static VersionEx parseVersionEx( String str ) {
        String[] parts = str.split( "\\." );

        if ( parts.length == 3 ) {
            return new VersionEx( Integer.parseInt( parts[0] ), Integer.parseInt( parts[1] ), Integer.parseInt( parts[2] ) );

        }

        if ( parts.length == 4 ) {
            return new VersionEx( Integer.parseInt( parts[0] ), Integer.parseInt( parts[1] ), Integer.parseInt( parts[2] ), Integer.parseInt( parts[3] ));
        }

        throw new IllegalArgumentException( "not a VersionEx" );
    }
}
