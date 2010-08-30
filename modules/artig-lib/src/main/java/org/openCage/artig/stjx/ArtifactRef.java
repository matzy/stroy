package org.openCage.artig.stjx;

import java.util.ArrayList;
import java.util.List;
public class ArtifactRef {
   private String groupId;
   private String name;
   private String version;
   private String scope;
   public String getGroupId(  ){
      return groupId;
   }
   public void setGroupId( String groupId ){
      this.groupId = groupId;
   }
   public String getName(  ){
      return name;
   }
   public void setName( String name ){
      this.name = name;
   }
   public String getVersion(  ){
      return version;
   }
   public void setVersion( String version ){
      this.version = version;
   }
   public String getScope(  ){
      return scope;
   }
   public void setScope( String scope ){
      this.scope = scope;
   }

}
