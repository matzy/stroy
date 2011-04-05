package org.openCage.generj;

import java.util.ArrayList;
import java.util.List;

import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.Typ.TYP;

public class Package {

    private List<Class_> classes = new ArrayList<Class_>();

    public static Package PACKAGE( NameExpr name ) {
        return new Package( name );
    }

    public static Package PACKAGE( String name ) {
        return new Package( NAME(name) );
    }

    public static Package valueOf( String dotted ) {
        String[] names = dotted.split("\\.");

        Package ret = PACKAGE( names[0] );

        for ( int i = 1; i < names.length; ++i ) {
            ret = ret.dot( names[i]);
        }

        return ret;
    }

    private final Package parent;
    private final String name;

    public Package( NameExpr name ) {
        parent = null;
        this.name = name.toString();
    }

    public Package( Package parent, NameExpr name ) {
        this.parent = parent;
        this.name = name.toString();
    }


    public Class_ class_( String name ) {
        return class_( TYP(name));
    }

    public Class_ class_(  Typ name ) {

        Class_ cl = new Class_( this, name );
        classes.add( cl );

        return cl;
    }

    public Package dot( NameExpr name ) {
        return new Package( this, name );
    }

    public Package dot( String name ) {
        return new Package( this, NAME(name) );
    }

    public String toString() {
        if ( parent != null ) {
            return parent.toString() + "." + name;
        }

        return name;
    }

}
