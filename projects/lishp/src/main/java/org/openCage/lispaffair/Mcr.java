/*
 * Mcr.java
 *
 * Created on August 30, 2004, 3:21 PM
 */

package org.openCage.lispaffair;
import org.openCage.lishp.Symbol;

import java.util.*;

/**
 *
 * @author  SPfab
 */
public class Mcr implements Macro {
    
    /** Creates a new instance of Mcr */
    public Mcr( List args, List body, Environment env ) {
        this.args = args;
        this.body = body;
        this.env  = env.copy();
    }
    
    public int argNum() {
        return args.size();
    }
    
    
    
        /** Creates a new instance of Fct */
    
    public Object expand(java.util.List lst) {
        env.push();
        for ( int i = 0; i < args.size(); i++) {
            env.bind( (Symbol)args.get(i), lst.get(i+1) );
        }
        
        Object ret = null;
        for ( int i = 0; i < body.size(); i++ ) {
            ret = Eval.eval( body.get(i), env );
        }
        env.pop();
        
        return ret;        
    }
    
    
    public StringBuffer format(StringBuffer toAppendTo) {
        LispFormat frmt = new LispFormat();
        toAppendTo.append( "(mcr ");
        toAppendTo.append( frmt.format(args));
        
        for ( int i = 0; i < body.size(); i++ ) {
            toAppendTo.append( " " );
            toAppendTo.append( frmt.format(body.get(i)));
        }

        toAppendTo.append( ")" );
        
        return toAppendTo;
    }
    
    private List        args;
    private List        body;
    private Environment env;
    
}

