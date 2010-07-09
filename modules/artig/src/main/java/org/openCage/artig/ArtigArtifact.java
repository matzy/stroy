package org.openCage.artig;

import net.jcip.annotations.Immutable;
import org.openCage.IOArtifact;
import org.openCage.lang.LangArtifact;
import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;

@Immutable
public class ArtigArtifact implements ArtifactProvider {

    private final Artifact lang;
    private final Project  proj;

    public ArtigArtifact() {
        proj = Project.get( "stroy" );
        proj.include( new LangArtifact().getProject());
        proj.include( new IOArtifact().getProject() );

        lang = proj.module( getClass(), "openCage", "openCage-artig" ).
                mpl11().
                java6().
                version( "0.0.1").
                author( proj.author( "Stephan Pfab" ).email( "mailto:openCag@gmail.com" )).
                email( "mailto:openCag@gmail.com" ).
                address( "http://stroy.wikidot.com", "wikidot.com").
                descriptionShort( "a library with small java language level additions" ).
                depends( proj.get( "openCage", "openCage-lang" )).
                depends( proj.get( "openCage", "openCage-io" ));



    }

    public Artifact getArtifact() {
        return lang;
    }

    public Project getProject() {
        return proj;
    }
}
