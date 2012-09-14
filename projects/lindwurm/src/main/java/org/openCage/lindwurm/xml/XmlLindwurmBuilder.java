package org.openCage.lindwurm.xml;

import org.openCage.kleinod.type.Null;
import org.openCage.lindwurm.Ignore;
import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
import org.openCage.lindwurm.LindwurmBuilder;
import org.openCage.lindwurm.SimpleTreeNode;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.InputStream;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/17/12
 * Time: 8:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class XmlLindwurmBuilder implements LindwurmBuilder  {
    private static final Logger LOG = Logger.getLogger(XmlLindwurmBuilder.class.getName());


    @Override
    public LindenNode build(Ignore ignore, File root) {

        try {
            // Neuen SAX-Parser erzeugen
            SAXParserFactory factory   = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            XmlHandler xmlHandler = new XmlHandler();
            saxParser.parse(root, xmlHandler );

            return xmlHandler.getRoot();
        }
        catch (Exception e) {
            LOG.log(Level.WARNING, "xml file corrupt ",  e);
        }

        return Null.of(LindenNode.class);

    }

    public LindenNode build(Ignore ignore, InputStream root) {

        try {
            // Neuen SAX-Parser erzeugen
            SAXParserFactory factory   = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            XmlHandler xmlHandler = new XmlHandler();
            saxParser.parse(root, xmlHandler );

            return xmlHandler.getRoot();
        }
        catch (Exception e) {
            LOG.log(Level.WARNING, "xml file corrupt ",  e);
        }

        return Null.of(LindenNode.class);

    }


    public static class XmlHandler extends DefaultHandler {


        private Stack<LindenDirNode> stack = new Stack<LindenDirNode>();
        private String txt = "";
        private String start;

        public LindenDirNode getRoot() {
            return root;
        }

        private LindenDirNode root;


        @Override
        public void startElement( String uri, String localName, String qName, Attributes attributes) throws SAXException {

            if ( stack.isEmpty() ) {
                root = SimpleTreeNode.makeDir(qName);
                stack.push(root);

            } else {

                if ( !Null.is(start)) {
                    LindenDirNode node = SimpleTreeNode.makeDir(qName);
                    stack.peek().dir().addChild( node );
                    stack.push(node);
                }

                start = qName;

                txt = "";
            }

        }

        @Override
        public void endElement(String s, String s1, String s2) throws SAXException {
            if ( !Null.is(start) ) {
                LindenNode textNode = new SimpleTreeNode( new TextContent( start, txt ));
                stack.peek().dir().addChild( textNode );
                start = null;
            } else {
                txt = "";
                stack.pop();
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {

            // sax parser might call 'characters' more than once to parse one string
            // => collect it

            // but it does automatically replace &lt; with < ...

            txt += new String(ch,start,length);

        }

    }


}
