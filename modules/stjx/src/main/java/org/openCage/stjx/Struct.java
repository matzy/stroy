package org.openCage.stjx;

import org.openCage.generj.*;
import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;

import java.util.ArrayList;
import java.util.List;

import static org.openCage.generj.BinOp.INSTANCEOF;
import static org.openCage.generj.BinOp.PLUS;
import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.Cast.CAST;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.GETTER;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.NameExpr.SETTER;
import static org.openCage.generj.NameExpr.TRUE;
import static org.openCage.generj.NameExpr.FALSE;
import static org.openCage.generj.Str.STR;
import static org.openCage.generj.Typ.TYP;
import static org.openCage.generj.Typ.TYPOF;
import static org.openCage.generj.Typ.STRING;
import static org.openCage.generj.UnOp.NOT;

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
    private String name;
    private List<Atti> attis = new ArrayList<Atti>();
    private List<Ref> complexs = new ArrayList<Ref>();
    private String interf;
    private boolean content;
    List<String> multiLines = new ArrayList<String>();
    private List<String> requiredMultiLines = new ArrayList<String>();

    public Struct(Stjx stjx, String name) {
        this.stjx = stjx;
        this.name = name;
    }

    public Struct string(String name) {
        attis.add( StringAtti.required( name ));
        return this;
    }

    public Struct multiLine(String name) {
        multiLines.add( name );
        requiredMultiLines.add( name );
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

    public ClassI toJava( String pack ) {
        Clazz clazz = new Clazz( pack, TYP(name) );

        //clazz.comment()

        clazz.imprt( "java.util.ArrayList" );
        clazz.imprt( "java.util.List" );

        if ( interf != null ) {
            clazz.implments( TYP(interf));
        }

        for ( Atti atti :attis ) {
            atti.toJavaProperty( clazz );
        }

        for ( String multi : multiLines ) {
            clazz.property( Typ.STRING, NAME(Strings.toFirstLower(multi)));
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
        clazz.property( TYP(name), NAME(Strings.toFirstLower(name)));
    }



    @Override
    public void toFromXMLStart(Block start) {
        Block thn = start.iff( CALL( DOT( NAME( "qName" ), NAME("equals")), STR(name) )).thn();

        thn.fild( TYP(name), NAME("elem") ).init( new NewExpr( TYP(name)));

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

            inner.fild( TYP("Object"), NAME("peek")).init( CALL( DOT( NAME( "stack"), NAME("peek"))));

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


        for ( String multi : multiLines ) {
            Block thnMulti = start.iff( CALL( DOT( NAME( "qName" ), NAME("equals")), STR(multi) )).thn();

            thnMulti.assign( NAME("getCharacters"), TRUE );
            thnMulti.retrn();

        }
    }



    public boolean uses(String name) {
        for ( Ref ref : complexs ) {
            if ( ref.getName().equals( name )) {
                return true;
            }
        }

        for ( String multi : multiLines ) {
            if ( multi.endsWith( name )) {
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
            }
        }

        for ( String multi : requiredMultiLines ) {
            thn.ifNull( CALL( DOT( CAST( TYP(name), NAME("goal")),
                                    GETTER( multi )))).thn().
                    thrwIllegalArgument( STR( name + ": required member " + multi + " not set"));
        }

        for ( String multi : multiLines ) {
            Block thnMulti = end.iff( CALL( DOT( NAME("qName"), NAME( "equals")), STR(multi) )).thn();

            thnMulti.fild( STRING, NAME("str")).init( STR("" ));

            Block stringbody = thnMulti.whle( INSTANCEOF( CALL( DOT(NAME("stack"), NAME("peek"))), STRING )).body();
            stringbody.assign( NAME("str"), PLUS( CAST( STRING, CALL( DOT(NAME("stack"), NAME("pop")))), NAME("str")));

            List<Complex> hasme = stjx.getUsers( multi );

            if ( !hasme.isEmpty()) {

                Block inner = thnMulti.iff( NOT( CALL( DOT( NAME( "stack"), NAME("empty"))))).thn();

                inner.fild( TYP("Object"), NAME("peek")).init( CALL( DOT( NAME( "stack"), NAME("peek"))));

                boolean list = false;
                for ( Complex complex : hasme ) {

                    if ( complex instanceof ListType) {
                        list = true;
                    } else {

                        String typeName = complex.getName();

                        inner.iff( INSTANCEOF( NAME("peek"), TYP(typeName))).
                                thn().
                                    call( DOT( CAST( TYP(typeName), NAME("peek")),
                                               SETTER( multi )),
                                          NAME("str"));
                    }
                }

                if ( list ) {
                    inner.thrw( TYP("IllegalStateException"), "TODO");
//                    inner.iff( INSTANCEOF( NAME("peek"), TYP("List"))).
//                            thn().
//                            call( DOT( CAST( TYPOF("List", TYP(className)), NAME("peek")),
//                                       NAME( "add" /*+ className*/ )),
//                                  NAME("elem"));
                }
            }


            thnMulti.assign( NAME("getCharacters"), FALSE );

        }

    }

    @Override
    public void toToXML( Clazz clazz) {

        Mesod mesod = clazz.publc().sttic().method( Typ.STRING, "toString" + name );

        String lower = Strings.toFirstLower(name);

        mesod.arg( Typ.STRING, NAME("prefix") ).arg( TYP(name), NAME(lower) ).
                body().
                    fild( STRING, NAME("ret")).init( NAME("prefix") ).
                    assignPlus( NAME("ret"), new Str("<" + name + " "));

        for ( Atti atti : attis ) {

            Call getAtti = getter( lower, atti.getName() );

            mesod.body().ifNotNull( getAtti ).
                    thn().assignPlus( NAME("ret"),
                            new BinOp( "+",
                                    new Str( atti.getName() + "=\\\""),
                                    new BinOp( "+",
                                            getAtti,
                                            Str.s("\\\" "))));
        }

        if ( complexs.isEmpty() && !content && multiLines.isEmpty() ) {
            mesod.body().assignPlus( NAME("ret"), STR("/>\\n"));
        } else {
            mesod.body().assignPlus( NAME("ret"), STR(">\\n"));

            for ( String multi : multiLines ) {
                Call getMulti = getter( lower, multi );

                mesod.body().ifNotNull( getMulti).
                        thn().assignPlus( NAME("ret"), PLUS( NAME( "prefix" ), STR("<" + multi + ">"), getMulti, STR("</" + multi +">\\n")));

            }

            for ( Ref ref : this.complexs ) {
                mesod.body().ifNotNull( getter( lower, ref.getName())).
                        thn().assignPlus( NAME("ret"), CALL( NAME("toString" + ref.getName()), PLUS( NAME("prefix"), Str.s("   ")), getter( lower, ref.getName())) );
            }

            if ( content ) {
                mesod.body().assignPlus( NAME("ret"), getter( lower, "content") );
            }

            mesod.body().assignPlus( NAME("ret"), PLUS( NAME("prefix"), Str.s( "</" + name + ">\\n")));
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

}
