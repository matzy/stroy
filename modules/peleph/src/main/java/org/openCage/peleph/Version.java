package org.openCage.peleph;

import net.jcip.annotations.Immutable;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

@Immutable
public class Version {

    private final int major;
    private final int minor;
    private final int patch;
    private final int build;
    private String originalString;

    public Version(int major, int minor, int patch, int build) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.build = build;
    }

    public int getBuildnumber() {
        return build;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

    @Override
    public String toString() {
        return "" + major + "." + minor + "." + patch + "." + build;
    }

    public int compareTo(Version version) {
        if (major != version.getMajor()) {
            return major - version.getMajor();
        }

        if (minor != version.getMinor()) {
            return minor - version.getMinor();
        }

        if (patch != version.getPatch()) {
            return patch - version.getPatch();
        }

        return build - version.getBuildnumber();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.major;
        hash = 37 * hash + this.minor;
        hash = 37 * hash + this.patch;
        hash = 37 * hash + this.build;
        return hash;
    }


    @SuppressWarnings({"CastToConcreteClass"})
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Version other = (Version) obj;
        if (this.major != other.major) {
            return false;
        }
        if (this.minor != other.minor) {
            return false;
        }
        if (this.patch != other.patch) {
            return false;
        }
        if (this.build != other.build) {
            return false;
        }
        return true;
    }

    public static Version parse(String str) {
        String[] parts = str.split( "\\." );

        Version ver;
        switch( parts.length ) {
            case 2: ver = new Version( Integer.parseInt( parts[0] ), Integer.parseInt( parts[1] ), 0, 0 ); break;
            case 3: ver = new Version( Integer.parseInt( parts[0] ), Integer.parseInt( parts[1] ), Integer.parseInt( parts[2] ), 0 ); break;
            case 4: ver = new Version( Integer.parseInt( parts[0] ), Integer.parseInt( parts[1] ), Integer.parseInt( parts[2] ), Integer.parseInt( parts[3] )); break;
            default: throw new IllegalArgumentException( "not a version" );
        }

        ver.originalString = str;

        return ver;

    }


    public String getOriginal() {
        return originalString;
    }
}
