package org.openCage.util.app;

import org.openCage.util.www.wikidot.WikiDotGen;
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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

/**
 * Collect information about used libraries / code snippets ...
 */
public class Reference {

    private final String prog;
    private String descr;
    private String descr_de;
    private String descr_es;
    private String address;
    private String shortAddress;

    private String licence;
    private String licenceShort;

    private String typ;
    private String version;


    public Reference( String prog  ) {
        this.prog = prog;
    }

    public Reference typ( String typ ) {
        this.typ = typ;
        return this;
    }

    public Reference address( String address ) {
        this.address = address;
        return this;
    }

    public Reference address( String address, String shortAdd ) {
        this.address = address;
        this.shortAddress = shortAdd;
        return this;
    }


    public Reference licence( String licence, String shortLicence ) {
        this.licence      = licence;
        this.licenceShort = shortLicence;
        return this;
    }

    public Reference apache2() {
        licence = "http://www.apache.org/licenses/LICENSE-2.0";
        licenceShort = "Apache 2.0";
        return this;
    }

    public Reference cpl() {
        licence = "http://junit.sourceforge.net/cpl-v10.html";
        licenceShort = "CPL 1.0";
        return this;
    }

    public Reference lgpl() {
        licence = "http://www.gnu.org/copyleft/lesser.html";
        licenceShort = "LGPL";
        return this;
    }

    public Reference gpl2() {
        licence = "http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt";
        licenceShort = "GPL2";
        return this;
    }

    public Reference bsd() {
        licence = "http://www.opensource.org/licenses/bsd-license.php";
        licenceShort = "BSD";
        return this;
    }

    public Reference description( String text ) {
        descr = text;
        return this;
    }

    public String getProg() {
        return prog;
    }

    public String getDescr() {
        return descr;
    }

    public String getAddress() {
        return address;
    }

    public String getShortAddress() {
        return shortAddress;
    }

    public String getLicence() {
        return licence;
    }

    public String getShortLicence() {
        return licenceShort;
    }

    public void printWikiFull() {
        System.out.println("+++ " + prog );
        if ( version != null ) {
            System.out.print("Version: " + version + ", ");
        }
        System.out.print( "Link: " + WikiDotGen.externalLink( address, shortAddress ) + ", ");
        System.out.println( "Licence: " + WikiDotGen.externalLink( licence, licenceShort ));
        System.out.println( descr );
        if ( descr_de != null ) {
            System.out.println( "DE: " + descr_de );
        }

        if ( descr_es != null ) {
            System.out.println( "ES: " +  descr_es );
        }
    }

    public boolean isRuntime() {
        return typ.equals( "runtime" );
    }

    public boolean isTest() {
        return typ.equals( "test" );
    }
    public boolean isBuild() {
        return typ.equals( "build" );
    }

    public void printHtmlLink() {
        System.out.println( HtmlGen.link( address, prog ));
    }

    public Reference version(String version) {
        this.version = version;
        return this;
    }

    public Reference de( String descr ) {
        this.descr_de = descr;
        return this;
    }

    public Reference es( String descr ) {
        this.descr_es = descr;
        return this;
    }


    public String getDescr_de() {
        return descr_de;
    }

    public String getDescr_es() {
        return descr_es;
    }
}
