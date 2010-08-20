package org.openCage.geni;

import java.util.ArrayList;
import java.util.List;

public class IfExpr<T> implements Statement {
    private Expr cond;
    private List<Statement> thns = new ArrayList<Statement>();
    private List<Statement> els = new ArrayList<Statement>();
    private T base;

    public IfExpr( T base, Expr cond ) {
        this.cond = cond;
    }


    public IfExpr thn( Statement stat ) {
        thns.add( stat );
        return this;
    }

    public IfExpr els( Statement stat ) {
        els.add( stat );
        return this;
    }

    public String toString() {
        String ret = "if( " + cond.toString() + " ){\n";

        for ( Statement st : thns ) {
            ret += st.toString() + ";\n";
        }

        if ( !els.isEmpty() ) {
            ret += "} else {\n";

            for ( Statement st : els ) {
                ret += st.toString() + ";\n";
            }
        }
        ret += "}";

        return ret;
    }

    public T r() {
        return base;
    }
}
