package org.openCage.geni;

import org.openCage.lang.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Call implements Expr {
    private String name;
    private List<Expr> args = new ArrayList<Expr>();

    public Call( String name, Expr ... args ) {
        this.name = name;
        this.args.addAll( Arrays.asList( args ));
    }

    public String toString() {
        return name + "( " + Strings.join( args ) + " )";
    }

    public static Call c( String name, Expr ... args ) {
        return new Call( name, args );
    }
}
