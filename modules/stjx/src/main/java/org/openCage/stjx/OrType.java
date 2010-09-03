package org.openCage.stjx;

import org.openCage.generj.*;
import org.openCage.lang.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openCage.generj.BinOp.INSTANCEOF;
import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.Cast.CAST;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.Str.STR;
import static org.openCage.generj.Typ.TYP;
import static org.openCage.generj.Typ.STRING;

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
        clazz.property( TYP(name), NAME(Strings.toFirstLower(name)));
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

        mesod.arg( STRING, NAME("prefix" )).arg( TYP(name), NAME(arg ));

        for ( String ref : alternatives ) {
            mesod.body().iff( INSTANCEOF( NAME(arg), TYP(ref) )).thn().
                    retrn( CALL( NAME("toString" + ref),
                                   NAME("prefix" ),
                                   CAST( TYP(ref), NAME(arg) )));
        }


        mesod.body().thrw( TYP("IllegalStateException"), "no a valid suptype of " + name );


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
            Complex comp = struct.getStjx().structs.get( alt );
            if ( comp == null ) {
                throw new IllegalArgumentException( alt + " used in or("+ name +") before declared as struct" );
            }

            comp.setInterface( name );
        }

        return struct;
    }

    public ClassI toJava(String pack) {
        Interf interf = new Interf( pack, TYP(name ));
        return interf;
    }
}
