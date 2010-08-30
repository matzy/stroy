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

import static org.openCage.generj.Cast.CAST;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.Typ.TYP;

public class Stjx {

    public static void main(String[] args) {
//        Stjx stjx = new Stjx("Artifact");

//        stjx.struct( "ArtifactRef" ).
//                string( "groupId" ).
//                string( "name" ).
//                string( "scope" );
//
//        stjx.struct( "Author" ).
//                string("name").
//                optional().string( "email" );
//
//        stjx.struct( "Module" ).string( "mod" );
//        stjx.struct( "Extern" ).string( "ext");
//        stjx.struct( "Licence" ).string( "name");
//
//        stjx.struct( "Artifact" ).list( "depends" ).of( "ArtifactRef" ).
//                string( "groupId" ).
//                string( "name" ).
//                string( "version" ).
//                list( "authors" ).of( "Author" ).
//                or( "Application" ).with( "Module", "Extern" ).
//                complex( "Licence" );
//
////        stjx.struct("Foo").map("props").of("ArtifactRef", "Artifact" );
////        stjx.struct("Foo").keyVal("props", "ref", "Artifact" );
//        stjx.struct("OO").or("Alti").with( "ArtifactRef", "Artifact" );
//
//        stjx.struct( "CCC").withContent();

        Stjx stjx = new Stjx("Loc");


        stjx.struct("Loc").locale( "theLocal" );

        System.out.println( stjx.toToXML( "org.openCage.foo" ));
//        System.out.println( stjx.toFromXML( "org.openCage.foo" ));

//        for ( Complex cop : stjx.structs.values() ) {
//            if ( cop instanceof Struct ) {
//                System.out.println( (((Struct) cop).toJava("org.doo")).toString());
//            }
//            if ( cop instanceof OrType ) {
//                System.out.println( (((OrType) cop).toJava("org.doo0000000000")).toString());
//            }
//        }

        Locale loc = new Locale( "German");

        System.out.println( loc );
    }

    public void generate( String baseDir, String packag ) {

        FileUtils.ensurePath( FSPathBuilder.getPath( baseDir ).add( "src", "main", "java" ).addPackage(packag ).add( "foo" ));

        {
            File outFile = FSPathBuilder.getPath( baseDir ).add( "src", "main", "java" ).addPackage(packag ).add( name+ "FromXML.java").toFile();
            FileWriter out = null;
            try {
                out = new FileWriter(outFile);
                out.write( /*"package " + packag + ";\n" +*/ toFromXML( packag ) );
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        {
            for ( Complex cop : structs.values() ) {

                Object tojava = cop.toJava( packag );

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
                out.write( toToXML( packag ) );
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

    private String toToXML( String pack) {

        Clazz clazz = new Clazz( pack, TYP( name + "ToXML") );

        clazz.imprt( "java.util.List");
//        import java.util.ArrayList;
//        import java.util.HashMap;
//        import
//        import java.util.Map;


        for ( Complex compl : this.structs.values() ) {

            compl.toToXML( clazz );
            //System.out.println( compl.getName() );
        }

        return clazz.toString();
    }

    private String toFromXML( String pack) {
        Clazz clazz = new Clazz( pack, Typ.s( name + "FromXML") ).
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

                extnds( Typ.s("DefaultHandler") ).
                    publc().sttic().clazz( Typ.of("ListHelper", Typ.s("T"))).
                        privt().fild( Typ.of("List",Typ.s("T")), "list").c().
                        publc().cnstr().arg( Typ.of("List",Typ.s("T")), "list").body().
                            assign( DOT( NAME("this"), NAME("list")), NAME("list")).r().c().
                        publc().method( "add").arg( TYP("T"), "elem ").body().
                            call( DOT( NAME( "list"), NAME("add")), NAME("elem")).r().c().r().

                    privt().fild( Typ.s("Stack"), "stack").init( new NewExpr( Typ.s("Stack"))).
                
                    privt().fild( Typ.s("Object"), "goal").c().

                    publc().override().method( "startDocument").thrws( Typ.s("SAXException")).body().
                        call( DOT( NAME( "stack"), NAME("clear"))).r().c().

                    publc().override().method( "endDocument").thrws( Typ.s("SAXException")).body().r().c().

                    publc().method( TYP(name), "getGoal").body().
                        retrn( CAST( TYP(name), NAME("goal"))).r().c()
                  ;

        Block start = clazz.publc().override().method( "startElement").thrws( Typ.s("SAXException")).
                arg( Typ.string, "uri").
                arg( Typ.string, "localName").
                arg( Typ.string, "qName").
                arg( Typ.s("Attributes"), "attributes").body();

        Block end = clazz.publc().override().method( "endElement").thrws( Typ.s("SAXException")).
                arg( Typ.string, "uri").
                arg( Typ.string, "localName").
                arg( Typ.string, "qName").body();

        for ( Complex complex : structs.values() ) {
            complex.toFromXMLStart(start);
        }

        start.thrwIllegalArgument( Exp.bi( "+", Exp.s("unknown tag "), Exp.n( "qName" )));

        for ( Complex complex : structs.values() ) {
            complex.toFromXMLEnd(end);
        }


        return clazz.toString();
    }

    private String toJava() {

        String ret =
                "import org.xml.sax.Attributes;\n" +
                "import org.xml.sax.SAXException;\n" +
                "import org.xml.sax.helpers.DefaultHandler;\n" +
                "\n" +
                "import javax.xml.parsers.SAXParser;\n" +
                "import javax.xml.parsers.SAXParserFactory;\n" +
                "import javax.xml.stream.events.EntityDeclaration;\n" +
                "import java.io.File;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.List;\n" +
                "import java.util.Map;\n" +
                "import java.util.Stack;\n\n" +
                "   public class FromXML extends DefaultHandler {\n" +
                "\n" +
                "       private static class ListHelper<T> {\n" +
                "           private final List<T> list;\n" +
                "\n" +
                "           public ListHelper( List<T> list ) {\n" +
                "               this.list = list;\n" +
                "           }\n" +
                "\n" +
                "           public void add( T elem) {\n" +
                "               list.add( elem );\n" +
                "           }\n" +
                "       }\n" +
                "\n" +
                "       private Stack stack = new Stack();"+
                "       private Object goal;\n" +
                "       @Override\n" +
                "       public void startDocument() throws SAXException {\n" +
                "           stack.clear();\n" +
                "       }\n" +
                "\n" +
                "       @Override\n" +
                "       public void endDocument() throws SAXException{}           \n" +
                "\n" +
                "       @Override\n" +
                "       public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{\n" +
                "";

        for ( Complex complex : structs.values() ) {
            ret += complex.toSAXStart();
        }

        ret += "             throw new IllegalArgumentException(\"unknown tag \" + qName );\n" +
                "\n" +
                "       }\n" +
                "\n" +
                "       @Override\n" +
                "       public void endElement( String uri, String localName, String qName) throws SAXException {\n";

        for ( Complex complex : structs.values() ) {
            ret += complex.toSAXEnd();
        }

        ret += "       }\n" +
                "\n" +
                "       public Object getGoal() {\n" +
                "           return goal;\n" +
                "       }\n" +
                "}\n";

        return ret;
    }

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


//    public static class Elem {
//        private String anAtti;
//        public String get() {
//            return anAtti;
//        }
//        public void setAnAtti( String anAtti ) {
//            this.anAtti = anAtti;
//        }
//    }
//
//    public static class Duh {
//        private String mabe;
//        public String get() {
//            return mabe;
//        }
//        public void setMabe( String mabe ) {
//            this.mabe = mabe;
//        }
//    }
//
//    public static class Compl {
//        private List<Elem>  eles = new ArrayList<Elem>();
//        public  void add( Elem elem) {
//            eles.add( elem );
//        };
//        public List<Elem> getEles() {
//            return eles;
//        }
//        private Duh duh;
//        public Duh get() {
//            return duh;
//        }
//        public void setDuh( Duh duh ) {
//            this.duh = duh;
//        }
//    }
//
//
//
//    private static class FromXml extends DefaultHandler {
//
//        private static class ListHelper<T> {
//            private final List<T> list;
//
//            public ListHelper( List<T> list ) {
//                this.list = list;
//            }
//
//            public void add( T elem) {
//                list.add( elem );
//            }
//        }
//
//        private Stack stack = new Stack();
//        private Object goal;
//        @Override
//        public void startDocument() throws SAXException {
//            stack.clear();
//        }
//
//        @Override
//        public void endDocument() throws SAXException {}
//
//        @Override
//        public void startElement(String uri, String localName, String
//                qName, Attributes attributes) throws SAXException {
//
//            if ( qName.equals( "Compl" )) {
//                Compl elem = new Compl();
//                stack.push( elem );
//                return;
//            }
//
//            if ( qName.equals("Elem")) {
//                Elem elem = new Elem();
//                String att1 = attributes.getValue( "anAtti" );
//                if ( att1 != null ) {
//                    elem.setAnAtti( att1 );
//                } else {
//                    throw new IllegalArgumentException( "attribute antAtti is required" );
//                }
//
//                Object peek =  stack.peek();
//
//                if ( peek instanceof ListHelper ) {
//                    ((ListHelper<Elem>)peek).add( elem );
//                }
//
//                stack.push(elem );
//                return;
//            }
//
//            if ( qName.equals( "Duh"))  {
//                Duh elem = new Duh();
//                String att1 = attributes.getValue( "maybe" );
//                if ( att1 != null ) {
//                    elem.setMabe( att1 );
//                }
//
//                Object peek =  stack.peek();
//
//                if ( peek instanceof Compl ) {
//                    ((Compl)peek).setDuh( elem );
//                } else {
//                    throw new IllegalArgumentException( "duh is not member of " + peek.getClass() );
//                }
//
//                stack.push(elem );
//                return;
//
//            }
//
//            if ( qName.equals( "eles"))  {
//
//                Object peek =  stack.peek();
//
//                if ( peek instanceof Compl ) {
//                    stack.push( new ListHelper<Elem>( ((Compl)peek).getEles() ));
//                    return;
//                } else {
//                    throw new IllegalArgumentException( "duh is not member of " + peek.getClass() );
//                }
//            }
//
//            throw new IllegalArgumentException( "unknown tag " + qName );
//
//        }
//
//        @Override
//        public void endElement( String uri, String localName, String
//                qName) throws SAXException {
//            if ( qName.equals( "eles" ) ) {
//                goal = stack.pop();
//            }
//            if ( qName.equals( "Duh" ) ) {
//                goal = stack.pop();
//            }
//            if ( qName.equals( "Elem" ) ) {
//                goal = stack.pop();
//            }
//            if ( qName.equals( "Compl" ) ) {
//                goal = stack.pop();
//            }
//        }
//
//        public Object getGoal() {
//            return goal;
//        }
//    }


}

