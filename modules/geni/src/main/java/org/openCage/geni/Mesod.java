package org.openCage.geni;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 17, 2010
 * Time: 9:35:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class Mesod {
    private Clazz clazz;
    private Typ ret;
    private String name;
    private String mod;

    private List<Expr> exprs = new ArrayList<Expr>();

    public Mesod(Clazz clazz, String mod, Typ retType, String name) {
        this.clazz = clazz;
        this.ret = retType;
        this.name = name;
        this.mod = mod;
    }

    public Mesod(Clazz clazz,String mod, String name) {
        this.clazz = clazz;
        this.ret = Typ.vod;
        this.name = name;
        this.mod = mod;
    }

    public String toString() {
        return "   " + mod + " " + ret + " " + name + "(){\n" +
               "   }\n";
    }

    public Clazz c() {
        return clazz;
    }

    public Mesod arg( String typ, String name ) {
        return this;
    }

    public Mesod expr( ) {
        Expr expr = new Expr();
        exprs.add( expr );

        return this;
    }

    public Mesod iff( Expr cond, Expr body )  {
        Expr expr = new Expr();
        exprs.add( expr );

        return this;        
    }
    
}
