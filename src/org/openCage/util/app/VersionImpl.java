package org.openCage.util.app;

import com.google.inject.Inject;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/
public class VersionImpl implements Version, Comparable<Version>{
    private final int major;
    private final int minor;
    private final int build;


    @Inject
    public VersionImpl( @ForMajor int major, @ForMinor int minor, @ForBuildNumber int build ) {
        this.major = major;
        this.minor = minor;
        this.build = build;

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
        return "" + major + "." + minor + "." + build;
    }

    public int compareTo(Version version) {

        return build - version.getBuildNum();
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VersionImpl version = (VersionImpl) o;

        if (build != version.build) return false;

        return true;
    }

    public int hashCode() {
        return build;
    }

    public static Version parseVersion( String str ) {
        String[] parts = str.split( "\\." );

        if ( parts.length != 3 ) {
            throw new IllegalArgumentException( "not a version" );
        }

        return new VersionImpl( Integer.parseInt( parts[0] ), Integer.parseInt( parts[1] ), Integer.parseInt( parts[2] ) );
    }
}
