package org.openCage.lang.artifact;

import org.openCage.lang.Constants;

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

public final class JavaVersion implements Comparable<JavaVersion>{


    private int verNum;
    private boolean plus;


    private JavaVersion( int num, boolean plus ) {
        verNum = num;
        this.plus = plus;
    }

    public static JavaVersion valueOf( String str ) {
        boolean plus = str.endsWith( "P" ) || str.endsWith( "+" );
        String num = (plus ? str.substring( 0, str.length() - 1 ) : str);

        return new JavaVersion( Integer.valueOf(num), plus );
    }

    public static final JavaVersion V5 = new JavaVersion( 5, false );
    public static final JavaVersion V5P = new JavaVersion( 5, true );
    public static final JavaVersion V6 = new JavaVersion( 6, false );
    public static final JavaVersion V6P = new JavaVersion( 6, true);
    public static final JavaVersion V7 = new JavaVersion( 7, false);
    public static final JavaVersion V7P = new JavaVersion( 7, true );

    public String jvmVersion() {
        return "1." + verNum;
    }

    @Override public String toString() {
        return "" + verNum + ((plus) ? "+" : "");
    }

    @Override public int compareTo(JavaVersion o) {
        return  (2 * verNum ) + (plus ? 1 : 0) - (2 * o.verNum ) - (o.plus ? 1 : 0);
    }

    @Override public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof JavaVersion)) { return false; }

        JavaVersion that = (JavaVersion) o;

        if (plus != that.plus) { return false; }
        if (verNum != that.verNum) { return false; }

        return true;
    }

    @Override public int hashCode() {
        int result = verNum;
        result = Constants.HASHFACTOR * result + (plus ? 1 : 0);
        return result;
    }

}
