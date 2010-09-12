package org.openCage.stjx.rng;

import java.util.ArrayList;
import java.util.List;

public class Attribute {
   private String name;
   private Text text;
   private Choice choice;
   public String getName(  ){
      return name;
   }
   public void setName( String name ){
      this.name = name;
   }
   public Text getText(  ){
      return text;
   }
   public void setText( Text text ){
      this.text = text;
   }
   public Choice getChoice(  ){
      return choice;
   }
   public void setChoice( Choice choice ){
      this.choice = choice;
   }

}
