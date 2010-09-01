package org.openCage.stjx;

import org.openCage.generj.*;
import org.openCage.lang.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.Cast.CAST;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.Str.STR;
import static org.openCage.generj.Typ.TYP;

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

    @Override
    public void toJavaProperty(Clazz clazz) {
        clazz.property( Typ.s(name), Strings.toFirstLower(name));
    }



    @Override
    public void toFromXMLStart(Block start) {
        start.iff( CALL( DOT( NAME( "qName" ), NAME("equals")), STR(name) )).thn().
                retrn();
    }
    public String toSAXStart() {
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

    @Override
    public void toFromXMLEnd(Block end) {
        // nothing to do
    }
    public String toSAXEnd() {
        return ""; // nohing to do
    }

    @Override
    public void toToXML(Clazz clazz) {
        Mesod mesod = clazz.publc().sttic().method( Typ.STRING, "toString" + name );

        String arg = Strings.toFirstLower( name );

        mesod.arg( Typ.STRING, "prefix" ).arg( Typ.s(name), arg );

        for ( String ref : alternatives ) {
            mesod.body().iff( Exp.bi( "instanceof", Exp.n(arg), Exp.n(ref) )).thn().
                    retrn( CALL( NAME("toString" + ref),
                                   Exp.n("prefix" ),
                                   CAST( TYP(ref), Exp.n(arg) )));
        }


        mesod.body().thrw( Typ.s("IllegalStateException"), "no a valid suptype of " + name );


//        mesod.arg( Typ.STRING, "prefix" ).arg( Typ.of("List", Typ.s(this.of)), name ).
//                body().
//                    fild( Typ.STRING, "ret").init( NameExpr.n("prefix") ).
//                    assignPlus( "ret", Exp.s("<" + name + ">\\n"));
//
//        mesod.body().fr( Typ.s(of), "vr",  Exp.n( name )).body().
//                assignPlus( "ret", Call.c( "toString" + of,
//                                                Exp.bi("+", Exp.n("prefix"), Exp.s("   ")),
//                                                Exp.n("vr")) );
//
//
//
//        mesod.body().assignPlus( "ret", Exp.bi( "+", Exp.n("prefix"), Exp.s( "</"+name+">\\n" ) ));
        
//        mesod.body().retrn( Exp.n("ret "));
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

    public Object toJava(String pack) {
        Interf interf = new Interf( pack, Typ.s(name ));
        return interf;
    }
}
