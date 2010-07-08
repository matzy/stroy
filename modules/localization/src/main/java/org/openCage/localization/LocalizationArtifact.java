package org.openCage.localization;

import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;
import org.openCage.property.PropertyArtifact;

public class LocalizationArtifact implements ArtifactProvider {

    private final Project proj;
    private final Artifact localization;

    public LocalizationArtifact() {
        proj = new PropertyArtifact().getProject();

        localization = proj.module( getClass(), "openCage", "openCage-localization" ).
                address( "http://stroy.wikidot.com", "stroy.wikidot.com" ).
                version( "0.0.3").
                mpl11().
                depends( proj.get("openCage", "openCage-property")).
//                testDepends( proj.module( getClass(), "openCage", "openCage-io").
//                    address( "http://stroy.wikidot.com", "stroy.wikidot.com" ).
//                    version( "0.1.0").
//                    mpl11().
//                    depends( proj.get("openCage", "openCage-lang")).
//                    depends( proj.get("commons-lang", "commons-lang" ) ).
//                    depends( proj.external( "munchsoft", "sys" ).
//                            version("1.0").
//                            descriptionShort( "Tool to help make java apps behave and look like native osx apps. e.g. Menus in menu bar, Preferences open on command-, ... " ).
//                            address( "http://www.muchsoft.com/", "munchsoft.com" ).
//                            licence( "MuchSoft" ))).
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
