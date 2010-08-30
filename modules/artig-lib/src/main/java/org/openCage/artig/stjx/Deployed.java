package org.openCage.artig.stjx;

import java.util.ArrayList;
import java.util.List;
public class Deployed implements Kind {
   private String icon;
   private Artifact artifact;
   private List<Artifact> dependencies = new ArrayList<Artifact>( );
   private List<Licence> licences = new ArrayList<Licence>( );
   public String getIcon(  ){
      return icon;
   }
   public void setIcon( String icon ){
      this.icon = icon;
   }
   public Artifact getArtifact(  ){
      return artifact;
   }
   public void setArtifact( Artifact artifact ){
      this.artifact = artifact;
   }
   public List<Artifact> getDependencies(  ){
      return dependencies;
   }
   public void setDependencies( List<Artifact> dependencies ){
      this.dependencies = dependencies;
   }
   public List<Licence> getLicences(  ){
      return licences;
   }
   public void setLicences( List<Licence> licences ){
      this.licences = licences;
   }

}
