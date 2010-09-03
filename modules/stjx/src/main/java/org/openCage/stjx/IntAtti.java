package org.openCage.stjx;

import org.openCage.generj.Block;
import org.openCage.generj.Clazz;
import org.openCage.generj.IfExpr;
import org.openCage.generj.Typ;
import org.openCage.lang.Strings;

import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.NameExpr.SETTER;
import static org.openCage.generj.Str.STR;
import static org.openCage.generj.Typ.TYP;

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
public class IntAtti implements Atti {
    private String name;
    private boolean optional;

    public IntAtti(String name, boolean optional) {
        this.name = name;
        this.optional = optional;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toRnc() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isOptional() {
        return optional;
    }

    @Override
    public void toJavaProperty(Clazz clazz) {
        clazz.property( TYP("Integer"), NAME(Strings.toFirstLower(name)));
    }

    @Override
    public void toFromXMLStart(Block block, String varName) {
        IfExpr ite = block.ifNotNull( CALL( DOT( NAME("attributes"), NAME( "getValue")), STR(name) ));

        ite.thn().call( DOT( NAME("elem"), SETTER(name)),
                        CALL( DOT( NAME("Integer"), NAME("valueOf")), CALL( DOT( NAME("attributes"), NAME( "getValue")), STR(name) )));

        if( !optional ) {
            ite.els().thrw( TYP("IllegalArgumentException"), varName + ": attribute " + name + " is required"  );
        }

    }

    public static Atti required(String name) {
        return new IntAtti( name, false );
    }

}
