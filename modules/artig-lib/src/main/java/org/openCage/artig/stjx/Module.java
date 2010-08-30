package org.openCage.artig.stjx;

import java.util.ArrayList;
import java.util.List;
public class Module implements Kind {
   private Artifact artifact;
   private App app;
   public Artifact getArtifact(  ){
      return artifact;
   }
   public void setArtifact( Artifact artifact ){
      this.artifact = artifact;
   }
   public App getApp(  ){
      return app;
   }
   public void setApp( App app ){
      this.app = app;
   }

}
