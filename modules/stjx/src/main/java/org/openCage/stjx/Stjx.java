package org.openCage.stjx;

import org.openCage.generj.*;
import org.openCage.io.IOUtils;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.lang.Strings;

import java.io.*;
import java.util.*;

import static org.openCage.generj.ArrayOf.ARRAYOF;
import static org.openCage.generj.BinOp.AND;
import static org.openCage.generj.BinOp.LESS;
import static org.openCage.generj.BinOp.PLUS;
import static org.openCage.generj.BracketExpr.BRACKET;
import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.Cast.CAST;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.Int.ZERO;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.NewExpr.NEW;
import static org.openCage.generj.Str.STR;
import static org.openCage.generj.Typ.*;
import static org.openCage.generj.UnOp.NOT;
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

//        Stjx stjx = new Stjx("Enumi");
//
//        stjx.struct( "Enumi" ).enm( "scope" );
//
//        stjx.enm( "Scope", "A", "B", "C");

//        Stjx stjx = new Stjx("EmbeddedMultiple");
//
//        stjx.struct( "EmbeddedMultiple" ).zeroOrMore().complex( "Inti" );
//
////        stjx.struct( "EmbeddedMultiple" ).list( "lst" ).of( "Inti");
////
//        stjx.struct("A");
//        stjx.struct("B");
//        stjx.or( "Inti").with( "A", "B" );


        Stjx stjx = new Stjx( "grammer" );

        stjx.struct( "grammer" ).complex( "start" ).zeroOrMore().complex("define");
        stjx.struct( "start").complex( "ref" );

        stjx.struct( "ref" ).string("name");

        stjx.struct( "define").string("name").complex("element");

        stjx.struct( "element" ).
                string( "name" ).
                zeroOrMore().complex("zeroOrMore").
                zeroOrMore().complex("optional").
                zeroOrMore().complex("attribute").
                zeroOrMore().complex("choice");

        stjx.struct( "zeroOrMore" ).complex( "ref");
        stjx.struct( "optional" ).
                optional().complex( "ref").
                optional().complex("attribute");
        stjx.struct( "attribute" ).
                string( "name" ).
                optional().complex("text").
                optional().complex("choice");
        stjx.struct( "text" );

        stjx.struct("choice").
                zeroOrMore().string("value").  // this is really an or of values and ref but that does not work without
                zeroOrMore().complex("ref");    // extra complex

        stjx.generate( FSPathBuilder.getPath( "/Users/stephan/Documents/prs/stroy-10" ).add( "modules", "stjx-rng").toString(), "org.openCage.stjx.rng" );


        //stjx.struct( "value" ).


//        Stjx stjx = new Stjx( "MT");
//
////        stjx.struct( "MT").multiLine( "foo" );
////        stjx.struct( "MT").list( "props" ).ofStrings( "property" ).
////        list( "aaa" ).of( "AAA" );
////        stjx.struct( "AAA");
//        stjx.struct( "MT").zeroOrMore().ofString( "property" );


//        stjx.struct("Loc").locale( "theLocal" );

//        System.out.println( stjx.toToXML( "org.openCage.foo" ));
//        System.out.println( stjx.toFromXML( "org.openCage.foo" ));
////
//        for ( Complex cop : stjx.structs.values() ) {
//            if ( cop.toJava( "org.doo") != null ) {
//                System.out.println( cop.toJava("org.doo").toString() );
//            }
//
////            if ( cop instanceof Struct ) {
////                System.out.println( (((Struct) cop).toJava("org.doo")).toString());
////            }
////            if ( cop instanceof OrType ) {
////                System.out.println( (((OrType) cop).toJava("org.doo0000000000")).toString());
////            }
////            if ( cop instanceof EnumType ) {
////                System.out.println( cop.toJava("org.doo0000000000").toString());
////            }
//        }
//
////        Locale loc = new Locale( "German");
////
////        System.out.println( loc );
    }



    public void mpl( String author, String email, String time, String project ) {
        clazzComment = new MPL(author, email, time, project ).getComment();
    }


    public void validate() {
        for ( Complex comp  : this.structs.values() ) {

            for ( String ref : comp.getRefs() ) {
                if ( !structs.containsKey(ref)) {
                    throw new IllegalArgumentException( comp.getTagName() +" Ref not a struct: " + ref );
                }
            }
        }
    }

    public void generate( String baseDir, String packag ) {

        validate();

        IOUtils.ensurePath( FSPathBuilder.getPath( baseDir ).add( "src", "main", "java" ).addPackage(packag ).add( "foo" ));
        Clazz tofrom = toFromXML( packag );
        if ( clazzComment != null ) {
            tofrom.comment( clazzComment );
        }

        {
            File outFile = FSPathBuilder.getPath( baseDir ).add( "src", "main", "java" ).addPackage(packag ).add( Strings.toFirstUpper( name) +  "FromXML.java").toFile();
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

                ClassI tojava = cop.toJava( packag, name);

                if ( tojava != null && clazzComment != null ) {
                    tojava.comment( clazzComment );
                }

                if ( tojava != null ) {

                    File outFile = FSPathBuilder.getPath( baseDir ).add( "src", "main", "java" ).addPackage(packag ).add( cop.getClassName() + ".java").toFile();
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
            FSPath path = FSPathBuilder.getPath( baseDir ).add( "src", "main", "java" ).addPackage(packag ).add( Strings.toFirstUpper( name )+ "ToXML.java" );
            IOUtils.ensurePath( path );
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
            IOUtils.ensurePath( path );
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

        Clazz clazz = new Clazz( pack, TYP( Strings.toFirstUpper( name )+ "ToXML") );

        clazz._import( "java.util.List");

        for ( Complex compl : this.structs.values() ) {

            compl.toToXML( clazz );
        }

        return clazz;
    }

    private Clazz toFromXML( String pack) {

        String clazzTyp = Strings.toFirstUpper( name );

        Clazz clazz = new Clazz( pack, TYP( clazzTyp + "FromXML") ).
                _import( "org.xml.sax.Attributes" ).
                _import( "org.xml.sax.SAXException" ).
                _import( "org.xml.sax.helpers.DefaultHandler" ).
                _import( "javax.xml.parsers.SAXParser" ).
                _import( "javax.xml.parsers.SAXParserFactory" ).
                _import( "javax.xml.stream.events.EntityDeclaration" ).
                _import( "java.io.File" ).
                _import( "java.util.ArrayList" ).
                _import( "java.util.HashMap" ).
                _import( "java.util.List" ).
                _import( "java.util.Map" ).
                _import( "java.util.Stack" ).
                _import( "java.util.Locale" ).
                _import( "java.io.InputStream" ).
                _import( "java.io.IOException" ).
                _import( "javax.xml.parsers.ParserConfigurationException").
                _import( "java.io.FileInputStream").
                _import( "java.io.FileNotFoundException").

                _extends( TYP("DefaultHandler") ).

                _privat().field( TYP("Stack"), NAME("stack")).init( NEW( TYP("Stack"))).

                _privat().field( TYP("Object"), NAME("goal")).c().

                _public().override().method( "startDocument")._throws( TYP("SAXException")).body().
                    call( DOT( NAME( "stack"), NAME("clear"))).r().c().

                _public().override().method( "endDocument")._throws( TYP("SAXException")).body().r().c().

                _public().method( TYP(clazzTyp), "getGoal").body().
                _return( CAST( TYP(clazzTyp), NAME("goal"))).r().c()
                ;

        Clazz AA = clazz._privat()._static().clazz( TYP("AttributedAttributes"));

        AA._privat().field( TYP("Attributes"), NAME("attis"));
        AA._privat().field( LIST(TYP("Integer")), NAME("idxes")).init( NEW( ARRAYLIST(TYP("Integer"))));

        AA._public().cnstr().arg( TYP("Attributes"), NAME("attis")).body().
                    assign( DOT(NAME("this"), NAME("attis")), NAME("attis"));

        Block AAgetValue = AA._public().method( STRING, "getValue").arg( STRING, NAME("name") ).body();
        AAgetValue.field( STRING, NAME("val")).init( CALL(DOT(NAME("attis"), NAME("getValue")), NAME("name")));
        AAgetValue.ifNotNull(NAME("val"))._then().
                call( DOT(NAME("idxes"), NAME("add")), CALL(DOT(NAME("attis"), NAME("getIndex")), NAME("name")));
        AAgetValue._return(NAME("val"));

        Block AAcheck = AA._public().method( "check").body();
        AAcheck._for( INT, "idx", ZERO, LESS(NAME("idx"), CALL(DOT(NAME("attis"), NAME("getLength")))), PLUSPLUS(NAME("idx")) ).
                body()._if( NOT( CALL( DOT(NAME("idxes"), NAME("contains")), NAME("idx"))))._then().
                throwIllegalArgument( PLUS(STR("Unknown Attribute: "), CALL(DOT(NAME("attis"), NAME("getQName")), NAME("idx"))));


        Block read =  clazz._public()._static().method( TYP(clazzTyp), "read").arg( TYP("InputStream"), NAME("is")).body();
        read.field( TYP( clazzTyp + "FromXML"), NAME("from" )).defaultInit();
        read.field( TYP("SAXParserFactory"), NAME("factory")).init( CALL(DOT(NAME("SAXParserFactory"), NAME("newInstance"))));

        TryStatement tryS= read._try();
        Block trytry = tryS._try();
        trytry.field( TYP("SAXParser"), NAME("parser")).init( CALL(DOT(NAME("factory"), NAME("newSAXParser"))));
        trytry.call( DOT("parser","parse"), NAME("is"), NAME("from") );

        tryS._catch( TYP("IOException"), NAME("exp") )._throw( NEW( TYP("Error"), NAME("exp")));
        tryS._catch( TYP("SAXException"), NAME("exp") )._throw( NEW( TYP("Error"), NAME("exp")));
        tryS._catch( TYP("ParserConfigurationException"), NAME("exp") )._throw( NEW( TYP("Error"), NAME("exp")));
        tryS._catch( TYP("IllegalArgumentException"), NAME("exp") )._throw( NEW( TYP("Error"), NAME("exp")));
//                call( DOT("exp","printStackTrace")).
//                call( DOT(NAME("System"), NAME("err"), NAME("println")), STR("Problem parsing "));
        TryStatement filly = tryS._finally().ifNotNull( NAME("is"))._then()._try();
        filly._try().call( DOT( "is", "close"));
        filly._catch( TYP("IOException"), NAME("e"));
//        if ( is != null ) {
//            try {
//                is.close();
//            } catch (IOException e) {
//                // ignore
//            }
//        }

        read._return( CALL(DOT("from","getGoal")));

        Block readPath =  clazz._public()._static().method( TYP(clazzTyp), "read").arg( TYP("File"), NAME("file")).body();
        TryStatement ttr = readPath._try();
        ttr._try()._return(CALL( NAME("read"), NEW( TYP("FileInputStream"), NAME("file"))));
        ttr._catch( TYP("FileNotFoundException"),NAME("e"))._throw( NEW( TYP("Error"), NAME("e")));

//        public static  Artig read( File file ){
//            try {
//                return read( new FileInputStream( file) );
//            } catch (FileNotFoundException e) {
//                throw new Error(e);
//            }
//        }






        clazz._privat().field( BOOLEAN, NAME("getCharacters" ));

        Block start = clazz._public().override().method( "startElement")._throws( TYP("SAXException")).
                arg( STRING, NAME("uri")).
                arg( STRING, NAME("localName")).
                arg( STRING, NAME("qName")).
                arg( TYP("Attributes"), NAME("saxAttis")).body();
        
        start.field( TYP("AttributedAttributes"), NAME("attributes")).init( NEW(TYP("AttributedAttributes"), NAME("saxAttis")));

        Block end = clazz._public().override().method( "endElement")._throws( TYP("SAXException")).
                arg( STRING, NAME("uri")).
                arg( STRING, NAME("localName")).
                arg( STRING, NAME("qName")).body();


        Block content = clazz._public().method( "characters" ).arg( TYP("char[]"), NAME("ch")).arg( INT, NAME("start")).arg( INT, NAME("length") ).body();
        Block contentBody = content._if( NAME("getCharacters"))._then();
        contentBody.field( TYP("StringBuffer"), NAME("sb")).init( NEW(TYP("StringBuffer")));
        contentBody._for( INT, "idx", NAME("start"),
                AND( LESS( NAME("idx"), DOT(NAME("ch"), NAME("length"))), LESS(NAME("idx"), BRACKET(PLUS(NAME("start"), NAME("length"))))),
                PLUSPLUS( NAME("idx"))).body().
                call( DOT( NAME("sb"), NAME("append")), ARRAYOF( NAME("ch"), NAME("idx")));
        contentBody.call( DOT(NAME("stack"), NAME("push")), CALL( DOT(NAME("sb"), NAME("toString"))));


        for ( Complex complex : structs.values() ) {
            complex.toFromXMLStart(start);
        }

        start.throwIllegalArgument( PLUS( STR("unknown tag "), NAME( "qName" )));

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

    private Map<String,Complex> structs = new HashMap<String,Complex>();
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

    public OrType or( String name ) {

        if ( name.charAt(0) != name.toUpperCase().charAt(0)) {
            throw new IllegalArgumentException( "or/interface type needs to be first letter uppercase not " + name );
        }

        OrType ot =  new OrType( this, name );
        structs.put( name, ot );
        //complexs.add( Ref.required( name ));
        return ot;
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

    public List<Complex> getEmbeddedUsers( String name ) {
        List<Complex> users = new ArrayList<Complex>();
        for ( Complex complex : structs.values() ) {
            if ( complex.usesEmbedded( name )) {
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
//        if ( !isValidType( name )) {
//            throw new IllegalArgumentException( name + " does not start with an uppercase letter" );
//        }
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


    public void addComplex(Complex complex) {

        Complex old = structs.get( complex.getClassName());
        if ( old != null ) {
            if ( !old.equals( complex )) {
                throw new IllegalArgumentException( "there is already a complex with name (upper or lower case) " + complex.getTagName()  );
            }

            return;
        }

        old = structs.get( complex.getTagName());
        if ( old != null ) {
            if ( !old.equals( complex )) {
                throw new IllegalArgumentException( "there is already a complex with name (upper or lower case) " + complex.getTagName()  );
            }

            return;
        }

        structs.put( complex.getTagName(), complex );
    }

    public Complex getComplex(String name) {
        if ( structs.containsKey( name )) {
            return structs.get( name );
        }

        String lower = Strings.toFirstLower( name );
        if ( structs.containsKey( lower )) {
            return structs.get( lower );
        }

        return structs.get( Strings.toFirstUpper( name ));
    }

    public Complex getComplex(Ref ref) {
        return getComplex( ref.getName() );
    }

    public static FSPath getProjectBase( Class clazz ) {
        FSPath path = FSPathBuilder.getPath( clazz.getResource("."));


        while ( !path.getFileName().equals( "classes" ) &&
                !path.getFileName().equals( "out" ) &&
                !path.getFileName().equals( "modules" )) {
            path = path.parent();
        }


        return path.parent();
    }

    public String toString() {

        String ret = "Stjx: " + name + "\n";

        for ( Complex comp : structs.values()  ) {
            ret += comp.toString() + "\n";
        }

        return ret;


    }

}

