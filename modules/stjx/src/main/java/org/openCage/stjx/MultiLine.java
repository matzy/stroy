package org.openCage.stjx;

import org.openCage.generj.*;
import org.openCage.lang.Strings;

import java.util.List;

import static org.openCage.generj.BinOp.INSTANCEOF;
import static org.openCage.generj.BinOp.PLUS;
import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.Cast.CAST;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.*;
import static org.openCage.generj.NameExpr.FALSE;
import static org.openCage.generj.Str.STR;
import static org.openCage.generj.Typ.STRING;
import static org.openCage.generj.Typ.TYP;
import static org.openCage.generj.Typ.TYPOF;
import static org.openCage.generj.UnOp.NOT;
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
public class MultiLine implements Complex {

    private Complex base;
    private String tagName;
    private Stjx stjx;

    
    public MultiLine( Stjx stjx, Complex base, String name) {
        this.base = base;
        this.tagName = name;
        this.stjx = stjx;
    }

    @Override
    public ClassI toJava(String pack) {
        return null; // not standalone
    }

    @Override
    public boolean uses(String name) {
        return false;
    }

    @Override
    public boolean usesEmbedded(String name) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getClassName() {
        return "String";
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    @Override
    public String toRnc() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setInterface(String name) {
        throw new Error("foo");
    }

    @Override
    public void toToXML(Clazz clazz) {
        Mesod mesod = clazz.publc().sttic().method( STRING, "toString" + toFirstUpper(tagName)  );

        String lower = Strings.toFirstLower(tagName);

        mesod.arg( Typ.STRING, NAME("prefix") ).arg( STRING, NAME(lower) ).
                body().
                    retrn( PLUS( NAME("prefix"), STR("<" + tagName + ">"), NAME(lower),STR("</" + tagName + ">\\n")));
    }

    @Override
    public void toJavaProperty(Clazz clazz) {
            clazz.property( STRING, NAME(Strings.toFirstLower(tagName)));
    }

    @Override
    public void toFromXMLStart(Block start) {
        Block thnMulti = start.iff( CALL( DOT( NAME( "qName" ), NAME("equals")), STR(tagName) )).thn();

        thnMulti.assign( NAME("getCharacters"), TRUE );
        thnMulti.retrn();
    }

    @Override
    public void toFromXMLEnd(Block end) {
//        for ( String multi : requiredMultiLines ) {
//            thn.ifNull( CALL( DOT( CAST( TYP(className), NAME("goal")),
//                                    GETTER( multi )))).thn().
//                    thrwIllegalArgument( STR( className + ": required member " + multi + " not set"));
//        }

        Block thnMulti = end.iff( CALL( DOT( NAME("qName"), NAME( "equals")), STR(tagName) )).thn();

        thnMulti.fild( STRING, NAME("str")).init( STR("" ));

        Block stringbody = thnMulti.whle( INSTANCEOF( CALL( DOT(NAME("stack"), NAME("peek"))), STRING )).body();
        stringbody.assign( NAME("str"), PLUS( CAST( STRING, CALL( DOT(NAME("stack"), NAME("pop")))), NAME("str")));

        List<Complex> hasme = stjx.getUsers( tagName );

        if ( !hasme.isEmpty()) {

            Block inner = thnMulti.iff( NOT( CALL( DOT( NAME( "stack"), NAME("empty"))))).thn();

            inner.fild( TYP("Object"), NAME("peek")).init( CALL( DOT( NAME( "stack"), NAME("peek"))));

            boolean list = false;
            for ( Complex complex : hasme ) {

                if ( complex instanceof ListType) {
                    list = true;
                } else if ( complex instanceof EmbeddedListType ) {

                    String baseClassName = ((EmbeddedListType)complex).getBase().getClassName();

                    inner.iff( INSTANCEOF( NAME("peek"), TYP(baseClassName))).thn().
                            call( DOT( CALL( DOT( CAST( TYP(baseClassName), NAME("peek")),
                                             GETTER( complex.getClassName() ))),
                                       NAME( "add")), 
                                  NAME("str"));


                } else {

                    String typeName = complex.getClassName();

                    inner.iff( INSTANCEOF( NAME("peek"), TYP(typeName))).
                            thn().
                            call( DOT( CAST( TYP(typeName), NAME("peek")),
                                    SETTER( tagName )),
                                    NAME("str"));
                }
            }

            if ( list ) {
                    inner.iff( INSTANCEOF( NAME("peek"), TYP("List"))).
                            thn().
                            call( DOT( CAST( TYPOF("List", STRING), NAME("peek")),
                                       NAME( "add" )),
                                  NAME("str"));
            }


            thnMulti.assign( NAME("getCharacters"), FALSE );
        }
    }
}
