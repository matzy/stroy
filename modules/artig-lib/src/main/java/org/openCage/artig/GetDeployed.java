package org.openCage.artig;

import org.openCage.artig.stjx.ArtifactDescription;
import org.openCage.artig.stjx.ArtifactDescriptionFromXML;
import org.openCage.artig.stjx.Deployed;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class GetDeployed {
    private String name;

    public GetDeployed( String name ) {
        this.name = name;
    }

    public InputStream getIS() {
        return getClass().getResourceAsStream( "/" + name + "-deployed.artig" );
    }

    public Deployed get() {

        InputStream is = getClass().getResourceAsStream( "/" + name + "-deployed.artig" );

        if ( is == null ) {
            throw new IllegalStateException("this module/jar does not have a <name>-deployed.artig file, i.e. not a artig project");
        }

        ArtifactDescriptionFromXML from = new ArtifactDescriptionFromXML();

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


        ArtifactDescription ad = (ArtifactDescription)from.getGoal();

        if ( !(ad.getKind() instanceof Deployed )) {
            throw new IllegalArgumentException("wrong kind of ArtifactDescription, expected Deployed) got " + ad.getKind().getClass().getName());
        }


        //((Deployed)ad.getKind()).getArtifact().setDescription( clazz.getResource("/deployed.artig").toString() );        

        return (Deployed)ad.getKind();
    }
}
