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
public class Licence {
   private String name;
   private String version;
   private Address address;
   private List<LicenceRef> positives = new ArrayList<LicenceRef>();
   private List<LicenceRef> negatives = new ArrayList<LicenceRef>();
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
   public Address getAddress(  ){
      return address;
   }
   public void setAddress( Address address ){
      this.address = address;
   }
   public List<LicenceRef> getPositives(  ){
      return positives;
   }
   public void setPositives( List<LicenceRef> positives ){
      this.positives = positives;
   }
   public List<LicenceRef> getNegatives(  ){
      return negatives;
   }
   public void setNegatives( List<LicenceRef> negatives ){
      this.negatives = negatives;
   }

}
