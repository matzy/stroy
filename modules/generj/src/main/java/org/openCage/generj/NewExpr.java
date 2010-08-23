package org.openCage.generj;


import org.openCage.lang.Strings;

import java.util.Arrays;
import java.util.List;

public class NewExpr implements Expr {
    private Typ typ;
    private List<String> args;

    public NewExpr( Typ typ, String ... args ) {
        this.typ = typ;
        this.args.addAll( Arrays.asList( args ));
    }

    public String toString() {
        return "new " + typ + "( " + Strings.join( args ) + ")";
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }
}
