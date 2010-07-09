package org.openCage.artig;

import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;

public class Localization implements ArtifactProvider {

    private final Project proj;
    private final Artifact localization;

    public Localization() {
        proj = new Property().getProject();

        localization = proj.module( getClass(), "openCage", "openCage-localization" ).
                address( "http://stroy.wikidot.com", "stroy.wikidot.com" ).
                version( "0.0.3").
                mpl11().
                depends( proj.get("openCage", "openCage-property")).
                depends( proj.external( "com.google.inject", "guice" ).
                        apache2().
                        java6p().
                        descriptionShort( "Google dependency injection lib in pure java.").
                        address( "http://code.google.com/p/google-guice/", "code.google" ).
                        version( "2.0" ).
                        depends( proj.external( "aopalliance", "aopalliance" ).
                            version( "1.0" ).
                            address( "http://aopalliance.sourceforge.net/", "sourceforge.net" ).
                            publicDomain())).
                testDepends( proj.get( "junit", "junit" ));

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
