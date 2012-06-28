package org.openCage.util.app;

import org.openCage.util.www.wikidot.WikiDotGen;
import org.openCage.util.www.html.HtmlGen;

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
