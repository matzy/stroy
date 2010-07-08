package org.openCage.artig;

import org.openCage.IOArtifact;
import org.openCage.gpad.FausterizeArtifact;
import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;
import org.openCage.osashosa.OsashosaArtifact;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 03.07.2010
 * Time: 11:05:34
 * To change this template use File | Settings | File Templates.
 */
public class StroyArtifact implements ArtifactProvider {
    private Project proj;

    public StroyArtifact() {
        proj = Project.get( "stroy" );
        proj.include( new FausterizeArtifact().getProject());
        proj.include( new OsashosaArtifact().getProject() );
        proj.include( new IOArtifact().getProject());
        proj.include( new FullLocalizationArtifact().getProject());

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
