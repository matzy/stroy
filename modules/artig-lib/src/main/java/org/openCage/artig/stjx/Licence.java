package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class Licence {
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
   private Address address;
   public Address getAddress() {
      return address;
   }
   public void setAddress( Address address ) {
      this.address = address;
   }
   private List<LicenceRef>  positives = new ArrayList<LicenceRef>();
   public List<LicenceRef> getPositives() {
      return positives;
   }
   private List<LicenceRef>  negatives = new ArrayList<LicenceRef>();
   public List<LicenceRef> getNegatives() {
      return negatives;
   }
    public String toString() {
       return "Licence(name: " + getName() +" version: " + getVersion() +" Addresspositivesnegatives)";    }
}
