package org.openCage.generj;

import org.openCage.lang.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Call implements Expr {
    private Callble name;
    private List<Expr> args = new ArrayList<Expr>();

    public Call( Callble name, Expr ... args ) {
        this.name = name;
        this.args.addAll( Arrays.asList( args ));
    }

    public String toString() {
        if ( args.isEmpty() ) {
            return name + "()";
        }
        
        return name + "( " + Strings.join( args ) + " )";
    }

    public static Call CALL( Callble name, Expr ... args ) {
        return new Call( name, args );
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }
}
