package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class TopLevel implements Kind  {
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
       return "TopLevel(modulesexternalslicences)";    }
}
