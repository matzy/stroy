package org.openCage.artig;

import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;

public class StroyArtifact implements ArtifactProvider {
    private Project proj;

    public StroyArtifact() {
        proj = Project.get( "stroy" );
        proj.include( new Lang().getProject());
        proj.include( new IO().getProject());
        proj.include( new Fausterize().getProject());
        proj.include( new Osashosa().getProject() );
        proj.include( new IO().getProject());
        proj.include( new Localization().getProject());
        proj.include( new Artig().getProject() );

    }

    @Override
    public Artifact getArtifact() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Project getProject() {
        return proj;
    }
}
