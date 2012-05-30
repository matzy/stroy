package org.openCage.comphy;

import org.openCage.io.IOUtils;
import org.openCage.io.fspath.FSPathBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLtoReadable extends DefaultHandler {

    private static Logger LOG = Logger.getLogger(XMLtoReadable.class.getName());

    private RMap map;

    public Stack<Readable> stack;

    @Override
    public void startDocument() throws SAXException {
        stack = new Stack<Readable>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ( map == null ) {
            if ( qName.equals("comphy")) {
                map = new RMap();
                stack.push( map );
                return;
            } else {
                throw new IllegalArgumentException("not a comphy xml");
            }
        }

        Object top = stack.peek();

        if (top instanceof RString) {
            stack.pop();
        }

        stack.push( Key.valueOf(qName));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if ( qName.equals("comphy")) {
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

        // now add the elment to the previous structure

        if ( stack.peek() instanceof RMap ) {

            RMap map = (RMap)stack.peek();

            if ( map.containsKey(key)) {
                // its a list
                stack.pop();
                RList list = new RList(key);
                list.add(map.get(key));
                list.add(elem);
                stack.push(list);

            } else {
                map.put( key, elem );
            }

        } else if ( stack.peek() instanceof Key ) {
            RMap map = new RMap();
            stack.push(map);
            map.put( key , elem );

        } else {

            ((RList)stack.peek()).add(elem);
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        // sax parser might call 'characters' more than once to parse one string
        // => collect it

        // but it does automatically replace &lt; with < ...

        String str =  new String(ch,start,length);

        if ( stack.peek() instanceof RString) {
            String old = stack.pop().toString();
            str = old + str;
        }

        stack.push( new RString(str));
    }

    public Readable read(String uri) {
        try {
            // Neuen SAX-Parser erzeugen
            SAXParserFactory factory   = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // XML Datei parsen, die entsprechenden Methoden des DefaultHandler
            // werden als Callback aufgerufen.
//            XMLtoReadable reader = new XMLtoReadable();
            saxParser.parse(uri,this);

            return map;
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
            return null;
        }

    }

}
