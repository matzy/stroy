package org.openCage.localization;

import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;
import org.openCage.property.PropertyArtifact;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Apr 19, 2010
 * Time: 5:24:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Arti implements ArtifactProvider {

    private final Project proj;
    private final Artifact localization;

    public Arti() {
        proj = new PropertyArtifact().getProject();

        localization = proj.module( "openCage", "localization" ).
                depends( proj.get("openCage", "property"));
//                depends( proj.external( "com.google.inject", "guice" ).
//                        apache2().
//                        java6p().
//                        version( "2.0" )).
//                depends( proj.external("commons-io", "commons-io").
//                        apache2().
//                        version( "1.4" )
//                );
    }

    @Override
    public Artifact getArtifact() {
        return localization;
    }

    @Override
    public Project getProject() {
        return proj;
    }
}
