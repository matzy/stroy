package org.openCage.stjx;

import org.openCage.generj.BinOp;
import org.openCage.generj.Block;
import org.openCage.generj.Call;
import org.openCage.generj.Cast;
import org.openCage.generj.Clazz;
import org.openCage.generj.Exp;
import org.openCage.generj.Mesod;
import org.openCage.generj.NameExpr;
import org.openCage.generj.NewExpr;
import org.openCage.generj.Str;
import org.openCage.generj.Typ;
import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;

import java.util.ArrayList;
import java.util.List;

import static org.openCage.generj.BinOp.INSTANCEOF;
import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.Cast.CAST;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.GETTER;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.NameExpr.SETTER;
import static org.openCage.generj.Str.STR;
import static org.openCage.generj.Typ.TYP;
import static org.openCage.generj.Typ.TYPOF;
import static org.openCage.generj.UnOp.NOT;

public class Struct implements Complex {
    private Stjx stjx;
    private String name;
    private List<Atti> attis = new ArrayList<Atti>();
    private List<Ref> complexs = new ArrayList<Ref>();
    private String interf;
    private boolean content;

    public Struct(Stjx stjx, String name) {
        this.stjx = stjx;
        this.name = name;
    }

    public Struct string(String name) {
        attis.add( StringAtti.required( name ));
        return this;
    }

    public Struct locale(String name) {
        attis.add( LocaleAtti.required( name ));
        return this;
    }

    public Struct integer(String s) {
        attis.add( IntAtti.required( name ));
        return this;
    }


    public Struct complex(String name) {
        complexs.add( Ref.required(name) );
        return this;
    }

//    public Struct keyVal(String mapName, String key, String complex) {
//        KeyVal ll = new KeyVal( mapName, key, complex );
//        stjx.structs.put( name, ll );
//        complexs.add( Ref.optional( name ));
//        return this;
//    }
//
//    public MapType map(String name) {
//        check( name );
//        MapType ll = new MapType( this, name );
//        stjx.structs.put( name, ll );
//        complexs.add( Ref.optional( name ));
//        return ll;
//    }

    public ListType list(String name) {

        check( name );

        ListType ll = new ListType( this, name );
        stjx.structs.put( name, ll );
        complexs.add( Ref.optional( name ));
        return ll;
    }

    private void check(String name) {
        if ( !Keywords.isAllowed( name )) {
            throw new IllegalArgumentException( name + " is a illegal name here" );
        }
    }

    public OrType or( String name ) {

        if ( name.charAt(0) != name.toUpperCase().charAt(0)) {
            throw new IllegalArgumentException( "or/interface type needs to be first letter uppercase not " + name );
        }

        OrType ot =  new OrType( this, name );
        stjx.structs.put( name, ot );
        complexs.add( Ref.required( name ));
        return ot;
    }
     public Optional optional() {
        return new Optional( this );
    }

    public String getType() {
        return name;
    }

    public Object toJava( String pack ) {
        Clazz clazz = new Clazz( pack, TYP(name) );

        clazz.imprt( "java.util.ArrayList" );
        clazz.imprt( "java.util.List" );

        if ( interf != null ) {
            clazz.implments( Typ.s(interf));
        }

        for ( Atti atti :attis ) {
            atti.toJavaProperty( clazz );
        }

        for ( Ref ref : complexs ) {
            Complex comp = stjx.structs.get( ref.getName() );

            if ( comp == null ) {
                throw new IllegalArgumentException( "unknown complex " + ref.getName() );
            }

            comp.toJavaProperty( clazz );
        }


        // todo toString

        
        return clazz;
    }

    @Override
    public void toJavaProperty(Clazz clazz) {
        clazz.property( TYP(name), Strings.toFirstLower(name));
    }


    public String toJava() {
        String ret = "import java.util.ArrayList;\n" +
                     "import java.util.List;\n" +
                     "public class " + name;


        if ( interf != null ) {
            ret += " implements " + interf + " ";
        }

        ret += " {\n";

        for ( Atti atti : attis ) {
            ret += atti.toJava();
        }

        for ( Ref ref : complexs ) {
            Complex comp = stjx.structs.get( ref.getName() );

            if ( comp == null ) {
                throw new IllegalArgumentException( "unknown complex " + ref.getName() );
            }

            ret += comp.toJavaDecl();
        }

        ret += "    public String toString() {\n" +
               "       return \"" + name + "(";
        for ( Atti atti : attis ) {
            ret += atti.getName() + ": \" + get" + Strings.toFirstUpper( atti.getName() ) + "() +\" ";
        }
        for ( Ref ref : complexs ) {
            ret += ref.getName();
        }
        ret += ")\";";
        ret += "    }\n";

        ret += "}\n";

        return ret;
    }

    public String toJavaDecl() {
        return Stjx.toJavaBeanAttribute( name, Strings.toFirstLower( name ));
    }

    @Override
    public void toFromXMLStart(Block start) {
        Block thn = start.iff( CALL( DOT( NAME( "qName" ), NAME("equals")), STR(name) )).thn();

        thn.fild( Typ.s(name), "elem" ).init( new NewExpr( Typ.s(name)));

        for ( Atti atti : attis ) {
            atti.toFromXMLStart( thn, name );
        }

        String className = name;
        if ( interf != null ) {
            className = interf;
        }

        List<Complex> hasme = stjx.getUsers( className );

        if ( !hasme.isEmpty()) {

            Block inner = thn.iff( NOT( CALL( DOT( NAME( "stack"), NAME("empty"))))).thn();

            inner.fild( TYP("Object"), "peek").init( CALL( DOT( NAME( "stack"), NAME("peek"))));

            boolean list = false;
            for ( Complex complex : hasme ) {

                if ( complex instanceof ListType) {
                    list = true;
                } else {

                    String typeName = complex.getName();

                    inner.iff( INSTANCEOF( NAME("peek"), TYP(typeName))).
                            thn().
                                call( DOT( CAST( TYP(typeName), NAME("peek")),
                                           SETTER( className )),
                                      NAME("elem"));
                }
            }

            if ( list ) {
                inner.iff( INSTANCEOF( NAME("peek"), TYP("List"))).
                        thn().
                        call( DOT( CAST( TYPOF("List", TYP(className)), NAME("peek")),
                                   NAME( "add" /*+ className*/ )),
                              NAME("elem"));
            }
        }

        thn.call( DOT(NAME("stack"), NAME("push")), NAME( "elem" ));

        thn.retrn();
    }


    public String toSAXStart() {
        String ret = "           if ( qName.equals(\"" + name + "\" )) {\n" +
                "               " + name + " elem = new " + name + "();\n";

        for ( Atti atti : attis ) {
            ret += atti.toSAXStart( name );
        }

        String className = name;
        if ( interf != null ) {
            className = interf;
        }

        List<Complex> hasme = stjx.getUsers( className );

        if ( !hasme.isEmpty()) {
            ret += "               if ( !stack.empty() ) {\n" +
                   "                  Object peek =  stack.peek();\n\n";


            boolean list = false;
            for ( Complex complex : hasme ) {

                if ( complex instanceof ListType) {
                    list = true;
                } else {

                    String typeName = complex.getName();

                    ret += "                  if ( peek instanceof "+ typeName +") {\n" +
                           "                     (("+ typeName+")peek).set" + className + "( elem );\n" +
                           "                  };\n";
                }


            }

            if ( list ) {
                ret +=
               "                  if ( peek instanceof ListHelper ) {\n" +
               "                      ((ListHelper<" + className +">)peek).add( elem );\n" +
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

        args += Strings.join( attis ).trans( new F1<String, Atti>() {
            public String call(Atti atti) {
                return atti.toRnc() + (atti.isOptional() ? "?" : "" );
            }
        });

        args += Strings.join( complexs ).trans( new F1<String, Ref>() {
            public String call(Ref ref) {
                return ref.getName() + (ref.isOptional() ? "?" : "");
            }
        }).startWithSeparator( !args.isEmpty() );


        return ret + args + "}";
    }

    public void setInterface(String name) {
        if ( interf != null ) {
            throw new IllegalArgumentException( "only one interface allowed" );
        }
        this.interf = name;
    }


    @Override
    public void toFromXMLEnd(Block end) {
        Block thn = end.iff( CALL( DOT( NAME("qName"), NAME( "equals")), STR(name) )).thn();

        thn.assign( NAME("goal"), CALL( DOT( NAME( "stack" ), NAME( "pop"))));

        for ( Ref ref : complexs ) {
            if ( !ref.isOptional() ) {
                thn.ifNull( CALL( DOT( CAST( TYP(name), NAME("goal")),
                                        GETTER( ref.getName() )))).thn().
                        thrwIllegalArgument( STR( name + ": required member " + ref.getName() + " not set"));

//                ret += "               if ( (("+name+")goal).get"+ Strings.toFirstUpper(ref.getName() )+"() == null ){\n" +
//                       "                  throw new IllegalArgumentException(\""+ name +" required member "+ ref.getName() +" not set \");\n" +
//                       "               }\n";
            }
        }

    }


    public String toSAXEnd() {

        String ret = "           if ( qName.equals( \"" + name + "\" ) ) {\n" +
                     "               goal = stack.pop();\n";

        for ( Ref ref : complexs ) {
            if ( !ref.isOptional() ) {
                ret += "               if ( (("+name+")goal).get"+ Strings.toFirstUpper(ref.getName() )+"() == null ){\n" +
                       "                  throw new IllegalArgumentException(\""+ name +" required member "+ ref.getName() +" not set \");\n" +
                       "               }\n";
            }
        }

        return ret +=  "           }\n";
    }

    @Override
    public void toToXML( Clazz clazz) {

        Mesod mesod = clazz.publc().sttic().method( Typ.string, "toString" + name );

        String lower = Strings.toFirstLower(name);

        mesod.arg( Typ.string, "prefix" ).arg( Typ.s(name), lower ).
                body().
                    fild( Typ.string, "ret").init( NameExpr.n("prefix") ).
                    assignPlus( NAME("ret"), new Str("<" + name + " "));

        for ( Atti atti : attis ) {

            Call getAtti = getter( lower, atti.getName() );

            mesod.body().iff( Exp.bi( "!=", getAtti, Exp.n("null"))).
                    thn().assignPlus( NAME("ret"),
                            new BinOp( "+",
                                    new Str( atti.getName() + "=\\\""),
                                    new BinOp( "+",
                                            getAtti,
                                            Str.s("\\\" "))));
        }

        if ( complexs.isEmpty() && !content ) {
            mesod.body().assignPlus( NAME("ret"), STR("/>\\n"));
        } else {
            mesod.body().assignPlus( NAME("ret"), STR(">\\n"));

            for ( Ref ref : this.complexs ) {
                mesod.body().iff( Exp.bi( "!=", getter( lower, ref.getName()), Exp.n("null"))).
                        thn().assignPlus( NAME("ret"), CALL( NAME("toString" + ref.getName()), Exp.bi("+", Exp.n("prefix"), Str.s("   ")), getter( lower, ref.getName())) );
            }

            if ( content ) {
                mesod.body().assignPlus( NAME("ret"), getter( lower, "content") );
            }

            mesod.body().assignPlus( NAME("ret"), Exp.bi( "+", Exp.n("prefix"), Str.s( "</" + name + ">\\n")));
        }

        mesod.body().retrn( Exp.n("ret "));
    }

    public static Call getter( String obj, String name ) {
        return CALL( DOT( NAME( Strings.toFirstLower( obj )), GETTER(name)));
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

    public Struct withContent() {
        this.content = true;
        return this;
    }

}
