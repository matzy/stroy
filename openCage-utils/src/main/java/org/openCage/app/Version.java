package org.openCage.app;

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

public class Version implements Comparable<Version>{
    private final int major;
    private final int minor;
    private final int patch;
    private final int build;

    private final boolean prePatch;


    public Version( int major, int minor, int build ) {
        this.major = major;
        this.minor = minor;
        patch = 0;
        this.build = build;

        prePatch = true;
    }

    public Version( int major, int minor, int patch, int build ) {
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

    public int compareTo( Version version) {

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

        Version version = (Version) o;

        if (build != version.build) return false;

        return true;
    }

    public int hashCode() {
        return build;
    }

    public static Version parseVersion( String str ) {
        String[] parts = str.split( "\\." );

        if ( parts.length == 3 ) {
            return new Version( Integer.parseInt( parts[0] ), Integer.parseInt( parts[1] ), Integer.parseInt( parts[2] ) );

        }

        if ( parts.length == 4 ) {
            return new Version( Integer.parseInt( parts[0] ), Integer.parseInt( parts[1] ), Integer.parseInt( parts[2] ), Integer.parseInt( parts[3] ));
        }

        throw new IllegalArgumentException( "not a version" );
    }
}
