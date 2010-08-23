package org.openCage.generj;


import com.sun.jndi.dns.DnsName;
import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;
import org.openCage.lang.structure.T2;

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
    private Typ retType;
    private String name;
    private String mod;

//    private List<Statement> statements = new ArrayList<Statement>();
    private List<T2<Typ,String>> args = new ArrayList<T2<Typ, String>>();


    private Block<Mesod> body;
    private List<String> typeNames = new ArrayList<String>();

    public Mesod(Clazz clazz, String mod, Typ retType, String name) {
        this.clazz = clazz;
        this.retType = retType;
        this.name = name;
        this.mod = mod;
    }

    public Mesod(Clazz clazz,String mod, String name) {
        this.clazz = clazz;
        this.retType = Typ.vooid;
        this.name = name;
        this.mod = mod;
    }

    public String toString() {
        String ret = "\n   " + mod + " ";

        ret += Strings.join( typeNames ).prefix( "<").postfix("> ");

        ret += retType + " " + name + "( ";


        ret += Strings.join( args ).trans( new F1<String, T2<Typ, String>>() {
                    public String call(T2<Typ, String> arg) {
                        return arg.i0 + " " + arg.i1;
                    }
                });

        ret +=                                                   " )";

        ret += body.toString( "   ", true );

        return ret;
    }

    public Clazz c() {
        return clazz;
    }

    public Mesod arg( Typ typ, String name ) {
        this.args.add( T2.valueOf(typ,name));
        return this;
    }


//    public Fild<Mesod> fild( Typ typ, String name ) {
//        Fild<Mesod> fld = new Fild<Mesod>( this, "", typ, name );
//        statements.add( fld );
//        return fld;
//    }
//
//
//    public Mesod assign(String var, Expr str) {
//        statements.add( new Assign(var,str));
//        return this;
//    }
//
//    public Mesod assignPlus(String var, Expr expr) {
//        statements.add( new Assign(var,expr).plus());
//        return this;
//    }
//
//    public Mesod retrn( Expr expr ) {
//        statements.add( new Return(expr));
//        return this;
//    }
//
//    public IfExpr<Mesod> iff( Expr cond ) {
//        IfExpr<Mesod> ex = new IfExpr<Mesod>( this, cond );
//        statements.add( ex );
//        return ex;
//    }

    public Block<Mesod> body() {
        if ( body == null ) {
            body = new Block<Mesod>( this );
        }
        return body;
    }

    public Mesod gen(String typeName) {
        typeNames.add( typeName );
        return this;
    }
}
