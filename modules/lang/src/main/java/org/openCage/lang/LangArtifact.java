package org.openCage.lang;

import net.jcip.annotations.Immutable;
import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;

@Immutable
public class LangArtifact implements ArtifactProvider {

    private final Artifact lang;
    private final Project  proj;

    public LangArtifact() {
        proj = new Project( "stroy" );

        lang = proj.module( "openCage", "lang" ).
                mpl11().
                java6p().
                version( "0.1.0").
                author( proj.author( "Stephan Pfab" ).email( "mailto:openCag@gmail.com" )).
                email( "mailto:openCag@gmail.com" ).
                address( "http://stroy.wikidot.org", "wikidot.com").
                descriptionShort( "a library with small java language level additions" ).
                depends( proj.external("com.intellij", "annotations" ).
                        descriptionShort( "Nullable annotations (bundled with Idea CE)" ).
                        address( "http://www.jetbrains.com/index.html", "jetbrains").
                        apache2().
                        version( "7.0.3")).
                depends( proj.external("net.jcip", "jcip-annotations" ).
                        version( "1.0" ));



    }

    public Artifact getArtifact() {
        return lang;
    }

    public Project getProject() {
        return proj;
    }
}
