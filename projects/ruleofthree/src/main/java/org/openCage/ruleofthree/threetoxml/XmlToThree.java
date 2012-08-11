package org.openCage.ruleofthree.threetoxml;

import org.openCage.io.IOUtils;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.inc.Null;
import org.openCage.ruleofthree.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static org.openCage.ruleofthree.Threes.THREE;


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

public class XmlToThree {

    private static Logger LOG = Logger.getLogger(XmlToThree.class.getName());


//    public Three read(String uri) {
//        return read("comphy", uri);
//    }


    public Three read( String title, String uri) {
        try {
            // Neuen SAX-Parser erzeugen
            SAXParserFactory factory   = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            XMLHandler xmlHandler = new XMLHandler( title );
            saxParser.parse(uri, xmlHandler );

            return THREE(xmlHandler.getMap());
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
            return Null.get( Three.class );
        }

    }

    public static class XMLHandler extends DefaultHandler {

        private ThreeMap<Three> map;
        private Stack<Three> stack = new Stack<Three>();
        private String title;

        public XMLHandler(String title) {
            this.title = title;
        }

        @Override
        public void startDocument() throws SAXException {}

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ( map == null ) {
                if ( qName.equals(title)) {
                    map = new ThreeHashMap<Three>();
                    stack.push( THREE(map) );
                    return;
                } else {
                    throw new IllegalArgumentException("not a comphy xml");
                }
            }

            Three top = stack.peek();

            if (top.isString()) {
                stack.pop();
            }

            stack.push( Key.valueOf(qName));

            // fix for unexpected sax reader behaviour
            // if start and end are close i.e. <foo></foo>
            // the character method is not called
            // but because it might be called several times for one string adding a empty string does not hurt
            stack.push( THREE(""));
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


            Three pstr = stack.pop();
            Three pkey = stack.peek();
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

            Three elem      = stack.pop();
            Three keyCheck  = stack.pop();

            if ( !key.equals( keyCheck )) {

                throw new IllegalArgumentException("malformed xml");
            }

            //String readKey = key.toString();

            // now add the element to the previous structure

            if ( stack.peek().isMap()) {

                Map<ThreeKey,Three> map = stack.peek().getMap();

                if ( map.containsKey(key)) {
                    // its a list
                    stack.pop();
                    List<Three> list = new ArrayList<Three>();
                    list.add(map.get(key));
                    list.add(elem);
                    stack.push(THREE(list));

                } else {
                    map.put( key.getSuper(), elem );
                }

            } else if ( stack.peek() instanceof Key ) {
                stack.push( Threes.newMap().put(key.getSuper(),elem));

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

            if ( stack.peek().isString()) {
                String old = stack.pop().toString();
                str = old + str;
            }

            stack.push( THREE(str));
        }


        public ThreeMap<Three> getMap() {
            return map;
        }
    }


    private static class Key extends ThreeKey implements Three{

        static private Pattern pattern = Pattern.compile("([A-Z]|[a-z]|\\-|\\.|_|[0-9])+");

        public Key( final String str ) {
            super(str);
            if ( !pattern.matcher(str).matches() ) {
                throw new IllegalArgumentException("not a legal key to be used as XML tag " + str );
            }
        }

        public static Key valueOf( String str ) {
            return new Key( str );
        }

        public ThreeKey getSuper() {
            return new ThreeKey(toString());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (toString()!= null ? !toString().equals(key.toString()) : key.toString() != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }


        @Override
        public boolean isString() {
            return false;
        }

        @Override
        public String getString() {
            throw new Error("not supported");
        }

        @Override
        public boolean isMap() {
            return false;
        }

        @Override
        public ThreeMap<Three> getMap() {
            throw new Error("not supported");
        }

        @Override
        public boolean isList() {
            return false;
        }

        @Override
        public List<Three> getList() {
            throw new Error("not supported");
        }

        @Override
        public Three put(ThreeKey key, Object val) {
            throw new Error("not supported");
        }

        @Override
        public Three put(String key, Object val) {
            throw new Error("not supported");
        }
    }
}
