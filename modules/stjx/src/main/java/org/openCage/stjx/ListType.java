package org.openCage.stjx;

import org.openCage.generj.*;
import org.openCage.lang.Strings;

import java.util.List;

import static org.openCage.generj.BinOp.INSTANCEOF;
import static org.openCage.generj.BinOp.PLUS;
import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.Cast.CAST;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.GETTER;
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
public class ListType implements Complex {
    private Struct struct;
    private String name;
    private String of;

    public ListType( Struct struct, String name) {
        this.struct = struct;
        this.name = name;
    }

    public Struct of(String typName ) {
        this.of = typName;
        return struct;
    }

    public String getType() {
        return "List<" + of + ">";
    }

    public String toJava() {
        return "";
    }


    public String toJavaDecl() {
        return "   private List<" + of + ">  " + name + " = new ArrayList<" + of + ">();\n" +
//            "   public  void add" + Strings.toFirstUpper( name ) + "( " + of + " " + Strings.toFirstLower(of) + ") {\n" +
//            "      " + name + ".add( " + Strings.toFirstLower( of) + " );\n" +
//            "   };\n" +
                "   public List<"+ of + "> get" + Strings.toFirstUpper( name ) + "() {\n" +
                "      return " + name + ";\n" +
                "   }\n"+
                "";

    }

    @Override
    public ClassI toJava(String pack) {
        return null;
    }


    @Override
    public void toFromXMLStart(Block start) {

        Block thn = start.iff( CALL( DOT( NAME( "qName" ), NAME("equals")), STR(name) )).thn();

        thn.iff( CALL( DOT( NAME("stack"), NAME("empty")))).thn().
                thrwIllegalArgument( STR( name + ": needs to be in complex type"));

        thn.fild( TYP("Object"), NAME("peek")).init( CALL( DOT( NAME("stack"), NAME("peek"))));

        List<Complex> users = struct.getStjx().getUsers( name );
        for ( Complex comp : users ) {

            thn.iff( INSTANCEOF( NAME("peek"), TYP(comp.getName()))).thn().
                    call( DOT( NAME("stack"), NAME("push")),
                            CALL( DOT( CAST( TYP(comp.getName()), NAME("peek")),
                                       GETTER( name )))).
                    retrn();



//            ret +=         "\n" +
//                    "              if ( peek instanceof "+ comp.getName() +" ) {\n" +
//                    "                  stack.push( new ListHelper<"+ of +">( (("+ comp.getName() +")peek).get" + Strings.toFirstUpper( name )+ "() ));\n" +
//                    "                  return;\n" +
//                    "              }\n";
//

        }
        thn.thrwIllegalArgument( PLUS( STR( name + " is not a member of " ), CALL( DOT( NAME("peek"), NAME("getClass")))));
    }

    public String toSAXStart() {

        List<Complex> users = struct.getStjx().getUsers( name );

        String ret = "          if ( qName.equals( \""+ name + "\"))  {\n" +
                "             if ( stack.empty() ) {\n" +
                "                throw new IllegalArgumentException( \""+name+" needs to be in a complex type \");\n" +
                "             }\n";


        ret +=       "             Object peek =  stack.peek();\n";

        for ( Complex comp : users ) {

            ret +=         "\n" +
                    "              if ( peek instanceof "+ comp.getName() +" ) {\n" +
                    "                  stack.push( new ListHelper<"+ of +">( (("+ comp.getName() +")peek).get" + Strings.toFirstUpper( name )+ "() ));\n" +
                    "                  return;\n" +
                    "              }\n";


        }
        ret +=  "              throw new IllegalArgumentException( \""+ name +" is not member of \" + peek.getClass() );\n";

        ret += "          }\n";

        return ret;
    }

    public boolean uses(String name) {
        return of.equals( name );
    }

    public String getName() {
        return name;
    }

    public String toRnc() {
        return name + " = element " + name +" { " + of + "* }";
    }

    public void setInterface(String name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void toFromXMLEnd(Block end) {
        end.iff( CALL( DOT( NAME( "qName" ), NAME("equals")), STR(name) )).thn().
            assign( NAME("goal"), CALL( DOT( NAME("stack"), NAME("pop"))));
    }

    public String toSAXEnd() {
        return "           if ( qName.equals( \"" + name + "\" ) ) {\n" +
                "               goal = stack.pop();\n" +
                "           }\n";
    }

    @Override
    public void toToXML( Clazz clazz ) {

        Mesod mesod = clazz.publc().sttic().method( STRING, "toString" + name );

        mesod.arg( STRING, NAME("prefix")).arg( Typ.of("List", TYP(this.of)), NAME(name ));

        mesod.body().iff( CALL( DOT( NAME(name), NAME("isEmpty" )))).
                thn().retrn( Str.s(""));

        mesod.body().
                fild( STRING, NAME("ret")).init( NAME("prefix") ).
                assignPlus( NAME("ret"), Exp.s("<" + name + ">\\n"));

        mesod.body().fr( TYP(of), "vr",  NAME( name )).body().
                assignPlus( NAME("ret"), CALL( NAME("toString" + of),
                        PLUS( NAME("prefix"), STR("   ")),
                        NAME("vr")) );



        mesod.body().assignPlus( NAME("ret"), PLUS( NAME("prefix"), STR( "</"+name+">\\n" ) ));

        mesod.body().retrn( NAME("ret"));
    }


    @Override
    public void toJavaProperty(Clazz clazz) {
        clazz.property( Typ.of( "List", TYP(of) ), NAME(Strings.toFirstLower(name)), new NewExpr( Typ.of("ArrayList", TYP(of))));

    }


}
