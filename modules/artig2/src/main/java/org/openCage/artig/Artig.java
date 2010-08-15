package org.openCage.artig;

import org.openCage.artig.stjx.Artifact;
import org.openCage.artig.stjx.ArtifactDescription;
import org.openCage.artig.stjx.ArtifactRef;
import org.openCage.artig.stjx.FromXML;
import org.openCage.artig.stjx.Module;
import org.openCage.artig.stjx.ModuleRef;
import org.openCage.artig.stjx.Project;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 14, 2010
 * Time: 8:23:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class Artig {
    private FSPath base;
    private Project project;
    private List<Module> modules = new ArrayList<Module>();

    public static void main(String[] args) {

        Artig artig = new Artig( FSPathBuilder.getPath("/Users/stephan/projects/stroy-7/" ));

        artig.readModules();

        artig.validate();

    }

    private void validate() {
        for ( Artifact ext : project.getExternals() ) {
            isValid( ext );
        }

        for ( Module mod : modules ) {
            isValid( mod.getArtifact() );
        }
    }


    public Artig( FSPath base ) {
        this.base = base;
        project = (Project)(read( base.add( "stroy.artig" )).getKind());
    }

    public void readModules() {
        for (ModuleRef mod : project.getModules() ) {

            System.out.println("Reading Module Description " + mod.getName() );

            Module module = (Module)read( base.add( "modules", mod.getName(), mod.getName() + ".artig")).getKind();
            modules.add( module );
            System.out.println( module );
        }
    }

    public void writePoms() {

    }

    public void isValid( Artifact arti ) {
        // check artirefs

        for ( ArtifactRef ref : arti.getDepends() ) {
            if ( find( ref) == null ) {
                throw new IllegalArgumentException( "Reference " + ref + " is not defined in the project" );
            }
        }
    }

    public Artifact find( ArtifactRef ref ) {
        for ( Artifact ext : project.getExternals() ) {
            if ( is( ext, ref )) {
                return ext;
            }
        }

        for ( Module mod : modules ) {
            if ( is( mod.getArtifact(), ref )) {
                return mod.getArtifact();
            }
        }

        return null;
    }

    public boolean is( Artifact arti, ArtifactRef ref ) {
        return arti.getName().equals( ref.getName() ) &&
                arti.getGroupId().equals( ref.getGroupId());
    }

    public ArtifactDescription read( FSPath path ) {
        FromXML from = new FromXML();

        // create factory
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // set it so that it is validating and namespace aware
        factory.setValidating( true );
        factory.setNamespaceAware( true );

        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse( path.toFile(), from );
        }
        catch( IOException ioe ) {
            ioe.printStackTrace();
        }
        catch( SAXException saxe ) {
            saxe.printStackTrace();
        }
        catch( ParserConfigurationException pce ) {
            pce.printStackTrace();
        }

        return (ArtifactDescription)from.getGoal();

    }
}
