package org.openCage.stjx.rng;

import java.util.List;

public class GrammerToXML {
   public static  String toStringDefine( String prefix, Define define ){
      String ret = prefix;
      ret += "<define ";
      if( define.getName() != null ){
         ret += "name=\"" + define.getName() + "\" ";
      }
      ret += ">\n";
      if( define.getElement() != null ){
         ret += toStringElement( prefix + "   ", define.getElement() );
      }
      ret += prefix + "</define>\n";
      return ret;
   }
   public static  String toStringElement( String prefix, Element element ){
      String ret = prefix;
      ret += "<element ";
      if( element.getName() != null ){
         ret += "name=\"" + element.getName() + "\" ";
      }
      ret += ">\n";
      if( element.getZeroOrMoreList() != null ){
         ret += toStringZeroOrMoreList( prefix + "   ", element.getZeroOrMoreList() );
      }
      if( element.getOptionalList() != null ){
         ret += toStringOptionalList( prefix + "   ", element.getOptionalList() );
      }
      if( element.getAttributeList() != null ){
         ret += toStringAttributeList( prefix + "   ", element.getAttributeList() );
      }
      if( element.getChoiceList() != null ){
         ret += toStringChoiceList( prefix + "   ", element.getChoiceList() );
      }
      ret += prefix + "</element>\n";
      return ret;
   }
   public static  String toStringOptionalList( String prefix, List<Optional> optional ){
      String ret = "";
      for ( Optional vr : optional ) {
         ret += toStringOptional( prefix + "   ", vr );
      }
      return ret;
   }
   public static  String toStringText( String prefix, Text text ){
      String ret = prefix;
      ret += "<text ";
      ret += "/>\n";
      return ret;
   }
   public static  String toStringChoice( String prefix, Choice choice ){
      String ret = prefix;
      ret += "<choice ";
      ret += ">\n";
      if( choice.getValueList() != null ){
         ret += toStringValueList( prefix + "   ", choice.getValueList() );
      }
      if( choice.getRefList() != null ){
         ret += toStringRefList( prefix + "   ", choice.getRefList() );
      }
      ret += prefix + "</choice>\n";
      return ret;
   }
   public static  String toStringZeroOrMoreList( String prefix, List<ZeroOrMore> zeroOrMore ){
      String ret = "";
      for ( ZeroOrMore vr : zeroOrMore ) {
         ret += toStringZeroOrMore( prefix + "   ", vr );
      }
      return ret;
   }
   public static  String toStringValueList( String prefix, List<String> value ){
      String ret = "";
      for ( String vr : value ) {
         ret += toStringValue( prefix + "   ", vr );
      }
      return ret;
   }
   public static  String toStringAttribute( String prefix, Attribute attribute ){
      String ret = prefix;
      ret += "<attribute ";
      if( attribute.getName() != null ){
         ret += "name=\"" + attribute.getName() + "\" ";
      }
      ret += ">\n";
      if( attribute.getText() != null ){
         ret += toStringText( prefix + "   ", attribute.getText() );
      }
      if( attribute.getChoice() != null ){
         ret += toStringChoice( prefix + "   ", attribute.getChoice() );
      }
      ret += prefix + "</attribute>\n";
      return ret;
   }
   public static  String toStringGrammer( String prefix, Grammer grammer ){
      String ret = prefix;
      ret += "<grammer ";
      ret += ">\n";
      if( grammer.getStart() != null ){
         ret += toStringStart( prefix + "   ", grammer.getStart() );
      }
      if( grammer.getDefineList() != null ){
         ret += toStringDefineList( prefix + "   ", grammer.getDefineList() );
      }
      ret += prefix + "</grammer>\n";
      return ret;
   }
   public static  String toStringZeroOrMore( String prefix, ZeroOrMore zeroOrMore ){
      String ret = prefix;
      ret += "<zeroOrMore ";
      ret += ">\n";
      if( zeroOrMore.getRef() != null ){
         ret += toStringRef( prefix + "   ", zeroOrMore.getRef() );
      }
      ret += prefix + "</zeroOrMore>\n";
      return ret;
   }
   public static  String toStringRef( String prefix, Ref ref ){
      String ret = prefix;
      ret += "<ref ";
      if( ref.getName() != null ){
         ret += "name=\"" + ref.getName() + "\" ";
      }
      ret += "/>\n";
      return ret;
   }
   public static  String toStringAttributeList( String prefix, List<Attribute> attribute ){
      String ret = "";
      for ( Attribute vr : attribute ) {
         ret += toStringAttribute( prefix + "   ", vr );
      }
      return ret;
   }
   public static  String toStringChoiceList( String prefix, List<Choice> choice ){
      String ret = "";
      for ( Choice vr : choice ) {
         ret += toStringChoice( prefix + "   ", vr );
      }
      return ret;
   }
   public static  String toStringStart( String prefix, Start start ){
      String ret = prefix;
      ret += "<start ";
      ret += ">\n";
      if( start.getRef() != null ){
         ret += toStringRef( prefix + "   ", start.getRef() );
      }
      ret += prefix + "</start>\n";
      return ret;
   }
   public static  String toStringOptional( String prefix, Optional optional ){
      String ret = prefix;
      ret += "<optional ";
      ret += ">\n";
      if( optional.getRef() != null ){
         ret += toStringRef( prefix + "   ", optional.getRef() );
      }
      if( optional.getAttribute() != null ){
         ret += toStringAttribute( prefix + "   ", optional.getAttribute() );
      }
      ret += prefix + "</optional>\n";
      return ret;
   }
   public static  String toStringValue( String prefix, String value ){
      return prefix + "<value>" + value + "</value>\n";
   }
   public static  String toStringRefList( String prefix, List<Ref> ref ){
      String ret = "";
      for ( Ref vr : ref ) {
         ret += toStringRef( prefix + "   ", vr );
      }
      return ret;
   }
   public static  String toStringDefineList( String prefix, List<Define> define ){
      String ret = "";
      for ( Define vr : define ) {
         ret += toStringDefine( prefix + "   ", vr );
      }
      return ret;
   }

}
