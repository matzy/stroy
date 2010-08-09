package org.openCage.stjx;

import org.openCage.lang.Strings;

import java.util.List;

/**
* Created by IntelliJ IDEA.
* User: stephan
* Date: Aug 8, 2010
* Time: 2:37:39 AM
* To change this template use File | Settings | File Templates.
*/
public class ListType implements Complex {
 private Struct struct;
 private String name;
 private String of;

 public ListType( Struct struct, String name) {
     this.struct = struct;
     this.name = name;
 }

 public Struct of(String typName ) {
     this.of = typName;
     return struct;
 }

 public String getType() {
     return "List<" + of + ">";
 }

 public String toJava() {
     return "";
 }

 public String toJavaDecl() {
     return "   private List<" + of + ">  " + name + " = new ArrayList<" + of + ">();\n" +
            "   public  void add( " + of + " " + Strings.toFirstLower(of) + ") {\n" +
            "      " + name + ".add( " + Strings.toFirstLower( of) + " );\n" +
            "   };\n" +
            "   public List<"+ of + "> get" + Strings.toFirstUpper( name ) + "() {\n" +
            "      return " + name + ";\n" +
            "   }\n"+
            "";

 }

 public String toSAXStart() {

     List<Complex> users = struct.getZeug().getUsers( name );

     String ret = "          if ( qName.equals( \""+ name + "\"))  {\n" +
                  "             if ( stack.empty() ) {\n" +
                  "                throw new IllegalArgumentException( \""+name+" needs to be in a complex type \");\n" +
                  "             }\n";


     ret +=       "             Object peek =  stack.peek();\n";

     for ( Complex comp : users ) {

         ret +=         "\n" +
                 "              if ( peek instanceof "+ comp.getName() +" ) {\n" +
                 "                  stack.push( new ListHelper<"+ of +">( (("+ comp.getName() +")peek).get" + Strings.toFirstUpper( name )+ "() ));\n" +
                 "                  return;\n" +
                 "              } else {\n" +
                 "                  throw new IllegalArgumentException( \""+ name +" is not member of \" + peek.getClass() );\n" +
                 "              }\n";
     }

     ret += "          }\n";

     return ret;
 }

 public boolean uses(String name) {
     return of.equals( name );
 }

 public String getName() {
     return name;
 }

    public String toRnc() {
        return name + " = element " + name +" { " + of + "* }";
    }

    public void setInterface(String name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
