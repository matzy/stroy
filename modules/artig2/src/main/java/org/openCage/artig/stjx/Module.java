package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class Module implements Kind  {
   private Artifact artifact;
   public Artifact getArtifact() {
      return artifact;
   }
   public void setArtifact( Artifact artifact ) {
      this.artifact = artifact;
   }
    public String toString() {
       return "Module(Artifact)";    }
}
