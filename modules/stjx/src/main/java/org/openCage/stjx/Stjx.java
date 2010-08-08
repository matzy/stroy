package org.openCage.stjx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 5, 2010
 * Time: 2:24:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stjx {

    public static void main(String[] args) {
        Stjx stjx = new Stjx("Artifact");

//       System.out.println( stjx.ding( "Elem").string( "anAtti" ).toJava() );
//       System.out.println( stjx.ding(
//"Duh").optional().string("mabe").toJava() );
//       System.out.println( stjx.ding( "Compl").list( "eles" ).of(
//"Elem" ).complex("Duh").toJava());

        //System.out.println( stjx.toJava());

        stjx.ding( "ArtifactRef" ).
                string( "groupId" ).
                string( "name" ).
                string( "scope" );

        stjx.ding( "Author" ).
                string("name").
                optional().string( "email" );

        stjx.ding( "Module" );
        stjx.ding( "Extern" );

        stjx.ding( "Artifact" ).list( "depends" ).of( "ArtifactRef" ).
                string( "groupId" ).
                string( "name" ).
                string( "version" ).
                list( "authors" ).of( "Author" ).
                or( "Application" ).with( "Module", "Extern" );

        System.out.println( stjx.toRnc());
//        stjx.generate();
//
//        try {
//            JAXBContext.newInstance("");
//        } catch (JAXBException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }


//        try {
//            // Neuen SAX-Parser erzeugen
//            SAXParserFactory factory   = SAXParserFactory.newInstance();
//            SAXParser saxParser = factory.newSAXParser();
//
//            // XML Datei parsen, die entsprechenden Methoden des DefaultHandler
//            // werden als Callback aufgerufen.
//            saxParser.parse(new File("/Users/stephan/Documents/prs/stroy-10/modules/adt/src/main/java/org/openCage/adt/Test2.xml"), new
//                    FromXml());
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }

    }

    private void generate() {


        {
            File outFile = new File( "/Users/stephan/projects/stroy-7/modules/adt/src/main/java/org/openCage/adt/FromXML.java");
            FileWriter out = null;
            try {
                out = new FileWriter(outFile);
                out.write( toJava() );
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for ( Complex cop : dings.values() ) {
            if ( cop.getType().startsWith( "List")) {
                // ignore
            } else {
                File outFile = new File( "/Users/stephan/projects/stroy-7/modules/adt/src/main/java/org/openCage/adt/" + cop.getName() + ".java");
                FileWriter out = null;
                try {
                    out = new FileWriter(outFile);
                    out.write( "package org.openCage.adt;\n" + cop.toJava() );
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    private String toJava() {

//        for ( Complex comp : this.dings.values() ) {
//            if ( comp instanceof OrType ) {
//
//            }
//        }

        String ret = "package org.openCage.adt;\n\n" +
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

        for ( Complex complex : dings.values() ) {
            ret += complex.toSAXStart();
        }

        ret += "             throw new IllegalArgumentException(\"unknown tag \" + qName );\n" +
                "\n" +
                "       }\n" +
                "\n" +
                "       @Override\n" +
                "       public void endElement( String uri, String localName, String qName) throws SAXException {\n";

        for ( Complex complex : dings.values() ) {
            ret += "           if ( qName.equals( \"" +
                    complex.getName() + "\" ) ) {\n" +
                    "               goal = stack.pop();\n" +
                    "           }\n";
        }

        ret += "       }\n" +
                "\n" +
                "       public Object getGoal() {\n" +
                "           return goal;\n" +
                "       }\n" +
                "}\n";

        return ret;
    }

    Map<String,Complex> dings = new HashMap<String,Complex>();
    private String name;


    public Stjx(String name ) {
        this.name = name;
    }

    public Struct ding(String name) {
        Struct struct = new Struct( this, name );
        dings.put( name, struct);
        return struct;
    }

    public List<Complex> getUsers( String name ) {
        List<Complex> users = new ArrayList<Complex>();
        for ( Complex complex : dings.values() ) {
            if ( complex.uses( name )) {
                users.add( complex );
            }
        }
        return users;
    }

    public String toRnc() {
        String ret = "default namespace = \"\"\n\n";

        ret += "start = " + name + "\n";

        for ( Complex comp : dings.values() ) {
            ret += comp.toRnc() + "\n";
        }

        return ret;
    }


    public static boolean isValidType( String name ) {
        return name.charAt( 0 ) == name.toUpperCase().charAt(0);
    }

    public static String toFirstLower( String name ) {
        return "" + name.toLowerCase().charAt(0) + name.substring(1);
    }

    public static String toFirstUpper(String name) {
        return "" + name.toUpperCase().charAt(0) + name.substring(1);
    }

    public static String toJavaBeanAttribute( String type, String name ) {
        String ret = "";
        ret += "   private " + type + " " + name + ";\n";
        ret += "   public "+ type + " get" + toFirstUpper( name ) + "() {\n";
        ret += "      return " + name + ";\n";
        ret += "   }\n";
        ret += "   public void set" + toFirstUpper( name ) + "( " + type
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

