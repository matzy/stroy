package org.openCage.stroy.algo.tree.contentType;

import java.util.Map;
import java.util.HashMap;
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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
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
