package org.openCage.stjx;

import org.openCage.lang.Strings;

/**
* Created by IntelliJ IDEA.
* User: stephan
* Date: Aug 8, 2010
* Time: 2:24:59 AM
* To change this template use File | Settings | File Templates.
*/
public class StringAtti implements Atti {
 private String name;
 private boolean optional;

 public StringAtti(String name, boolean optional ) {
     this.name = name;
     this.optional = optional;
 }

 public String getType() {
     return "String";
 }

 public String getName() {
     return name;
 }

 public String toJava() {
     return Stjx.toJavaBeanAttribute( "String", name );
 }

 public String toSAXStart() {
     String ret = "               String "+ name + " = attributes.getValue( \"" + name +"\" );\n" +
            "               if ( "+ name + " != null ) {\n" +
            "                  elem.set" + Strings.toFirstUpper(name ) + "( " + name + ");\n" +
            "               } \n";

     if ( !optional ) {
         ret +=                  "               else {\n                  throw new IllegalArgumentException( \"attribute " + name + " is required\" );\n" +
            "               }\n";

     }

     return ret;
 }

    public String toRnc() {
        return "attribute " + name + " {xsd:string}";
    }

    public boolean isOptional() {
        return optional;
    }

    public static Atti optional(String name) {
     return new StringAtti( name, true );
 }

 public static Atti required(String name) {
     return new StringAtti( name, false );
 }
}
