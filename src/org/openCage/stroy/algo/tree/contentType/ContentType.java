package org.openCage.stroy.algo.tree.contentType;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Collection;

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

public class ContentType {

    private final String name;
    private String descr;
    private boolean isText;
    private boolean isXML;
    private boolean isPic;
    private List<String> extensions = new ArrayList<String>();
    private boolean isMusic;

    public ContentType( @NotNull String name ) {
        this.name = name;
    }

    public ContentType withDescr(String descr ) {
        this.descr = descr;
        return this;
    }

    public ContentType asText() {
        this.isText = true;
        return this;
    }

    public ContentType asXML() {
        this.isXML = true;
        return this;
    }

    public ContentType asPic() {
        this.isPic = true;
        return this;
    }


    public String toString() {
        return "<ContentType " + name + ">";
    }


    public String getDescr() {
        return descr;
    }

    public boolean isText() {
        return isText;
    }

    public boolean isXML() {
        return isXML;
    }

    public boolean isPic() {
        return isPic;
    }

    public ContentType withExtension( String ext ) {
        extensions.add( ext.toLowerCase( Locale.US ) );
        return this;
    }

    public ContentType asMusic() {
        this.isMusic = true;
        return this;
    }

    public Collection<String> getExtensions() {
        return extensions;
    }
}
