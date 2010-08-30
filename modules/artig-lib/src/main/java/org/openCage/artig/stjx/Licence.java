package org.openCage.artig.stjx;

import java.util.ArrayList;
import java.util.List;
public class Licence {
   private String name;
   private String version;
   private Address address;
   private List<LicenceRef> positives = new ArrayList<LicenceRef>( );
   private List<LicenceRef> negatives = new ArrayList<LicenceRef>( );
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
