package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class Author {
   private String name;
   public String getName() {
      return name;
   }
   public void setName( String name ) {
      this.name = name;
   }
   private String email;
   public String getEmail() {
      return email;
   }
   public void setEmail( String email ) {
      this.email = email;
   }
    public String toString() {
       return "Author(name: " + getName() +" email: " + getEmail() +" )";    }
}
