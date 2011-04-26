package org.openCage.stjx;

import org.openCage.generj.*;
import org.openCage.lang.Strings;

import java.util.Collections;
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
public class ListType implements Complex {
    private String name;
    private String of;
    private String ofName;
    private Stjx stjx;

    public ListType( Struct struct, String name, String of, boolean ofString ) {
        this.stjx = struct.getStjx();
        this.name = name;

        if ( ofString ) {
            MultiLine ml = new MultiLine( stjx, this, of );
            struct.getStjx().addComplex( ml );
            this.of = "String";
            ofName = of;

        } else {
            this.of = of;
            this.ofName = of;
        }
    }

//    public Struct of(String typName ) {
//        this.of = typName;
//        this.ofName = typName;
//        return struct;
//    }

//    public Struct ofStrings(String name) {
//        MultiLine ml = new MultiLine( struct.getStjx(), this, name );
//        struct.getStjx().addComplex( ml );
//        of = "String";
//        ofName = name;
//        return struct;
//    }

    @Override
    public ClassI toJava(String pack, String rootName) {
        return null;    // not standalone
    }


    @Override
    public void toFromXMLStart(Block start) {

        Block thn = start.if_( CALL( DOT( NAME( "qName" ), NAME("equals")), STR(name) ))._then();

        thn.if_( CALL( DOT( NAME("stack"), NAME("empty"))))._then().
                throwIllegalArgument( STR( name + ": needs to be in complex type"));

        thn.field( TYP("Object"), NAME("peek")).init( CALL( DOT( NAME("stack"), NAME("peek"))));

        List<Complex> users = stjx.getUsers( name );
        for ( Complex comp : users ) {

            thn.if_( INSTANCEOF( NAME("peek"), TYP(comp.getClassName())))._then().
                    call( DOT( NAME("stack"), NAME("push")),
                            CALL( DOT( CAST( TYP(comp.getClassName()), NAME("peek")),
                                       GETTER( name )))).
                    return_();
        }
        thn.throwIllegalArgument( PLUS( STR( name + " is not a member of " ), CALL( DOT( NAME("peek"), NAME("getClass")))));
    }

    public boolean uses(String name) {
        return ofName.equals( name );
    }

    @Override
    public boolean usesEmbedded(String name) {
        return false;
    }

    @Override
    public String getClassName() {
        return name;
    }

    @Override
    public String getTagName() {
        return name;
    }

//    public String getName() {
//        return name;
//    }

    public String toRnc() {
        return name + " = element " + name +" { " + of + "* }";
    }

    public void setInterface(String name) {
        throw new IllegalArgumentException( "no inheritance for String" );
    }

    @Override
    public void toFromXMLEnd(Block end) {
        end.if_( CALL( DOT( NAME( "qName" ), NAME("equals")), STR(name) ))._then().
            assign( NAME("goal"), CALL( DOT( NAME("stack"), NAME("pop"))));
    }

    @Override
    public List<String> getRefs() {
        if ( of == null ) {
            int i = 0;
        }
        if ( of.equals("String")) {
            return Collections.EMPTY_LIST;
        }
        return Collections.singletonList( of );
    }

    @Override
    public void toToXML( Class_ clazz ) {

        Method_ mesod = clazz.public_()._static().method( STRING, "toString" + toFirstUpper(name) );

        mesod.arg( STRING, NAME("prefix")).arg( Typ.of("List", TYP(this.of)), NAME(name ));

        mesod.body().if_( CALL( DOT( NAME(name), NAME("isEmpty" )))).
                _then().return_( Str.s(""));

        mesod.body().
                field( STRING, NAME("ret")).init( NAME("prefix") ).
                assignPlus( NAME("ret"), Exp.s("<" + name + ">\\n"));

        mesod.body().for_( TYP(of), "vr",  NAME( name )).body().
                assignPlus( NAME("ret"), CALL( NAME("toString" + toFirstUpper(ofName)),
                        PLUS( NAME("prefix"), STR("   ")),
                        NAME("vr")) );



        mesod.body().assignPlus( NAME("ret"), PLUS( NAME("prefix"), STR( "</"+name+">\\n" ) ));

        mesod.body().return_( NAME("ret"));
    }


    @Override
    public void toJavaProperty(Class_ clazz) {
        clazz.property( Typ.of( "List", TYP(of) ), NAME(Strings.toFirstLower(name)), new NewExpr( Typ.of("ArrayList", TYP(of))));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListType listType = (ListType) o;

        if (name != null ? !name.equals(listType.name) : listType.name != null) return false;
        if (of != null ? !of.equals(listType.of) : listType.of != null) return false;
        if (ofName != null ? !ofName.equals(listType.ofName) : listType.ofName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (of != null ? of.hashCode() : 0);
        result = 31 * result + (ofName != null ? ofName.hashCode() : 0);
        return result;
    }
}
