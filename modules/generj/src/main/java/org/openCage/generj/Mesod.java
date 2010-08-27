package org.openCage.generj;


import com.sun.codemodel.internal.JWhileLoop;
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

    private List<T2<Typ,String>> args = new ArrayList<T2<Typ, String>>();

    private Block<Mesod> body;
    private List<String> typeNames = new ArrayList<String>();
    private List<Typ> thrws = new ArrayList<Typ>();

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
        return toString("");
    }

    public String toString( String prefix ) {
        String ret = prefix + mod + " ";

        ret += Strings.join( typeNames ).prefix( "<").postfix("> ");

        ret += (retType != null ? retType : "" )+ " " + name + "( ";


        ret += Strings.join( args ).trans( new F1<String, T2<Typ, String>>() {
                    public String call(T2<Typ, String> arg) {
                        return arg.i0 + " " + arg.i1;
                    }
                });

        ret += " )";

        ret += Strings.join( thrws ).prefix( " throws ").postfix(" ");

        ret += body.toString( prefix  );

        return ret;
    }


    public Clazz c() {
        return clazz;
    }

    public Mesod arg( Typ typ, String name ) {
        this.args.add( T2.valueOf(typ,name));
        return this;
    }



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

    public Mesod thrws(Typ typ) {
        this.thrws.add( typ );
        return this;
    }
}
