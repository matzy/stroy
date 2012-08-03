package org.openCage.comphy.readable;

import org.openCage.comphy.Readable;
import org.openCage.io.IOUtils;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.inc.GHashMap;
import org.openCage.lang.inc.GMap;
import org.openCage.lang.inc.Null;
import org.openCage.lang.inc.Str;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static org.openCage.comphy.Readables.R;
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

public class XMLtoReadable   {

    private static Logger LOG = Logger.getLogger(XMLtoReadable.class.getName());


    public org.openCage.comphy.Readable read(String uri) {
        return read("comphy", uri);
    }

    public Readable read( Str titel, String uri) {
        return read(titel.get(), uri);
    }

    public Readable read( String title, String uri) {
        try {
            // Neuen SAX-Parser erzeugen
            SAXParserFactory factory   = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            XMLHandler xmlHandler = new XMLHandler( title );
            saxParser.parse(uri, xmlHandler );

            return R(xmlHandler.getMap());
        }
        catch (Exception e) {
            LOG.log(Level.WARNING, "property file corrupt, using defaults", e);
            File file = new File( uri );
            if ( !file.exists()) {
                return null;
            }
            File back = FSPathBuilder.getTmpDir().add(file.getName()).toFile();
            IOUtils.ensurePath(back);
            LOG.warning("copying to " + back.getAbsolutePath());
            IOUtils.copy(file, back);
            return Null.get( Readable.class );
        }

    }

    public static class XMLHandler extends DefaultHandler {

        private GMap<Str,Readable> map;

        public Stack<Readable> stack;
        private String title;

        public XMLHandler(String title) {
            this.title = title;
        }

        @Override
        public void startDocument() throws SAXException {
            stack = new Stack<Readable>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ( map == null ) {
                if ( qName.equals(title)) {
                    map = new GHashMap<Str, Readable>();
                    stack.push( R(map));
                    return;
                } else {
                    throw new IllegalArgumentException("not a comphy xml");
                }
            }

            Readable top = stack.peek();

            if (top.isStr()) {
                stack.pop();
            }

            stack.push( Key.valueOf(qName));

            // fix for unexpected sax reader behaviour
            // if start and end are close i.e. <foo></foo>
            // the character method is not called
            // but because it might be called several times for one sting adding a empty string does not hurt
            stack.push( R(""));
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

            if ( qName.equals(title)) {
                // done
                return;
            }

            Key key = Key.valueOf(qName);

            // top of stack is always a rstring
            // but its only relevant if the next one is a key (must be the same a the current qName)


            Readable pstr = stack.pop();
            Readable pkey = stack.peek();
            if ( pkey instanceof Key ) {
                if ( !pkey.equals( key )) {
                    throw new IllegalArgumentException("illegal xml");
                }
                stack.push(pstr);
            }

            // no it should be |...readable, key, readable

            if ( stack.size() < 3 ) {
                throw new Error( "too small" );
            }

            Readable elem      = stack.pop();
            Readable keyCheck  = stack.pop();

            if ( !key.equals( keyCheck )) {
                throw new IllegalArgumentException("malformed xml");
            }

            Str readKey = S(key.get());

            // now add the element to the previous structure

            if ( stack.peek().isMap()) {

                GMap<Str,Readable> map = stack.peek().getMap();

                if ( map.containsKeyG(readKey)) {
                    // its a list
                    stack.pop();
                    List<Readable> list = new ArrayList<Readable>();
                    list.add(map.getG(readKey));
                    list.add(elem);
                    stack.push(R(list));

                } else {
                    map.put( readKey, elem );
                }

            } else if ( stack.peek() instanceof Key ) {
                GMap<Str,Readable> map = new GHashMap<Str, Readable>();
                stack.push(R(map));
                map.put( readKey , elem );

            } else {

                if ( !stack.peek().isList()) {
                    throw new IllegalStateException( "oops, expected a list not " + stack.peek() );
                }

                stack.peek().getList().add(elem);
            }

        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {

            // sax parser might call 'characters' more than once to parse one string
            // => collect it

            // but it does automatically replace &lt; with < ...

            String str =  new String(ch,start,length);

            if ( stack.peek().isStr()) {
                String old = stack.pop().toString();
                str = old + str;
            }

            stack.push( R(str));
        }


        public GMap<Str, Readable> getMap() {
            return map;
        }
    }


    private static class Key implements Comparable<Key>, Readable{

        static private Pattern pattern = Pattern.compile("([A-Z]|[a-z]|\\-|\\.|_|[0-9])+");

        private final String str;

        public Key( final String str ) {
            if ( !pattern.matcher(str).matches() ) {
                throw new IllegalArgumentException("not a legal key to be used as XML tag " + str );
            }
            this.str = str;
        }

        public String get() {
            return str;
        }

        @Override
        public int compareTo(Key o) {
            return str.compareTo(o.get());
        }

        @Override
        public String toString() {
            return str;
        }

        public static Key valueOf( String str ) {
            return new Key( str );
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (str != null ? !str.equals(key.str) : key.str != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return str != null ? str.hashCode() : 0;
        }

        public static Key valueOf(Str key) {
            return new Key( key.get());
        }

        @Override
        public boolean isStr() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Str getStr() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean isMap() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public GMap<Str, Readable> getMap() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean isList() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public List<Readable> getList() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Readable put(Str key, Object val) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
