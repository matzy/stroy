package org.openCage.stjx;

import java.util.ArrayList;
import java.util.List;

/**
* Created by IntelliJ IDEA.
* User: stephan
* Date: Aug 8, 2010
* Time: 2:26:22 AM
* To change this template use File | Settings | File Templates.
*/
public class Struct implements Complex {
    private Stjx stjx;
    private String name;
    private List<Atti> attis = new ArrayList<Atti>();
    private List<Ref> complexs = new ArrayList<Ref>();
    private List<String> interfaces = new ArrayList<String>();

    public Struct(Stjx stjx, String name) {
        this.stjx = stjx;
        this.name = name;
    }

    public Struct string(String name) {
        attis.add( StringAtti.required( name ));
        return this;
    }

    public Struct complex(String name) {
        complexs.add( Ref.required(name) );
        return this;
    }

    public ListType list(String name) {
        ListType ll = new ListType( this, name );
        stjx.dings.put( name, ll );
        complexs.add( Ref.optional( name ));
        return ll;
    }

    public OrType or(String name ) {

        OrType ot =  new OrType( this, name );
        stjx.dings.put( name, ot );
        complexs.add( Ref.required( name ));
        return ot;
    }
     public Optional optional() {
        return new Optional( this );
    }

    public String getType() {
        return name;
    }

    public String toJava() {
        String ret = "import java.util.ArrayList;\n" +
                     "import java.util.List;\n" +
                     "public class " + name;

        String intfs = "";
        if ( !interfaces.isEmpty() ) {
            for ( String interf : interfaces ) {
                if ( !intfs.isEmpty() ) {
                    intfs += ", ";
                }

                intfs += interf;
            }

            intfs = " implements " + intfs;
        }



        ret += intfs + " {\n";

        for ( Atti atti : attis ) {
            ret += atti.toJava();
        }

        for ( Ref ref : complexs ) {
            Complex comp = stjx.dings.get( ref.getName() );
            ret += comp.toJavaDecl();
        }

        ret += "}\n";

        return ret;
    }

    public String toJavaDecl() {
        return Stjx.toJavaBeanAttribute( name, Stjx.toFirstLower( name ));
    }

    public String toSAXStart() {
        String ret = "           if ( qName.equals(\"" + name + "\" )) {\n" +
                "               " + name + " elem = new " + name + "();\n";

        for ( Atti atti : attis ) {
            ret += atti.toSAXStart();
        }

        List<Complex> hasme = stjx.getUsers( name );

        if ( !hasme.isEmpty()) {
            ret += "               if ( !stack.empty() ) {\n" +
                   "                  Object peek =  stack.peek();\n\n";


            boolean list = false;
            for ( Complex complex : hasme ) {

                if ( complex instanceof ListType) {
                    list = true;
                } else {

                    String typeName = complex.getName();

                    ret += "                  if ( peek instanceof"+ typeName +") {\n" +
                           "                     (("+ typeName+")peek).set" + name + "( elem );\n" +
                           "                  };\n";
                }


            }

            if ( list ) {
                ret +=
               "                  if ( peek instanceof ListHelper ) {\n" +
               "                      ((ListHelper<" + name +">)peek).add( elem );\n" +
               "                  }\n" +
               "\n";

            }


            ret += "               }\n";

        }


        ret +=
                "               stack.push(elem );\n" +
                "               return;\n" +
                "           }\n" +
                "";
        return ret;
    }

    public boolean uses(String name) {
        for ( Ref ref : complexs ) {
            if ( ref.getName().equals( name )) {
                return true;
            }
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public String toRnc() {
        String ret = name + " = element " + name + " { ";


        String args = "";

        for ( Atti atti : attis ) {
            if ( !args.isEmpty() ) {
                args += ", ";
            }

            args += atti.toRnc();            

            if ( atti.isOptional() ) {
                args += "? ";
            }
        }

        for ( Ref ref : complexs ) {
            if ( !args.isEmpty() ) {
                args += ", ";
            }

            args += ref.getName();

            if ( ref.isOptional() ) {
                args += "? ";
            }

        }

        return ret + args + "}";
    }

    public void addInterface(String name) {
        this.interfaces.add( name );
    }

    public Stjx getZeug() {
        return stjx;
    }

    public List<Atti> getAttis() {
        return attis;
    }

    public List<Ref> getComplexs() {
        return complexs;
    }

}
