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
public class Deployed implements Kind {
   private String icon;
   private Artifact artifact;
   private List<Artifact> dependencies = new ArrayList<Artifact>();
   private List<Licence> licences = new ArrayList<Licence>();
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
