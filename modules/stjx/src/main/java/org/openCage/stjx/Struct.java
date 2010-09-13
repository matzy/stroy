package org.openCage.stjx;

import org.openCage.generj.*;
import org.openCage.lang.Strings;

import java.util.ArrayList;
import java.util.List;

import static org.openCage.generj.BinOp.INSTANCEOF;
import static org.openCage.generj.BinOp.PLUS;
import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.Cast.CAST;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.*;
import static org.openCage.generj.Str.STR;
import static org.openCage.generj.Typ.TYP;
import static org.openCage.generj.Typ.TYPOF;
import static org.openCage.generj.Typ.STRING;
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
public class Struct implements Complex {
    private Stjx stjx;
    private String className;
    private String tagName;
    private List<Atti> attis = new ArrayList<Atti>();
    private List<Ref> complexs = new ArrayList<Ref>();
    private String interf;
    private boolean content;
//    List<String> multiLines = new ArrayList<String>();
    private List<String> requiredMultiLines = new ArrayList<String>();

    public Struct(Stjx stjx, String name) {
        this.stjx = stjx;
        this.className = Strings.toFirstUpper( name );
        this.tagName = name;
    }

    public Struct string(String name) {
        attis.add( StringAtti.required( name ));
        return this;
    }

    public Struct enm( String name ) {
        attis.add( EnumAtti.required( name ));
        return this;
    }



    public Struct locale(String name) {
        attis.add( LocaleAtti.required( name ));
        return this;
    }

    public Struct integer(String name ) {
        attis.add( IntAtti.required( name ));
        return this;
    }


    public Struct complex(String name) {
        complexs.add( Ref.required(name) );
        return this;
    }

    public ListType list(String name) {

        check( name );

        ListType ll = new ListType( this, name );
        stjx.addComplex( ll );
        complexs.add( Ref.optional( name ));
        return ll;
    }

    public Struct multiLine(String name) {
        MultiLine ml = new MultiLine( stjx, this, name );
        stjx.addComplex( ml );
        complexs.add( Ref.required( name ));
//        multiLines.add( name );
//        requiredMultiLines.add( name );
        return this;
    }

    public Struct embeddedList(String name) {
        check( name );

        EmbeddedListType ll = new EmbeddedListType( this, name, false );
        stjx.addComplex( ll );
        complexs.add( Ref.optional( ll.getTagName() ));
        return this;
    }

    public Struct embeddedStringList(String name) {
        check( name );

        EmbeddedListType ll = new EmbeddedListType( this, name, true );
        stjx.addComplex( ll );
        complexs.add( Ref.optional( ll.getTagName() ));
        return this;
    }



    private void check(String name) {
        if ( !Keywords.isAllowed( name )) {
            throw new IllegalArgumentException( name + " is a illegal name here" );
        }
    }

    public Optional optional() {
        return new Optional( this );
    }

    public ClassI toJava( String pack ) {

        Clazz clazz = new Clazz( pack, TYP( className ));

        //clazz.comment()

        clazz.imprt( "java.util.ArrayList" );
        clazz.imprt( "java.util.List" );

        if ( interf != null ) {
            clazz.implments( TYP(interf));
        }

        for ( Atti atti :attis ) {
            atti.toJavaProperty( clazz );
        }

//        for ( String multi : multiLines ) {
//            clazz.property( Typ.STRING, NAME(Strings.toFirstLower(multi)));
//        }

        for ( Ref ref : complexs ) {
            Complex comp = stjx.getComplex( ref.getName() );

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
        clazz.property( TYP(className), NAME(Strings.toFirstLower(className)));
    }



    @Override
    public void toFromXMLStart(Block start) {
        Block thn = start.iff( CALL( DOT( NAME( "qName" ), NAME("equals")), STR(tagName) )).thn();

        thn.fild( TYP(className), NAME("elem") ).init( new NewExpr( TYP(className)));

        for ( Atti atti : attis ) {
            atti.toFromXMLStart( thn, className );
        }        
        thn.call( DOT(NAME("attributes"), NAME("check")));

        String className = this.className;
        if ( interf != null ) {
            className = interf;
        }

        List<Complex> hasme = stjx.getUsers( className );

        if ( !hasme.isEmpty()) {

            Block inner = thn.iff( NOT( CALL( DOT( NAME( "stack"), NAME("empty"))))).thn();

            inner.fild( TYP("Object"), NAME("peek")).init( CALL( DOT( NAME( "stack"), NAME("peek"))));

            boolean list = false;
            for ( Complex complex : hasme ) {

                if ( complex instanceof ListType) {
                    list = true;
                } else {

                    String typeName = complex.getClassName();

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

        List<Complex> hasEmbedded = stjx.getEmbeddedUsers( className );
        if ( !hasEmbedded.isEmpty()) {

            Block inner = thn.iff( NOT( CALL( DOT( NAME( "stack"), NAME("empty"))))).thn();

            inner.fild( TYP("Object"), NAME("peek")).init( CALL( DOT( NAME( "stack"), NAME("peek"))));

            for ( Complex complex : hasEmbedded ) {

                String typeName = complex.getClassName();

                inner.iff( INSTANCEOF( NAME("peek"), TYP(typeName))).
                        thn().
                            call( DOT( CALL( DOT( CAST( TYP(typeName), NAME("peek")),
                                       GETTER( className + "List"))), NAME("add")), 
                                  NAME("elem"));
            }

        }


        thn.call( DOT(NAME("stack"), NAME("push")), NAME( "elem" ));

        thn.retrn();


//        for ( String multi : multiLines ) {
//            Block thnMulti = start.iff( CALL( DOT( NAME( "qName" ), NAME("equals")), STR(multi) )).thn();
//
//            thnMulti.assign( NAME("getCharacters"), TRUE );
//            thnMulti.retrn();
//
//        }
    }



    public boolean uses(String name) {
        for ( Ref ref : complexs ) {
            if ( ref.getName().equalsIgnoreCase( name )) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean usesEmbedded(String name) {
        for ( Ref ref : complexs ) {
            Complex comp = stjx.getComplex( ref );

            if ( comp instanceof EmbeddedListType ) {
                if ( ((EmbeddedListType)comp).getOf().equals( name )) {
                    return true;
                }
            }
        }
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

//    public String getName() {
//        return name;
//    }

    public String toRnc() {
//        String ret = name + " = element " + name + " { ";
//
//
//        String args = "";
//
//        args += Strings.join( attis ).trans( new F1<String, Atti>() {
//            public String call(Atti atti) {
//                return atti.toRnc() + (atti.isOptional() ? "?" : "" );
//            }
//        });
//
//        args += Strings.join( complexs ).trans( new F1<String, Ref>() {
//            public String call(Ref ref) {
//                return ref.getName() + (ref.isOptional() ? "?" : "");
//            }
//        }).startWithSeparator( !args.isEmpty() );
//
//
//        return ret + args + "}";
        return null;
    }

    public void setInterface(String name) {
        if ( interf != null ) {
            throw new IllegalArgumentException( "only one interface allowed" );
        }
        this.interf = name;
    }


    @Override
    public void toFromXMLEnd(Block end) {
        Block thn = end.iff( CALL( DOT( NAME("qName"), NAME( "equals")), STR(tagName) )).thn();

        thn.assign( NAME("goal"), CALL( DOT( NAME( "stack" ), NAME( "pop"))));

        for ( Ref ref : complexs ) {
            if ( !ref.isOptional() ) {
                thn.ifNull( CALL( DOT( CAST( TYP(className), NAME("goal")),
                                        GETTER( ref.getName() )))).thn().
                        thrwIllegalArgument( STR( className + ": required member " + ref.getName() + " not set"));
            }
        }

        for ( String multi : requiredMultiLines ) {
            thn.ifNull( CALL( DOT( CAST( TYP(className), NAME("goal")),
                                    GETTER( multi )))).thn().
                    thrwIllegalArgument( STR( className + ": required member " + multi + " not set"));
        }

//        for ( String multi : multiLines ) {
//            Block thnMulti = end.iff( CALL( DOT( NAME("qName"), NAME( "equals")), STR(multi) )).thn();
//
//            thnMulti.fild( STRING, NAME("str")).init( STR("" ));
//
//            Block stringbody = thnMulti.whle( INSTANCEOF( CALL( DOT(NAME("stack"), NAME("peek"))), STRING )).body();
//            stringbody.assign( NAME("str"), PLUS( CAST( STRING, CALL( DOT(NAME("stack"), NAME("pop")))), NAME("str")));
//
//            List<Complex> hasme = stjx.getUsers( multi );
//
//            if ( !hasme.isEmpty()) {
//
//                Block inner = thnMulti.iff( NOT( CALL( DOT( NAME( "stack"), NAME("empty"))))).thn();
//
//                inner.fild( TYP("Object"), NAME("peek")).init( CALL( DOT( NAME( "stack"), NAME("peek"))));
//
//                boolean list = false;
//                for ( Complex complex : hasme ) {
//
//                    if ( complex instanceof ListType) {
//                        list = true;
//                    } else {
//
//                        String typeName = complex.getClassName();
//
//                        inner.iff( INSTANCEOF( NAME("peek"), TYP(typeName))).
//                                thn().
//                                    call( DOT( CAST( TYP(typeName), NAME("peek")),
//                                               SETTER( multi )),
//                                          NAME("str"));
//                    }
//                }
//
//                if ( list ) {
//                    inner.thrw( TYP("IllegalStateException"), "TODO");
////                    inner.iff( INSTANCEOF( NAME("peek"), TYP("List"))).
////                            thn().
////                            call( DOT( CAST( TYPOF("List", TYP(className)), NAME("peek")),
////                                       NAME( "add" /*+ className*/ )),
////                                  NAME("elem"));
//                }
//            }
//
//
//            thnMulti.assign( NAME("getCharacters"), FALSE );
//
//        }

    }

    @Override
    public void toToXML( Clazz clazz) {

        Mesod mesod = clazz.publc().sttic().method( Typ.STRING, "toString" + toFirstUpper(className) );

        String lower = Strings.toFirstLower(className);

        mesod.arg( Typ.STRING, NAME("prefix") ).arg( TYP(className), NAME(lower) ).
                body().
                    fild( STRING, NAME("ret")).init( NAME("prefix") ).
                    assignPlus( NAME("ret"), new Str("<" + tagName + " "));

        for ( Atti atti : attis ) {

            Call getAtti = getter( lower, atti.getName() );

            mesod.body().ifNotNull( getAtti ).
                    thn().assignPlus( NAME("ret"),
                            PLUS( STR( atti.getName() + "=\\\""), getAtti, STR("\\\" ")));
        }

        if ( complexs.isEmpty() && !content /* && multiLines.isEmpty() */) {
            mesod.body().assignPlus( NAME("ret"), STR("/>\\n"));
        } else {
            mesod.body().assignPlus( NAME("ret"), STR(">\\n"));

//            for ( String multi : multiLines ) {
//                Call getMulti = getter( lower, multi );
//
//                mesod.body().ifNotNull( getMulti).
//                        thn().assignPlus( NAME("ret"), PLUS( NAME( "prefix" ), STR("<" + multi + ">"), getMulti, STR("</" + multi +">\\n")));
//
//            }

            for ( Ref ref : this.complexs ) {
                mesod.body().ifNotNull( getter( lower, ref.getName())).
                        thn().assignPlus( NAME("ret"), CALL( NAME("toString" + toFirstUpper(ref.getName())), PLUS( NAME("prefix"), Str.s("   ")), getter( lower, ref.getName())) );
            }

            if ( content ) {
                mesod.body().assignPlus( NAME("ret"), getter( lower, "content") );
            }

            mesod.body().assignPlus( NAME("ret"), PLUS( NAME("prefix"), Str.s( "</" + tagName + ">\\n")));
        }

        mesod.body().retrn( NAME("ret"));
    }

    public static Call getter( String obj, String name ) {
        return CALL( DOT( NAME( Strings.toFirstLower( obj )), GETTER(name)));
    }

    public Stjx getStjx() {
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

    public EmbeddedMultiple zeroOrMore() {
        return new EmbeddedMultiple( this );
    }

    public String toString() {
        String ret = "";

        ret += "Struct(" + className + "/" + tagName +")";

        return ret;
    }
}
