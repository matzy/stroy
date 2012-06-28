package org.openCage.lispaffair;

import java.util.ArrayList;
import java.util.List;

import org.openCage.lishp.Symbol;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;

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
 * Minimales Beispiel für die Verwendung des SAX-Parsers in Java. Die XML Datei wird
 * eingelesen und ordentlich formatiert auf der Konsole ausgegeben.
 * DefaultHandler ist eine abstrakte Klasse, die die Interfaces ContentHandler,
 * DTDHandler, EntityResolver und ErrorHandler implementiert.
 */
public class XMLReader extends DefaultHandler
{
    /**
     * Verschachtelungstiefe der Tags, wird verwendet, um das XML-Dokument
     * formatiert auszugeben.
     */
    private int level = 0;
    private List<List<Object>> stack = new ArrayList<List<Object>>();

    /**
     * Leerer Konstruktor, die Initialisierung des Parsers erfolgt in der
     * main-Methode.
     */
    public XMLReader()
    {
    }

    /**
     * Gibt <code>level</code> Tabs auf der Konsole aus.
     */
    public void indent()
    {
        // Mit Tabs einrücken
        for (int i=0;i<level;i++)
            System.out.print("\t");
    }

    /**
     * Wird am Anfang des Dokuments aufgerufen, definiert im Interface ContentHandler.
     */
    public void startDocument() throws SAXException
    {
        //System.out.println("Start des Dokuments");
        stack.add( new ArrayList<Object>());



    }

    /**
     * Wird bei jedem öffnenden Tag aufgerufen, definiert im Interface ContentHandler.
     * Bei leeren Tags wie zum Beispiel &lt;img /&gt; wird startElement und
     * endElement direkt hintereinander aufgerufen. Mit J2SE 1.4.2 scheint nur
     * qName gefüllt zu sein.
     *
     * @param namespaceURI URI des Namespaces für dieses Element, kann auch ein leerer String sein.
     * @param localName Lokaler Name des Elements, kann auch ein leerer String sein.
     * @param qName Qualifizierter Name (mit Namespace-Prefix) des Elements.
     * @param atts Liste der Attribute.
     */
    public void startElement(String namespaceURI, String localName,
            String qName, Attributes atts) throws SAXException
    {
        indent();

        List<Object> elem = new ArrayList<Object>();
        stack.add( elem );

        elem.add( Symbol.get( qName ));

        System.out.print("<" + qName);

        // Test-Code um zu sehen, was in namespaceURI und localName steht
        // System.out.print(" " + namespaceURI);
        // System.out.print(" " + localName);

        // Attribute ausgeben
        for( int i = 0; i < atts.getLength(); i++ ) {
            System.out.print(" " + atts.getQName(i) + "=\"" + atts.getValue(i) + "\"");
            List<Object> atti = new ArrayList<Object>();
            atti.add( Symbol.get(atts.getQName(i)));
            atti.add( atts.getValue(i) ); // string

            elem.add( atti );
        }

        System.out.println(">");

        level++;
    }

    /**
     * Wird bei jedem schließenden Tag aufgerufen, definiert im Interface ContentHandler.
     *
     * @param namespaceURI URI des Namespaces für dieses Element, kann auch ein leerer String sein.
     * @param localName Lokaler Name des Elements.
     * @param qName Qualifizierter Name des Elements.
     */
    public void endElement(String namespaceURI, String localName, String qName)
    {
        level--;

        indent();

        System.out.println("</" + qName + ">");

        stack.get( stack.size() -2 ).add(stack.get(stack.size() - 1));

        stack.remove( stack.size() - 1 );
    }

    /**
     * Wird immer aufgerufen, wenn Zeichen im Dokument auftauchen.
     *
     * @param ch Character Array
     * @param start Startindex der Zeichen in ch
     * @param length Länge der Zeichenkette
     */
    public void characters(char ch[], int start, int length)
    {
        String s = new String(ch,start,length).trim();
        if (s.length() > 0) {
            indent();
            System.out.println(s);
        }
    }

    /**
     * Wird aufgerufen, wenn Leerraum (" ", "\t", "\n", "\r") im Dokument
     * auftaucht, der für die Struktur des Dokuments nicht von Bedeutung ist.
     *
     * @param ch Character Array
     * @param start Startindex der Zeichen in ch
     * @param length Länge der Zeichenkette
     */
    public void ignorableWhitespace(char[] ch, int start, int length)
    {
    }

    public static void main(String args[])
    {
        try {
            // Neuen SAX-Parser erzeugen
            SAXParserFactory factory   = SAXParserFactory.newInstance();
            SAXParser        saxParser = factory.newSAXParser();

            // XML Datei parsen, die entsprechenden Methoden des DefaultHandler
            // werden als Callback aufgerufen.
            XMLReader reader = new XMLReader();
            saxParser.parse("/Users/stephan/tt.xml",reader);

            System.out.println(reader.stack);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Object readTags() {
        try {
            // Neuen SAX-Parser erzeugen
            SAXParserFactory factory   = SAXParserFactory.newInstance();
            SAXParser        saxParser = factory.newSAXParser();

            // XML Datei parsen, die entsprechenden Methoden des DefaultHandler
            // werden als Callback aufgerufen.
            XMLReader reader = new XMLReader();
            saxParser.parse("/Users/stephan/tt.xml",reader);

            return reader.stack;
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
}