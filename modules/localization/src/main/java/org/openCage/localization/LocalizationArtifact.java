package org.openCage.localization;

import org.openCage.IOArtifact;
import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;
import org.openCage.property.PropertyArtifact;

public class LocalizationArtifact implements ArtifactProvider {

    private final Project proj;
    private final Artifact localization;

    public LocalizationArtifact() {
        proj = new PropertyArtifact().getProject();
        proj.include( new IOArtifact().getProject());

        localization = proj.module( getClass(), "openCage", "openCage-localization" ).
                address( "http://stroy.wikidot.com", "stroy.wikidot.com" ).
                version( "0.0.3").
                mpl11().
                depends( proj.get("openCage", "openCage-property")).
                testDepends( proj.get("openCage", "openCage-io")).
                testDepends( proj.get( "junit", "junit")).
                testDepends( proj.external( "com.google.inject", "guice" ).
                        apache2().
                        java6p().
                        descriptionShort( "Google dependency injection lib in pure java.").
                        address( "http://code.google.com/p/google-guice/", "code.google" ).
                        version( "2.0" ).
                        depends( proj.external( "aopalliance", "aopalliance" ).
                            version( "1.0" ).
                            address( "http://aopalliance.sourceforge.net/", "sourceforge.net" ).
                            publicDomain()));

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
