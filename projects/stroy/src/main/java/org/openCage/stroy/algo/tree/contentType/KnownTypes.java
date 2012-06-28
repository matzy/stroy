package org.openCage.stroy.algo.tree.contentType;

import java.util.Map;
import java.util.HashMap;
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

public class KnownTypes {

    private Map<String, ContentType> known = new HashMap<String, ContentType>();
    private Map<String, ContentType> exts = new HashMap<String, ContentType>();

    public ContentType get( String name ) {
        return known.get(name);
    }

    public Collection<ContentType> getAll() {
        return known.values();
    }

    public KnownTypes() {
        known.put( "Java", new ContentType( "Java").
                withDescr( "Java program source file" ).
                asText().
                withExtension( "Java"));
        known.put( "C", new ContentType( "C").
                withDescr( "C/C++ program source or header file" ).
                asText().
                withExtension( "cpp").
                withExtension( "c++").
                withExtension( "cc").
                withExtension( "c").
                withExtension( "hpp").
                withExtension( "h"));
        known.put( "Text", new ContentType( "Text").
                withDescr( "Text" ).
                asText().
                withExtension( "text").
                withExtension( "txt"));
        known.put( "TeX", new ContentType( "TeX").
                withDescr( "TeX file (Knuth)" ).
                asText().
                withExtension( "tex"));

        known.put( "JPG", new ContentType( "JPG").
                withDescr( "JPEG picture" ).
                withEXIF().
                withExtension( "jpeg" ).
                withExtension( "jpg"));

        known.put( "MP3", new ContentType( "MP3").
                withDescr( "MPEG3 audio file" ).
                withID3().
                withExtension( "mp3" ).
                withExtension( "mpg3" ).
                withExtension( "mpeg3"));

        add( "PDF").withDescr( "Adobe portable document format" ).
                asText().
                withExtension("pdf");


        add( "log").withDescr( "log file" ).
                asText().
                withExtension( "log");

        add( "xml").withDescr( "XML file" ).
                asText().
                asXML().
                withExtension("xml");

        add( "html" ).withDescr( "hypertext meta language" ).
                asText().
                asXML().
                withExtension("html").
                withExtension("htm");
//        "ism", "",
//        "dtd", "XML scheme definition",
//        "jspx", "java server page in xml",
//        "plist", "",
//        "xls", "MS Excel data",
//        "iml", "IntelliJ Idea module description",
//        "ipr", "IntelliJ Idea project description",
//        "iws", "" };



        for ( ContentType ct : known.values() ) {
            for ( String ext : ct.getExtensions()) {
                exts.put( ext, ct );
            }
        }
    }

    private ContentType add( String name ) {
        ContentType ct = new ContentType( name );
        known.put( name, ct );
        return ct;
    }


}
