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
public class LocalizationArtifact implements ArtifactProvider {

    private final Project proj;
    private final Artifact localization;

    public LocalizationArtifact() {
        proj = new PropertyArtifact().getProject();

        localization = proj.module( "openCage", "localization" ).
                address( "http://stroy.wikidot.com", "stroy.wikidot.com" ).
                version( "0.0.3").
                mpl11().                
                depends( proj.get("openCage", "property")).
                testDepends( proj.get( "junit", "junit"));
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
