package org.openCage.lang.artifact;

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
public class JavaVersion {


    private int verNum;
    private boolean plus;

    private JavaVersion( int num, boolean plus ) {
        verNum = num;
        this.plus = plus;
    }

    public final static JavaVersion v5     = new JavaVersion( 5, false );
    public final static JavaVersion v5plus = new JavaVersion( 5, true );
    public final static JavaVersion v6     = new JavaVersion( 6, false );
    public final static JavaVersion v6plus = new JavaVersion( 6, true);
    public final static JavaVersion v7     = new JavaVersion( 7, false);
    public final static JavaVersion v7plus = new JavaVersion( 7, true );

    public String jvmVersion() {
        return "1." + verNum;
    }

    /** TODO ugh */
    public boolean is6() {
        return verNum == 6;
    }

    @Override
    public String toString() {
        return "" + verNum + ((plus) ? "+" : "");
    }
}
