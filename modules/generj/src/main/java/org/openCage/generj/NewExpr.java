package org.openCage.generj;


import org.openCage.lang.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewExpr implements Expr {
    private Typ typ;
    private List<Expr> args = new ArrayList<Expr>();

    public static NewExpr NEW( Typ typ, Expr ... args ) {
        return new NewExpr( typ, args );
    }

    public NewExpr( Typ typ, Expr ... args ) {
        this.typ = typ;
        if ( args.length > 0 ) {
            this.args.addAll( Arrays.asList( args ));
        }
    }

    public String toString() {
        if ( args.isEmpty() ) {
            return "new " + typ + "()";    
        }

        return "new " + typ + "( " + Strings.join( args ) + ")";
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }
}
