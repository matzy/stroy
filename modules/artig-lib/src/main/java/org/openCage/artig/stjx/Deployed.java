package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class Deployed implements Kind  {
   private Artifact artifact;
   public Artifact getArtifact() {
      return artifact;
   }
   public void setArtifact( Artifact artifact ) {
      this.artifact = artifact;
   }
   private List<Artifact>  externals = new ArrayList<Artifact>();
   public List<Artifact> getExternals() {
      return externals;
   }
   private List<Licence>  licences = new ArrayList<Licence>();
   public List<Licence> getLicences() {
      return licences;
   }
    public String toString() {
       return "Deployed(Artifactexternalslicences)";    }
}
