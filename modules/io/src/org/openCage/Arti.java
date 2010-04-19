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
public class Arti implements ArtifactProvider{
    private final Project proj;
    private final Artifact io;

    public Arti() {
        proj = new LangArtifact().getProject();

        io = proj.module( "openCage", "io").
                version( "0.0.3").
                depends( proj.get("openCage", "lang")).
                depends( proj.external( "munchsoft", "sys" ).
                        version("1.0"));
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
