package org.openCage.util.app.gen;

import org.openCage.util.app.AppInfo;
import org.openCage.util.app.Reference;
import org.openCage.util.www.html.HtmlGen;

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
public class GenHtmlRefs {

    private final AppInfo appInfo;

    public GenHtmlRefs( AppInfo appInfo ) {
        this.appInfo = appInfo;
    }

    public void gen() {
        printRuntime();
        printBuild();
        printTest();
    }

    private void printRuntime() {
        System.out.println( HtmlGen.header(2, "Libraries in the App" ) );
        for ( Reference ref : appInfo.getReferences() ) {
            if ( ref.isRuntime() ) {
                ref.printHtmlLink();
            }
        }
    }

    private void printBuild() {
        System.out.println( HtmlGen.header(2, "Tools used to Build" ) );
        for ( Reference ref : appInfo.getReferences() ) {
            if ( ref.isBuild() ) {
                ref.printHtmlLink();
            }
        }
    }
    private void printTest() {
        System.out.println( HtmlGen.header(2, "Test Tools" ) );
        for ( Reference ref : appInfo.getReferences() ) {
            if ( ref.isTest() ) {
                ref.printHtmlLink();
            }
        }
    }


}
