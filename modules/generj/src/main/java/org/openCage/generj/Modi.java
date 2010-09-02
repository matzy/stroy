package org.openCage.generj;


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
public class Modi {

    private String mod;
    private Clazz clazz;

    public Modi( String typ, Clazz clazz  ) {
        this.mod = typ;
        this.clazz = clazz;
    }

    public Mesod method( Typ retType, String name ) {
        Mesod mes = new Mesod( clazz, mod, retType, name );

        clazz.mesods.add( mes );

        return mes;
    }

    public Mesod method( String name ) {
        Mesod mes = new Mesod( clazz, mod, name );

        clazz.mesods.add( mes );

        return mes;
    }


    public Fild<Clazz> fild( Typ typ, String name ) {
        Fild<Clazz> fild = new Fild<Clazz>( clazz, mod, typ, name );
        clazz.filds.add( fild );        
        return  fild;
    }

    public Mesod cnstr() {
        Mesod mes = new Mesod( clazz, mod, null, clazz.name.getName() );

        clazz.mesods.add( mes );

        return mes;
    }

    public Clazz clazz(Typ typ) {
        Clazz subClazz = new Clazz( clazz, typ );
        clazz.innerClazzes.add( subClazz );
        return subClazz;
    }


    public Modi sttic() {
        mod += " static ";
        return this;
    }

    public Modi override() {
        mod = "@Override " + mod;
        return this;
    }
}
