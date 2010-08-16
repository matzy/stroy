package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class ArtifactRef {
   private String groupId;
   public String getGroupId() {
      return groupId;
   }
   public void setGroupId( String groupId ) {
      this.groupId = groupId;
   }
   private String name;
   public String getName() {
      return name;
   }
   public void setName( String name ) {
      this.name = name;
   }
   private String version;
   public String getVersion() {
      return version;
   }
   public void setVersion( String version ) {
      this.version = version;
   }
   private String scope;
   public String getScope() {
      return scope;
   }
   public void setScope( String scope ) {
      this.scope = scope;
   }
    public String toString() {
       return "ArtifactRef(groupId: " + getGroupId() +" name: " + getName() +" version: " + getVersion() +" scope: " + getScope() +" )";    }
}
