package org.openCage.artig;

import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;


public class IO implements ArtifactProvider {
    private final Project proj;
    private final Artifact io;

    public IO() {
        proj = new Lang().getProject();

        io = proj.module( getClass(), "openCage", "openCage-io").
                address( "http://stroy.wikidot.com", "stroy.wikidot.com" ).
                version( "0.1.0").
                mpl11().
                depends( proj.get("openCage", "openCage-lang")).
                depends( proj.get("commons-lang", "commons-lang" ) ).
                depends( proj.external( "munchsoft", "sys" ).
                        version("1.0").
                        descriptionShort( "Tool to help make java apps behave and look like native osx apps. e.g. Menus in menu bar, Preferences open on command-, ... " ).
                        address( "http://www.muchsoft.com/", "munchsoft.com" ).
                        licence( "MuchSoft" )).
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
