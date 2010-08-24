package org.openCage.artig;

import org.apache.commons.io.IOUtils;
import org.openCage.artig.stjx.ArtifactDescription;
import org.openCage.artig.stjx.Deployed;
import org.openCage.artig.stjx.FromXML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class GetDeployed {
    private Class clazz;

    public GetDeployed( Class clazz ) {
        this.clazz = clazz;
    }

    public InputStream getIS() {
        System.out.println( clazz.getResource("."));

        return clazz.getResourceAsStream( "/deployed.artig" );
    }

    public Deployed get() {

        InputStream is = clazz.getResourceAsStream( "/deployed.artig" );

        if ( is == null ) {
            throw new IllegalStateException("this module/jar does not have a deployed.artig file, i.e. not a artig project");
        }

        FromXML from = new FromXML();

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
            IOUtils.closeQuietly( is );            
        }


        ArtifactDescription ad = (ArtifactDescription)from.getGoal();

        if ( !(ad.getKind() instanceof Deployed )) {
            throw new IllegalArgumentException("wrong kind of ArtifactDescription, expected Deployed) got " + ad.getKind().getClass().getName());
        }

        return (Deployed)ad.getKind();
    }
}
