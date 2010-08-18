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
   private App app;
   public App getApp() {
      return app;
   }
   public void setApp( App app ) {
      this.app = app;
   }
    public String toString() {
       return "Module(ArtifactApp)";    }
}
