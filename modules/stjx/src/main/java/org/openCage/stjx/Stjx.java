package org.openCage.stjx;

import org.openCage.generj.*;
import org.openCage.io.FileUtils;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.Strings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.openCage.generj.ArrayOf.ARRAYOF;
import static org.openCage.generj.BinOp.AND;
import static org.openCage.generj.BinOp.LESS;
import static org.openCage.generj.BinOp.PLUS;
import static org.openCage.generj.BracketExpr.BRACKET;
import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.Cast.CAST;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.NewExpr.NEW;
import static org.openCage.generj.Str.STR;
import static org.openCage.generj.Typ.TYP;
import static org.openCage.generj.Typ.INT;
import static org.openCage.generj.Typ.STRING;
import static org.openCage.generj.Typ.BOOLEAN;
import static org.openCage.generj.UnOp.PLUSPLUS;

/**
 * ** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * <p/>
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * <p/>
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * <p/>
 * The Original Code is stroy code
 * <p/>
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 * <p/>
 * Contributor(s):
 * **** END LICENSE BLOCK ****
*/
public class Stjx {
    private BlockComment clazzComment;

    public static void main(String[] args) {
//        Stjx stjx = new Stjx("Artifact");

//        stjx.struct( "ArtifactRef" ).
//                STRING( "groupId" ).
//                STRING( "name" ).
//                STRING( "scope" );
//
//        stjx.struct( "Author" ).
//                STRING("name").
//                optional().STRING( "email" );
//
//        stjx.struct( "Module" ).STRING( "mod" );
//        stjx.struct( "Extern" ).STRING( "ext");
//        stjx.struct( "Licence" ).STRING( "name");
//
//        stjx.struct( "Artifact" ).list( "depends" ).of( "ArtifactRef" ).
//                STRING( "groupId" ).
//                STRING( "name" ).
//                STRING( "version" ).
//                list( "authors" ).of( "Author" ).
//                or( "Application" ).with( "Module", "Extern" ).
//                complex( "Licence" );
//
////        stjx.struct("Foo").map("props").of("ArtifactRef", "Artifact" );
////        stjx.struct("Foo").keyVal("props", "ref", "Artifact" );
//        stjx.struct("OO").or("Alti").with( "ArtifactRef", "Artifact" );
//
//        stjx.struct( "CCC").withContent();

//        Stjx stjx = new Stjx("Big");
//
//        stjx.struct( "Big" ).multiLine( "description" );

        Stjx stjx = new Stjx("Enumi");

        stjx.struct( "Enumi" ).enm( "scope" );

        stjx.enm( "Scope", "A", "B", "C");







//        stjx.struct("Loc").locale( "theLocal" );

//        System.out.println( stjx.toToXML( "org.openCage.foo" ));
        System.out.println( stjx.toFromXML( "org.openCage.foo" ));

        for ( Complex cop : stjx.structs.values() ) {
            if ( cop instanceof Struct ) {
                System.out.println( (((Struct) cop).toJava("org.doo")).toString());
            }
            if ( cop instanceof OrType ) {
                System.out.println( (((OrType) cop).toJava("org.doo0000000000")).toString());
            }
            if ( cop instanceof EnumType ) {
                System.out.println( cop.toJava("org.doo0000000000").toString());
            }
        }

//        Locale loc = new Locale( "German");
//
//        System.out.println( loc );
    }


    public void mpl( String author, String email, String time, String project ) {
        clazzComment = new MPL(author, email, time, project ).getComment();
    }

    public void generate( String baseDir, String packag ) {

        FileUtils.ensurePath( FSPathBuilder.getPath( baseDir ).add( "src", "main", "java" ).addPackage(packag ).add( "foo" ));
        Clazz tofrom = toFromXML( packag );
        if ( clazzComment != null ) {
            tofrom.comment( clazzComment );
        }

        {
            File outFile = FSPathBuilder.getPath( baseDir ).add( "src", "main", "java" ).addPackage(packag ).add( name+ "FromXML.java").toFile();
            FileWriter out = null;
            try {
                out = new FileWriter(outFile);
                out.write( tofrom.toString() );
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        {
            for ( Complex cop : structs.values() ) {

                ClassI tojava = cop.toJava( packag );

                if ( tojava != null && clazzComment != null ) {
                    tojava.comment( clazzComment );
                }

                if ( tojava != null ) {

                    File outFile = FSPathBuilder.getPath( baseDir ).add( "src", "main", "java" ).addPackage(packag ).add( cop.getName() + ".java").toFile();
                    FileWriter out = null;
                    try {
                        out = new FileWriter(outFile);
                        out.write( /*"package " + packag + ";\n" +*/ tojava.toString() );
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        {
            FSPath path = FSPathBuilder.getPath( baseDir ).add( "src", "main", "java" ).addPackage(packag ).add( name + "ToXML.java" );
            FileUtils.ensurePath( path );
            File outFile = path.toFile();
            FileWriter out = null;
            try {
                out = new FileWriter(outFile);
                Clazz toxml = toToXML( packag );
                if( clazzComment != null ) {
                    toxml.comment( clazzComment );
                }
                out.write( toxml.toString() );
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        {
            FSPath path = FSPathBuilder.getPath( baseDir ).add( "src", "main", "resources" ).addPackage(packag ).add( name + ".rnc" );
            FileUtils.ensurePath( path );
            File outFile = path.toFile();
            FileWriter out = null;
            try {
                out = new FileWriter(outFile);
                out.write( toRnc() );
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private Clazz toToXML( String pack) {

        Clazz clazz = new Clazz( pack, TYP( name + "ToXML") );

        clazz.imprt( "java.util.List");

        for ( Complex compl : this.structs.values() ) {

            compl.toToXML( clazz );
        }

        return clazz;
    }

    private Clazz toFromXML( String pack) {
        Clazz clazz = new Clazz( pack, TYP( name + "FromXML") ).
                imprt( "org.xml.sax.Attributes" ).
                imprt( "org.xml.sax.SAXException" ).
                imprt( "org.xml.sax.helpers.DefaultHandler" ).
                imprt( "javax.xml.parsers.SAXParser" ).
                imprt( "javax.xml.parsers.SAXParserFactory" ).
                imprt( "javax.xml.stream.events.EntityDeclaration" ).
                imprt( "java.io.File" ).
                imprt( "java.util.ArrayList" ).
                imprt( "java.util.HashMap" ).
                imprt( "java.util.List" ).
                imprt( "java.util.Map" ).
                imprt( "java.util.Stack" ).
                imprt( "java.util.Locale" ).

                extnds( TYP("DefaultHandler") ).
                    publc().sttic().clazz( Typ.of("ListHelper", TYP("T"))).
                        privt().fild( Typ.of("List",TYP("T")), NAME("list")).c().
                        publc().cnstr().arg( Typ.of("List",TYP("T")), NAME("list")).body().
                            assign( DOT( NAME("this"), NAME("list")), NAME("list")).r().c().
                        publc().method( "add").arg( TYP("T"), NAME("elem")).body().
                            call( DOT( NAME( "list"), NAME("add")), NAME("elem")).r().c().r().

                    privt().fild( TYP("Stack"), NAME("stack")).init( NEW( TYP("Stack"))).
                
                    privt().fild( TYP("Object"), NAME("goal")).c().

                    publc().override().method( "startDocument").thrws( TYP("SAXException")).body().
                        call( DOT( NAME( "stack"), NAME("clear"))).r().c().

                    publc().override().method( "endDocument").thrws( TYP("SAXException")).body().r().c().

                    publc().method( TYP(name), "getGoal").body().
                        retrn( CAST( TYP(name), NAME("goal"))).r().c()
                  ;

        clazz.privt().fild( BOOLEAN, NAME("getCharacters" ));

        Block start = clazz.publc().override().method( "startElement").thrws( TYP("SAXException")).
                arg( STRING, NAME("uri")).
                arg( STRING, NAME("localName")).
                arg( STRING, NAME("qName")).
                arg( TYP("Attributes"), NAME("attributes")).body();

        Block end = clazz.publc().override().method( "endElement").thrws( TYP("SAXException")).
                arg( STRING, NAME("uri")).
                arg( STRING, NAME("localName")).
                arg( STRING, NAME("qName")).body();


        Block content = clazz.publc().method( "characters" ).arg( TYP("char[]"), NAME("ch")).arg( INT, NAME("start")).arg( INT, NAME("length") ).body();
        Block contentBody = content.iff( NAME("getCharacters")).thn();
        contentBody.fild( TYP("StringBuffer"), NAME("sb")).init( NEW(TYP("StringBuffer")));
        contentBody.fr( INT, "idx", NAME("start"),
                    AND( LESS( NAME("idx"), DOT(NAME("ch"), NAME("length"))), LESS(NAME("idx"), BRACKET(PLUS(NAME("start"), NAME("length"))))),
                    PLUSPLUS( NAME("idx"))).body().
                        call( DOT( NAME("sb"), NAME("append")), ARRAYOF( NAME("ch"), NAME("idx")));
        contentBody.call( DOT(NAME("stack"), NAME("push")), CALL( DOT(NAME("sb"), NAME("toString"))));


        for ( Complex complex : structs.values() ) {
            complex.toFromXMLStart(start);
        }

        start.thrwIllegalArgument( PLUS( STR("unknown tag "), NAME( "qName" )));

        for ( Complex complex : structs.values() ) {
            complex.toFromXMLEnd(end);
        }


        return clazz;
    }

//    private String toJava() {
//
//        String ret =
//                "import org.xml.sax.Attributes;\n" +
//                "import org.xml.sax.SAXException;\n" +
//                "import org.xml.sax.helpers.DefaultHandler;\n" +
//                "\n" +
//                "import javax.xml.parsers.SAXParser;\n" +
//                "import javax.xml.parsers.SAXParserFactory;\n" +
//                "import javax.xml.stream.events.EntityDeclaration;\n" +
//                "import java.io.File;\n" +
//                "import java.util.ArrayList;\n" +
//                "import java.util.HashMap;\n" +
//                "import java.util.List;\n" +
//                "import java.util.Map;\n" +
//                "import java.util.Stack;\n\n" +
//                "   public class FromXML extends DefaultHandler {\n" +
//                "\n" +
//                "       private static class ListHelper<T> {\n" +
//                "           private final List<T> list;\n" +
//                "\n" +
//                "           public ListHelper( List<T> list ) {\n" +
//                "               this.list = list;\n" +
//                "           }\n" +
//                "\n" +
//                "           public void add( T elem) {\n" +
//                "               list.add( elem );\n" +
//                "           }\n" +
//                "       }\n" +
//                "\n" +
//                "       private Stack stack = new Stack();"+
//                "       private Object goal;\n" +
//                "       @Override\n" +
//                "       public void startDocument() throws SAXException {\n" +
//                "           stack.clear();\n" +
//                "       }\n" +
//                "\n" +
//                "       @Override\n" +
//                "       public void endDocument() throws SAXException{}           \n" +
//                "\n" +
//                "       @Override\n" +
//                "       public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{\n" +
//                "";
//
//        for ( Complex complex : structs.values() ) {
//            ret += complex.toSAXStart();
//        }
//
//        ret += "             throw new IllegalArgumentException(\"unknown tag \" + qName );\n" +
//                "\n" +
//                "       }\n" +
//                "\n" +
//                "       @Override\n" +
//                "       public void endElement( String uri, String localName, String qName) throws SAXException {\n";
//
//        for ( Complex complex : structs.values() ) {
//            ret += complex.toSAXEnd();
//        }
//
//        ret += "       }\n" +
//                "\n" +
//                "       public Object getGoal() {\n" +
//                "           return goal;\n" +
//                "       }\n" +
//                "}\n";
//
//        return ret;
//    }

    Map<String,Complex> structs = new HashMap<String,Complex>();
    private String name;


    public Stjx(String name ) {
        this.name = name;
    }

    public Struct struct(String name) {
        checkClassName( name );

        Struct struct = new Struct( this, name );
        structs.put( name, struct);
        return struct;
    }

    public void enm(String name, String ... vals ) {
        checkClassName( name );

        EnumType en = new EnumType( this, name, vals );
        structs.put( name, en);

    }


    public List<Complex> getUsers( String name ) {
        List<Complex> users = new ArrayList<Complex>();
        for ( Complex complex : structs.values() ) {
            if ( complex.uses( name )) {
                users.add( complex );
            }
        }
        return users;
    }

    public String toRnc() {
        String ret = "default namespace = \"\"\n\n";

        ret += "start = " + name + "\n";

        for ( Complex comp : structs.values() ) {
            ret += comp.toRnc() + "\n";
        }

        return ret;
    }


    public static boolean isValidType( String name ) {
        return name.charAt( 0 ) == name.toUpperCase().charAt(0);
    }

    private void checkClassName(String name) {
        if ( !isValidType( name )) {
            throw new IllegalArgumentException( name + " does not start with an uppercase letter" );
        }
    }



    public static String toJavaBeanAttribute( String type, String name ) {
        String ret = "";
        ret += "   private " + type + " " + name + ";\n";
        ret += "   public "+ type + " get" + Strings.toFirstUpper( name ) + "() {\n";
        ret += "      return " + name + ";\n";
        ret += "   }\n";
        ret += "   public void set" + Strings.toFirstUpper( name ) + "( " + type
                + " " + name + " ) {\n";
        ret += "      this." + name + " = " + name + ";\n";
        ret += "   }\n";
        return ret;
    }


}

