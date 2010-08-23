package org.openCage.artig;

import org.apache.commons.io.IOUtils;
import org.openCage.artig.stjx.Artifact;
import org.openCage.artig.stjx.ArtifactDescription;
import org.openCage.artig.stjx.ArtifactRef;
import org.openCage.artig.stjx.Deployed;
import org.openCage.artig.stjx.Licence;
import org.openCage.artig.stjx.ModuleRef;
import org.openCage.artig.stjx.ToXML;
import org.openCage.io.FileUtils;
import org.openCage.io.fspath.FSPath;
import org.openCage.lang.errors.Unchecked;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 22, 2010
 * Time: 8:45:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class CalcDeployed {
    private Artig artig;

    public CalcDeployed( Artig artig) {
        this.artig = artig;
    }

    public Deployed calc( ModuleRef mod  ) {
        Deployed deployed = new Deployed();

        deployed.setArtifact( artig.get( mod ).getArtifact());

        addArtifactInfo( deployed, deployed.getArtifact());

        return deployed;
    }

    private void addArtifactInfo( Deployed deployed, Artifact arti ) {
        for (ArtifactRef ref : arti.getDepends() ) {
            if ( !included( deployed, ref ) ) {
                Artifact newArti = artig.find( ref );
                deployed.getDependencies().add( newArti );
                addArtifactInfo( deployed, newArti );
            }
        }

        for ( Licence licence : deployed.getLicences() ) {
            if ( licence.getName().equals( arti.getLicence() )) {
                return;
            }
        }

        deployed.getLicences().add( artig.findLicence( arti.getLicence() ));
    }

    private boolean included( Deployed deployed, ArtifactRef ref ) {
        for ( Artifact arti : deployed.getDependencies() ) {
            if ( Artig.is( arti, ref )) {
                return true;
            }
        }

        return false;
    }

    public void generate() {

        FSPath rootPom = artig.getRootPath().add( "dependencies.xml" );
        FileUtils.ensurePath( rootPom );

        FileWriter writer = null;
//        try {
//            writer = new FileWriter( rootPom.toFile() );
//
//            writer.write( deps());
//
//        } catch (IOException e) {
//            throw Unchecked.wrap( e );
//        } finally {
//            IOUtils.closeQuietly( writer );
//        }

          for ( ModuleRef mod : artig.getProject().getModules() ) {
            FSPath modPom = artig.getRootPath().add( "modules", mod.getName(), "src", "main", "resources", mod.getName() + "-deployed.artig" );
            FileUtils.ensurePath( modPom );

            try {
                writer = new FileWriter( modPom.toFile() );

                ArtifactDescription descr = new ArtifactDescription();
                descr.setKind( calc( mod ) );

                writer.write(ToXML.toStringArtifactDescription("", descr ));

            } catch (IOException e) {
                throw Unchecked.wrap( e );
            } finally {
                IOUtils.closeQuietly( writer );
            }

        }

    }

}
