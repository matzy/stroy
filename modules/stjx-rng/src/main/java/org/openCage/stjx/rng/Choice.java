package org.openCage.stjx.rng;

import java.util.ArrayList;
import java.util.List;

public class Choice {
   private List<String> valueList = new ArrayList<String>();
   private List<Ref> refList = new ArrayList<Ref>();
   public List<String> getValueList(  ){
      return valueList;
   }
   public void setValueList( List<String> valueList ){
      this.valueList = valueList;
   }
   public List<Ref> getRefList(  ){
      return refList;
   }
   public void setRefList( List<Ref> refList ){
      this.refList = refList;
   }

}
