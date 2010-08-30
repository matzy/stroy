package org.openCage.artig.stjx;

import java.util.ArrayList;
import java.util.List;
public class ArtifactDescription {
   private String version;
   private Kind kind;
   public String getVersion(  ){
      return version;
   }
   public void setVersion( String version ){
      this.version = version;
   }
   public Kind getKind(  ){
      return kind;
   }
   public void setKind( Kind kind ){
      this.kind = kind;
   }

}
