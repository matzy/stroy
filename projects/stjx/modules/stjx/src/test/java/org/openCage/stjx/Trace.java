package org.openCage.stjx;

import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Arrays;

public class Trace extends DefaultHandler {

    int indent;

    void printIndent() {
        for (int i=0; i<indent; i++) System.out.print("   ");
    }

    public void startDocument() {
        System.out.println("start document");
    }

    public void endDocument() {
        System.out.println("end document");
    }

    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        printIndent();
        System.out.println("starting element: " + qName);
        indent++;
    }

    public void endElement(String uri, String localName,
                           String qName) {
        indent--;
        printIndent();
        System.out.println("end element: " + qName);
    }

    public void ignorableWhitespace(char[] ch, int start, int length) {
        printIndent();
        System.out.println("whitespace, length " + length);
    }

    public void processingInstruction(String target, String data) {
        printIndent();
        System.out.println("processing instruction: " + target);
    }

    public void characters(char[] ch, int start, int length){
        printIndent();
        System.out.println("character data, length " + length + " [" + toString( ch, start, length ) + "]");
    }

    public static void main(String[] args) {

        FSPath path = FSPathBuilder.getPath("/Users/stephan/tmp/stroy.artig");

        Trace t = new Trace();
        // create factory
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // set it so that it is validating and namespace aware
        factory.setValidating( true );
        factory.setNamespaceAware( true );

        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse( path.toFile(), t );
        }
        catch( IOException ioe ) {
            ioe.printStackTrace();
        }
        catch( SAXException saxe ) {
            saxe.printStackTrace();
        }
        catch( ParserConfigurationException pce ) {
            pce.printStackTrace();
        } catch ( IllegalArgumentException exp ) {
            System.err.println("Problem parsing " + path );
            exp.printStackTrace();
        }
    }

    /** Converts an array of char to a readable string (for debugging purpose).
     */
    public final static String toString(char[] array, int start, int length ) {
        if (array == null) {
            return "";
        }

        StringBuffer sb = new StringBuffer();

        for (int j = start; j < array.length && j < (start + length); ++j) {
            sb.append(array[j]);
        }
        return sb.toString();
    }
}