package org.openCage.stroy.algo.tree.contentType;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Collection;

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
 * ContentType describes the content of a object (fiel) for stroy purposes
 * meta information should give an idea how java can analyse the content
 */
public class ContentType {

    private final String name;
    private String       descr;
    private boolean      isText;
    private boolean      isXML;
    private boolean      hasEXIF;
    private boolean      hasID3;
    private List<String> extensions = new ArrayList<String>();

    public ContentType( @NotNull String name ) {
        this.name = name;
    }

    public ContentType withDescr( @NotNull String descr ) {
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

    public ContentType withEXIF() {
        this.hasEXIF = true;
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

    public boolean hasEXIF() {
        return hasEXIF;
    }

    public boolean hasID3() {
        return hasID3;
    }

    public ContentType withExtension( String ext ) {
        extensions.add( ext.toLowerCase( Locale.US ) );
        return this;
    }

    public ContentType withID3() {
        this.hasID3 = true;
        return this;
    }

    public Collection<String> getExtensions() {
        return extensions;
    }
}
