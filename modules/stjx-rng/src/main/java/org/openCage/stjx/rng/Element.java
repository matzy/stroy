package org.openCage.stjx.rng;

import java.util.ArrayList;
import java.util.List;

public class Element {
   private String name;
   private List<ZeroOrMore> zeroOrMoreList = new ArrayList<ZeroOrMore>();
   private List<Optional> optionalList = new ArrayList<Optional>();
   private List<Attribute> attributeList = new ArrayList<Attribute>();
   private List<Choice> choiceList = new ArrayList<Choice>();
   public String getName(  ){
      return name;
   }
   public void setName( String name ){
      this.name = name;
   }
   public List<ZeroOrMore> getZeroOrMoreList(  ){
      return zeroOrMoreList;
   }
   public void setZeroOrMoreList( List<ZeroOrMore> zeroOrMoreList ){
      this.zeroOrMoreList = zeroOrMoreList;
   }
   public List<Optional> getOptionalList(  ){
      return optionalList;
   }
   public void setOptionalList( List<Optional> optionalList ){
      this.optionalList = optionalList;
   }
   public List<Attribute> getAttributeList(  ){
      return attributeList;
   }
   public void setAttributeList( List<Attribute> attributeList ){
      this.attributeList = attributeList;
   }
   public List<Choice> getChoiceList(  ){
      return choiceList;
   }
   public void setChoiceList( List<Choice> choiceList ){
      this.choiceList = choiceList;
   }

}
