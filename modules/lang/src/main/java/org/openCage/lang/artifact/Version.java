//package org.openCage.lang.artifact;
//
//import org.openCage.lang.Constants;
//import org.openCage.lang.structure.Once;
//
///***** BEGIN LICENSE BLOCK *****
// * Version: MPL 1.1
// *
// * The contents of this file are subject to the Mozilla Public License Version
// * 1.1 (the "License"); you may not use this file except in compliance with
// * the License. You may obtain a copy of the License at
// * http://www.mozilla.org/MPL/
// *
// * Software distributed under the License is distributed on an "AS IS" basis,
// * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
// * for the specific language governing rights and limitations under the
// * License.
// *
// * The Original Code is stroy code.
// *
// * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
// * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
// * All Rights Reserved.
// *
// * Contributor(s):
// ***** END LICENSE BLOCK *****/
//
//public class Version { //implements Comparable<Version>{
//
//
//    private final String version;
//
//    private Once<Integer> buildNumber = new Once<Integer>( 0 );
//    private Once<String> tag = new Once<String>( "" );
//
//
//    public Version( String ver ) {
//        version = ver;
//    }
//
//    public static Version valueOf( String str  ) {
//        return new Version( str );
//    }
//
//    public Version build( int num ) {
//        buildNumber.set( num );
//        return this;
//    }
//
//    public int getBuildNumber()  {
//        return buildNumber.get();
//    }
//
//    public String getTag() {
//        return tag.get();
//    }
//
//    public Version tag( String str ) {
//        tag.set( str );
//        return this;
//    }
//
//    public Version beta() {
//        return tag( "beta" );
//    }
//
//    public Version alpha() {
//        return tag( "alpha" );
//    }
//
//    public String getShort() {
//        return version;
//    }
//
//
//    @Override public String toString() {
//        String ret = "Version: " + version;
//        if ( buildNumber.isSet()) {
//            ret += buildNumber.get();
//        }
//
//        if ( tag.isSet()) {
//            ret += " " + tag.get();
//        }
//
//        return ret;
//    }
//
//    @Override public boolean equals(Object o) {
//        if (this == o) { return true; }
//        if (!(o instanceof Version)) { return false; }
//
//        Version version1 = (Version) o;
//
//        if (buildNumber != null ? !buildNumber.equals(version1.buildNumber) : version1.buildNumber != null) { return false; }
//        if (version != null ? !version.equals(version1.version) : version1.version != null) { return false; }
//
//        return true;
//    }
//
//    @Override public int hashCode() {
//        int result = version != null ? version.hashCode() : 0;
//        result = Constants.HASHFACTOR * result + (buildNumber != null ? buildNumber.hashCode() : 0);
//        return result;
//    }
//
//}
