package org.openCage.stjx;

import org.openCage.generj.*;
import org.openCage.lang.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.Typ.STRING;
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
public class EnumType implements Complex {
    private Stjx stjx;
    private String name;
    private List<String> vals = new ArrayList<String>();

    public EnumType(Stjx stjx, String name, String... vals) {
        this.stjx = stjx;
        this.name = name;
        this.vals.addAll( Arrays.asList( vals ));
    }

    @Override
    public ClassI toJava(String pack) {
        return new Enm( pack, name, vals );
    }

    @Override
    public boolean uses(String name) {
        return false;  // no members
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
    public void setInterface(String name) {
        throw new IllegalArgumentException( "enum can't implement an interface" );
    }

    @Override
    public void toToXML(Clazz clazz) {
        // nix is used in attis
    }

    @Override
    public void toJavaProperty(Clazz clazz) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void toFromXMLStart(Block start) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void toFromXMLEnd(Block end) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
