package org.openCage.generj;



public class Exp {


    public static BinOp bi( String op, Expr left, Expr right ) {
        return new BinOp( op, left, right );
    }

    public static NameExpr n( String name ) {
        return new NameExpr( name );
    }

    public static Str s(String str) {
        return new Str( str );
    }
}
