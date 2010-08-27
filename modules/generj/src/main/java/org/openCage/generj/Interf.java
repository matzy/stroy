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
 * Date: Aug 27, 2010
 * Time: 5:22:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Interf {

    public static class MethodDec {
        private Interf parent;
        private Typ retType;
        private String nme;
        private List<T2<Typ,String>> args = new ArrayList<T2<Typ, String>>();

        public MethodDec( Interf parent, Typ retType, String nme ) {
            this.parent = parent;
            this.retType = retType;
            this.nme = nme;
        }

        public MethodDec arg( Typ typ, String name ) {
            this.args.add( T2.valueOf(typ,name));
            return this;
        }

        public String toString(String prefix) {
            return prefix + "public " + retType + " " + nme + "( " + Strings.join( args ).trans( new F1<String, T2<Typ, String>>() {
                @Override
                public String call(T2<Typ, String> arg) {
                    return arg.i0 + " " + arg.i1;
                }
            }) + " );";
        }
    }

    private String pack;
    private Typ name;
    private List<String> imports = new ArrayList<String>();
    private String modi = "public";
    private List<MethodDec> methods = new ArrayList<MethodDec>();



    public Interf(String pack, Typ typ) {
        this.pack = pack;
        this.name = typ;
    }

    public String toString() {
        return toString("");
    }

    public String toString( String prefix ) {
        String ret = prefix;

        if ( pack != null ) {
            ret += prefix + "package " + pack + ";\n\n";
        }

        for ( String imp : imports ) {
            ret += prefix + "import " + imp + ";\n";
        }

        ret += prefix + modi + " interface " + name;
        // extends

        ret += " {\n";

        for( MethodDec md : methods ) {
            ret += md.toString( prefix + "   ") + "\n";
        }

        ret += prefix + "}\n";

        return ret;
    }

    public MethodDec method( Typ retTyp, String name ) {
        MethodDec md = new MethodDec( this, retTyp, name );
        methods.add( md );
        return md;
    }

    public Interf imprt(String imp) {
        imports.add( imp );
        return this;
    }



}
