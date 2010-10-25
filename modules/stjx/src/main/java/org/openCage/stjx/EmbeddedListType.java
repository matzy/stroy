package org.openCage.stjx;

import org.openCage.generj.*;
import org.openCage.lang.Strings;

import java.util.Collections;
import java.util.List;

import static org.openCage.generj.BinOp.PLUS;
import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.NewExpr.NEW;
import static org.openCage.generj.Str.STR;
import static org.openCage.generj.Typ.STRING;
import static org.openCage.generj.Typ.TYP;
import static org.openCage.generj.Typ.TYPOF;
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
public class EmbeddedListType implements Complex {
    private Struct base;
    private String of;
    private String ofName;
    private String listName;

    public EmbeddedListType(Struct base, String name, boolean ofString ) {
        this.base = base;
        if ( ofString ) {
            this.of = "String";
        } else {
            this.of = Strings.toFirstUpper(name);
        }
        this.ofName = name;
        this.listName = Strings.toFirstLower(name) + "List";
    }

    @Override
    public ClassI toJava(String pack, String rootName) {
        return null; // not standalone
    }

    @Override
    public boolean uses(String name) {
        return ofName.equals( name );
    }

    @Override
    public boolean usesEmbedded(String name) {
        return false;
    }

    @Override
    public String getClassName() {
        return listName;
    }

    @Override
    public String getTagName() {
        return listName;
    }


    @Override
    public String toRnc() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setInterface(String name) {
        throw new IllegalStateException( "no interface for embedded list" );
    }

    @Override
    public void toToXML(Clazz clazz) {
        Mesod mesod = clazz._public()._static().method( STRING, "toString" + toFirstUpper(listName) );

         mesod.arg( STRING, NAME("prefix")).arg( TYPOF("List", TYP(this.of)), NAME(ofName ));

         mesod.body().
                 field( STRING, NAME("ret")).init(STR(""));

         mesod.body()._for( TYP(of), "vr",  NAME( ofName )).body().
                 assignPlus( NAME("ret"), CALL( NAME("toString" + toFirstUpper(ofName)),
                         PLUS( NAME("prefix"), STR("   ")),
                         NAME("vr")) );



//         mesod.body().assignPlus( NAME("ret"), PLUS( NAME("prefix"), STR( "</"+name+">\\n" ) ));

         mesod.body()._return( NAME("ret"));
     }

    @Override
    public void toJavaProperty(Clazz clazz) {
        clazz.property( Typ.of( "List", TYP(of) ), NAME( listName ), NEW( TYPOF("ArrayList", TYP(of))));
    }



    @Override
    public void toFromXMLStart(Block start) {
        // nothing to do, embedded
    }

    @Override
    public void toFromXMLEnd(Block end) {
        // nothing to do, embedded
    }

    @Override
    public List<String> getRefs() {
        return Collections.singletonList( of );
    }

    public String getOf() {
        return of;
    }

    public Struct getBase() {
        return base;
    }
}
