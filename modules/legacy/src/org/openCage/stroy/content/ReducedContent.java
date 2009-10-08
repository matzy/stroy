package org.openCage.stroy.content;

import org.openCage.stroy.algo.fuzzyHash.HasDistance;

import java.io.File;

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
 * The idea here is to replace a real file with ReducedContent, i.e. with just its fingerprint and fuzzyHash
 * Also good to build tests
 */
public class ReducedContent implements Content {
    private final String    name;
    private final String    checksum;
    private final HasDistance fuzzyHash;
    private final String    typ;


    public ReducedContent( final String    name,
                           final String    checksum,
                           final HasDistance fuzzyHash,
                           final String    typ ) {
        this.name     = name;
        this.checksum = checksum;
        this.fuzzyHash = fuzzyHash;
        this.typ       = typ;
    }

    public ReducedContent( final Content orig ) {
        this.name      = orig.getName();
        this.checksum  = orig.getChecksum();
        this.fuzzyHash = orig.getFuzzyHash();
        this.typ       = orig.getType();
    }


    public String getName() {
        return name;
    }

    public String getChecksum() {
        return checksum;
    }

    public HasDistance getFuzzyHash() {
        return fuzzyHash;
    }

    public String getType() {
        return typ;
    }

    // lets refactor
    public String getLocation() {
        // TODO
        return "here";
    }

    public File getFile() {
        return null;
    }


    public String toString() {
        return "Reduced: " + name;
    }
}
