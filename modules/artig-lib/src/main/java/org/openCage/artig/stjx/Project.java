package org.openCage.artig.stjx;

import java.util.ArrayList;
import java.util.List;

/**
 * ** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * <p/>
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * <p/>
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * <p/>
 * The Original Code is stroy code
 * <p/>
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 * <p/>
 * Contributor(s):
 * **** END LICENSE BLOCK ****
*/
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
   public String toString(  ){
      return ArtifactDescriptionToXML.toStringProject( "", this );
   }

}
