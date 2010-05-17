package org.openCage.artig;

import net.jcip.annotations.Immutable;
import org.openCage.gpad.FausterizeArtifact;
import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;

@Immutable
public class ArtigArtifact implements ArtifactProvider {

    private final Artifact lang;
    private final Project  proj;

    public ArtigArtifact() {
        proj = new Project( "stroy" );
        proj.include( new FausterizeArtifact().getProject());

        lang = proj.module( getClass(), "openCage", "openCage-artig" ).
                mpl11().
                java6().
                version( "0.0.1").
                author( proj.author( "Stephan Pfab" ).email( "mailto:openCag@gmail.com" )).
                email( "mailto:openCag@gmail.com" ).
                address( "http://stroy.wikidot.com", "wikidot.com").
                descriptionShort( "a library with small java language level additions" ).
                depends( proj.get( "openCage", "openCage-fausterize" ));



    }

    public Artifact getArtifact() {
        return lang;
    }

    public Project getProject() {
        return proj;
    }
}
