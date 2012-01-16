package org.openCage.lispaffair;
import org.openCage.lishp.Function;
import org.openCage.lishp.Symbol;

import java.util.List;

public class Fct extends Function {
    
    public Fct( final List<Symbol> args, List<Object> body, Environment env ) {
        this.args = args;
        this.body = body;
        this.env  = env;
    }
    
    public Object apply( final List<Object> lst, Environment dummy ) {
        env.push();
        if ( args.size() != lst.size() - 1) {
            throw new IllegalArgumentException("wrong number of arguments");
        }
        for ( int i = 0; i < args.size(); i++) {
            env.bind( args.get(i), lst.get(i+1) );
        }

        Object ret = null;
        for ( int i = 0; i < body.size(); i++ ) {
            ret = Eval.eval( body.get(i), env );
        }
        env.pop();
        
        return ret;        
    }
    
    public int argNum() {
        return args.size();
    }
    
    public StringBuffer format(final StringBuffer toAppendTo) {
        final LispFormat frmt = new LispFormat();
        toAppendTo.append( "(fct ");
        toAppendTo.append( frmt.format(args));
        
        for ( int i = 0; i < body.size(); i++ ) {
            toAppendTo.append( " " );
            toAppendTo.append( frmt.format(body.get(i)));
        }

        toAppendTo.append( ")" );
        
        return toAppendTo;
    }

    public String toString() {
        return format( new StringBuffer()).toString();
    }
    
    private List<Symbol>  args;
    private List          body;
    private Environment   env;

}
