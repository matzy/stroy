package org.openCage;

import org.openCage.lang.LangArtifact;
import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Apr 19, 2010
 * Time: 5:17:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class IOArtifact implements ArtifactProvider{
    private final Project proj;
    private final Artifact io;

    public IOArtifact() {
        proj = new LangArtifact().getProject();

        io = proj.module( getClass(), "openCage", "io").
                address( "http://stroy.wikidot.com", "stroy.wikidot.com" ).
                version( "0.0.3").
                mpl11().
                depends( proj.get("openCage", "lang")).
                depends( proj.get("commons-lang", "commons-lang" ) ).
                depends( proj.external( "com.google.inject", "guice" ).
                        apache2().
                        java6p().
                        descriptionShort( "Google dependency injection lib in pure java.").
                        address( "http://code.google.com/p/google-guice/", "code.google" ).
                        version( "2.0" )).
                depends( proj.external( "munchsoft", "sys" ).
                        version("1.0").
                        descriptionShort( "Tool to help make java apps behave and look like native osx apps. e.g. Menus in menu bar, Preferences open on command-, ... " ).
                        address( "http://www.muchsoft.com/", "munchsoft.com" ).
                        openIfUnchanged()
                        ).
                testDepends( proj.get( "junit", "junit"));
    }


    @Override
    public Artifact getArtifact() {
        return io;
    }

    @Override
    public Project getProject() {
        return proj;
    }
}
