package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class ArtifactDescription {
   private String version;
   public String getVersion() {
      return version;
   }
   public void setVersion( String version ) {
      this.version = version;
   }
   private Kind kind;
   public Kind getKind() {
      return kind;
   }
   public void setKind( Kind kind ) {
      this.kind = kind;
   }
    public String toString() {
       return "ArtifactDescription(version: " + getVersion() +" Kind)";    }
}
