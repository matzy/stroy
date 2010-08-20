package org.openCage.stjx;

import org.openCage.geni.Mesod;
import org.openCage.lang.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 8, 2010
 * Time: 9:15:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrType implements Complex {
    private String name;
    private Struct struct;
    private List<String> alternatives = new ArrayList<String>();

    public OrType(Struct struct, String name) {
        this.struct = struct;
        this.name = name;
    }

    public String getType() {
        String ret = "public interface " + name + " {}\n";
        return ret;
    }

    public String toJava() {
        return "public interface " + name + " {}\n";
    }

    public String toJavaDecl() {
        return Stjx.toJavaBeanAttribute( name, Strings.toFirstLower( name ));
    }

    public String toSAXStart() {
//        List<Complex> users = struct.getZeug().getUsers( name );
//
//        String ret = "          if ( qName.equals( \""+ name + "\"))  {\n" +
//                     "             if ( stack.empty() ) {\n" +
//                     "                throw new IllegalArgumentException( \""+name+" needs to be in a complex type \");\n" +
//                     "             }\n";
//
//
//        ret +=       "             Object peek =  stack.peek();\n";
//
//        for ( Complex comp : users ) {
//
//            ret +=         "\n" +
//                    "              if ( peek instanceof "+ comp.getName() +" ) {\n" +
//                    "                  stack.push( new OrHelper<"+ name +">( (("+ comp.getName() +")peek).get" + Strings.toFirstUpper( name )+ "() ));\n" +
//                    "                  return;\n" +
//                    "              } else {\n" +
//                    "                  throw new IllegalArgumentException( \""+ name +" is not member of \" + peek.getClass() );\n" +
//                    "              }\n";
//        }
//
//        ret += "          }\n";
//
//        return ret;

        String ret = "           if ( qName.equals(\"" + name + "\" )) {\n" +
                     "              // ortype: nothing to do\n" +
                     "              return;\n" +
                     "           }\n";

        return ret;
    }

    public boolean uses(String name) {
        return alternatives.contains( name );
    }

    public String getName() {
        return name;
    }

    public String toRnc() {
        String ret = name + " = " + name + " {(";
        String arg = "";
        for ( String alt : alternatives) {
            if ( !arg.isEmpty()) {
                arg += ", ";
            }

            arg += alt;
        }
        ret += arg + ")}";

        return ret;
    }

    public void setInterface(String name) {
    }

    public String toSAXEnd() {
        return ""; // nohing to do
    }

    @Override
    public String toToXML( Mesod mesod ) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Struct with( String ... names ) {
        alternatives.addAll( Arrays.asList( names ));

        for ( String alt : names ) {
            Complex comp = struct.getZeug().structs.get( alt );
            if ( comp == null ) {
                throw new IllegalArgumentException( alt + " used in or("+ name +") before declared as struct" );
            }

            comp.setInterface( name );
        }

        return struct;
    }
}
