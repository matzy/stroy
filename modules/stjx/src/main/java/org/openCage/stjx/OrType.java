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
import static org.openCage.lang.Strings.toFirstUpper;

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
    private String className;
    private String tagName;
    private Stjx stjx;
    private List<String> alternatives = new ArrayList<String>();

    public OrType(Stjx stjx, String name) {
        this.stjx = stjx;
        this.tagName = name;
        this.className = Strings.toFirstUpper( name );
    }

    @Override
    public void toJavaProperty(Clazz clazz) {
        clazz.property( TYP(className), NAME(Strings.toFirstLower(className)));
    }



    @Override
    public void toFromXMLStart(Block start) {
        start.iff( CALL( DOT( NAME( "qName" ), NAME("equals")), STR(tagName) )).thn().
                retrn();
    }

    public boolean uses(String name) {
        return alternatives.contains( name );
    }

    @Override
    public boolean usesEmbedded(String name) {
        return false;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getTagName() {
        return tagName;
    }


    public String toRnc() {
//        String ret = name + " = " + name + " {(";
//        String arg = "";
//        for ( String alt : alternatives) {
//            if ( !arg.isEmpty()) {
//                arg += ", ";
//            }
//
//            arg += alt;
//        }
//        ret += arg + ")}";
//
//        return ret;
        return null;
    }

    public void setInterface(String name) {
    }

    @Override
    public void toFromXMLEnd(Block end) {
        // nothing to do
    }

    @Override
    public List<String> getRefs() {
        return alternatives;
    }

    @Override
    public void toToXML(Clazz clazz) {
        Mesod mesod = clazz.publc().sttic().method( Typ.STRING, "toString" + toFirstUpper(className) );

        String arg = Strings.toFirstLower( className );

        mesod.arg( STRING, NAME("prefix" )).arg( TYP(className), NAME(arg ));

        for ( String ref : alternatives ) {
            mesod.body().iff( INSTANCEOF( NAME(arg), TYP(ref) )).thn().
                    retrn( CALL( NAME("toString" + toFirstUpper(ref)),
                                   NAME("prefix" ),
                                   CAST( TYP(ref), NAME(arg) )));
        }


        mesod.body().thrw( TYP("IllegalStateException"), "no a valid suptype of " + className );


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



    public void with( String ... names ) {
        alternatives.addAll( Arrays.asList( names ));

        for ( String alt : names ) {
            Complex comp = stjx.getComplex( alt );
            if ( comp == null ) {
                throw new IllegalArgumentException( alt + " used in or("+ className +") before declared as struct" );
            }

            comp.setInterface( className );
        }
    }

    public ClassI toJava(String pack, String rootName) {
        Interf interf = new Interf( pack, TYP(className ));
        return interf;
    }
}
