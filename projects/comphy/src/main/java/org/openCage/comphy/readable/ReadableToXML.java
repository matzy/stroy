package org.openCage.comphy.readable;

import org.openCage.comphy.*;
import org.openCage.comphy.Readable;
import org.openCage.lang.Strings;
import org.openCage.lang.inc.GMap;
import org.openCage.lang.inc.Str;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.openCage.lang.inc.Strng.S;

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

public class ReadableToXML {

    public  String write( Str title, GMap<Str, org.openCage.comphy.Readable> map ) {
        StringBuilder builder = new StringBuilder();
        write( builder, title, "", map );
        return builder.toString();
    }


    public String write(Str key, Readable readable) {
        StringBuilder builder = new StringBuilder();
        writeReadable( builder, "", key, readable );
        return builder.toString();
    }

    private void write(StringBuilder builder, Str title, String pre, GMap<Str,Readable> map) {

        if ( !XMLUtils.isNameOk(title) ) {
            throw new IllegalArgumentException( "not an accepted XML element/attribute name " + title );
        }

        builder.append(pre + "<" + title + ">\n");

        // sort
        List<Str> keys = new ArrayList<Str>( map.keySet());
        Collections.sort(keys);


        for ( Str key : keys ) {

            Readable val = map.getG(key);

            writeReadable(builder, pre + "  ", key, val);

        }

        builder.append(pre + "</" + title + ">\n");
    }

    private void writeReadable(StringBuilder builder, String pre, Str key, Readable val) {
        if ( val.isStr()) {
            if ( !XMLUtils.isNameOk(key) ) {
                throw new IllegalArgumentException( "not an accepted XML element/attribute name: " + key );
            }
            builder.append(pre + "<" + key + ">" + XMLUtils.escapeXmlEntities(val.getStr().get()) + "</" + key + ">\n");
        } else if (val.isMap() ) {
            write(builder, key, pre, val.getMap());
        } else if (val.isList() ) {
            write(builder, key, pre, val.getList());
        } else {
            throw new Error("impl me");
        }
    }


    private void write(StringBuilder builder, Str title, String pre, List<Readable> list) {
        if ( !XMLUtils.isNameOk(title) ) {
            throw new IllegalArgumentException( "not an accepted XML element/attribute name " + title );
        }
        builder.append(pre + "<" + title + ">\n");
        Str elementKey = toElementKey(title);
        for ( Readable elem : list ) {

            writeReadable( builder, pre + "  ", elementKey, elem );
        }
        builder.append(pre + "</" + title + ">\n");
    }

    protected Str toElementKey( Str str ) {
        if ( str.endsWith("s")) {
            return str.substring(0, str.size() - 1);
        }

        return S( str + "-element");
    }


}
