package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class Project implements Kind  {
   private String name;
   public String getName() {
      return name;
   }
   public void setName( String name ) {
      this.name = name;
   }
   private String groupId;
   public String getGroupId() {
      return groupId;
   }
   public void setGroupId( String groupId ) {
      this.groupId = groupId;
   }
   private List<ModuleRef>  modules = new ArrayList<ModuleRef>();
   public List<ModuleRef> getModules() {
      return modules;
   }
   private List<Artifact>  externals = new ArrayList<Artifact>();
   public List<Artifact> getExternals() {
      return externals;
   }
   private List<Licence>  licences = new ArrayList<Licence>();
   public List<Licence> getLicences() {
      return licences;
   }
    public String toString() {
       return "Project(name: " + getName() +" groupId: " + getGroupId() +" modulesexternalslicences)";    }
}
