package org.openCage.generj;


/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 17, 2010
 * Time: 9:51:44 AM
 * To change this template use File | Settings | File Templates.
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

}
