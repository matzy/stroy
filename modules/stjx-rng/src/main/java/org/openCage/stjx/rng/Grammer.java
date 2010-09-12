package org.openCage.stjx.rng;

import java.util.ArrayList;
import java.util.List;

public class Grammer {
   private Start start;
   private List<Define> defineList = new ArrayList<Define>();
   public Start getStart(  ){
      return start;
   }
   public void setStart( Start start ){
      this.start = start;
   }
   public List<Define> getDefineList(  ){
      return defineList;
   }
   public void setDefineList( List<Define> defineList ){
      this.defineList = defineList;
   }

}
