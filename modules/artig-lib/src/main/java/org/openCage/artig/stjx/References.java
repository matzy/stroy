package org.openCage.artig.stjx;

import java.util.ArrayList;
import java.util.List;
public class References {
   private List<Artifact> references = new ArrayList<Artifact>( );
   public List<Artifact> getReferences(  ){
      return references;
   }
   public void setReferences( List<Artifact> references ){
      this.references = references;
   }

}
