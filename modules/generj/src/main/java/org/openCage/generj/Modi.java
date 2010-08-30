package org.openCage.generj;


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
