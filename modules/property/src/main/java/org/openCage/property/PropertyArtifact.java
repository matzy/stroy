package org.openCage.property;

import org.openCage.lang.LangArtifact;
import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Apr 19, 2010
 * Time: 11:01:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class PropertyArtifact implements ArtifactProvider{

    private final Project proj;
    private final Artifact property;

    public PropertyArtifact() {
        proj = new LangArtifact().getProject();

        property = proj.module( "openCage", "property" ).
                version( "0.1.0" ).
                address( "http://stroy.wikidot.org", "stroy.wikidot.org" ).
                mpl11().
                depends( proj.get("openCage", "lang")).
                depends( proj.external( "com.google.inject", "guice" ).
                        apache2().
                        java6p().
                        descriptionShort( "Google dependency injection lib in pure java.").
                        address( "http://code.google.com/p/google-guice/", "code.google" ).
                        version( "2.0" )).
                depends( proj.external("commons-io", "commons-io").
                        apache2().
                        descriptionShort( "Apache IO tools. e.g. tools to cleanly close resources." ).
                        address( "http://commons.apache.org/io", "apache").
                        version( "1.4" )
                );
    }

    @Override
    public Artifact getArtifact() {
        return property;
    }

    @Override
    public Project getProject() {
        return proj;
    }
}
