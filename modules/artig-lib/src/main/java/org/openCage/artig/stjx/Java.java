package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class Java {
   private String min;
   public String getMin() {
      return min;
   }
   public void setMin( String min ) {
      this.min = min;
   }
   private String max;
   public String getMax() {
      return max;
   }
   public void setMax( String max ) {
      this.max = max;
   }
    public String toString() {
       return "Java(min: " + getMin() +" max: " + getMax() +" )";    }
}
