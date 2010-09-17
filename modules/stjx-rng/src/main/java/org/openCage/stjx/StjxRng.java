package org.openCage.stjx;

import org.openCage.stjx.rng.GrammerFromXML;
import org.openCage.stjx.rng.GrammerToXML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Sep 10, 2010
 * Time: 10:03:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class StjxRng {

    public static void main(String[] args) {
        InputStream is = StjxRng.class.getResourceAsStream( "/org/openCage/stjx/rng/MD.xml"  );

        if ( is == null ) {
            throw new IllegalStateException("file not found");
        }

        GrammerFromXML from = new GrammerFromXML();

        // create factory
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // set it so that it is validating and namespace aware
        factory.setValidating( true );
        factory.setNamespaceAware( true );

        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse( is, from );
        }
        catch( IOException ioe ) {
            ioe.printStackTrace();
        }
        catch( SAXException saxe ) {
            saxe.printStackTrace();
        }
        catch( ParserConfigurationException pce ) {
            pce.printStackTrace();
        } finally {
            if ( is != null ) {
                try {
                    is.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }

        //System.out.println( GrammerToXML.toStringGrammer( "", from.getGoal()));

        System.out.println( new GrammerToStjx().getStjx( from.getGoal() ).toString() );

    }

}
