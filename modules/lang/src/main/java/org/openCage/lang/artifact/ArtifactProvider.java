package org.openCage.lang.artifact;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Apr 19, 2010
 * Time: 10:57:08 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ArtifactProvider {

    public Artifact getArtifact();
    public Project  getProject();
}
