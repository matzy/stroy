package org.openCage.geni;

import org.openCage.lang.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cnstr implements Expr {

    private Typ typ;
    private List<Expr> args = new ArrayList<Expr>();

    public Cnstr(Typ typ, Expr ... args ) {
        this.typ = typ;
        this.args.addAll( Arrays.asList( args ));
    }

    public String toString() {
        return "new " + typ + "( " + Strings.join( args ) + " )";
    }
}
