package org.openCage.artig.stjx;

import java.util.ArrayList;
import java.util.List;
public class Project implements Kind {
   private String name;
   private String groupId;
   private List<ModuleRef> modules = new ArrayList<ModuleRef>();
   private List<Artifact> externals = new ArrayList<Artifact>();
   private List<Licence> licences = new ArrayList<Licence>();
   private List<ArtifactRef> dropIns = new ArrayList<ArtifactRef>();
   public String getName(  ){
      return name;
   }
   public void setName( String name ){
      this.name = name;
   }
   public String getGroupId(  ){
      return groupId;
   }
   public void setGroupId( String groupId ){
      this.groupId = groupId;
   }
   public List<ModuleRef> getModules(  ){
      return modules;
   }
   public void setModules( List<ModuleRef> modules ){
      this.modules = modules;
   }
   public List<Artifact> getExternals(  ){
      return externals;
   }
   public void setExternals( List<Artifact> externals ){
      this.externals = externals;
   }
   public List<Licence> getLicences(  ){
      return licences;
   }
   public void setLicences( List<Licence> licences ){
      this.licences = licences;
   }
   public List<ArtifactRef> getDropIns(  ){
      return dropIns;
   }
   public void setDropIns( List<ArtifactRef> dropIns ){
      this.dropIns = dropIns;
   }

}
