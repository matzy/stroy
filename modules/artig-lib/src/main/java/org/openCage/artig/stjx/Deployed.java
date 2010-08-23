package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class Deployed implements Kind  {
   private String icon;
   public String getIcon() {
      return icon;
   }
   public void setIcon( String icon ) {
      this.icon = icon;
   }
   private Artifact artifact;
   public Artifact getArtifact() {
      return artifact;
   }
   public void setArtifact( Artifact artifact ) {
      this.artifact = artifact;
   }
   private List<Artifact>  dependencies = new ArrayList<Artifact>();
   public List<Artifact> getDependencies() {
      return dependencies;
   }
   private List<Licence>  licences = new ArrayList<Licence>();
   public List<Licence> getLicences() {
      return licences;
   }
    public String toString() {
       return "Deployed(icon: " + getIcon() +" Artifactdependencieslicences)";    }
}
