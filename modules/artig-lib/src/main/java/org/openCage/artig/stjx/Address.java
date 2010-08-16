package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class Address {
   private String page;
   public String getPage() {
      return page;
   }
   public void setPage( String page ) {
      this.page = page;
   }
   private String shrt;
   public String getShrt() {
      return shrt;
   }
   public void setShrt( String shrt ) {
      this.shrt = shrt;
   }
    public String toString() {
       return "Address(page: " + getPage() +" shrt: " + getShrt() +" )";    }
}
