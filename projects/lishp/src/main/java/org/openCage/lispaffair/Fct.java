package org.openCage.lispaffair;
import org.openCage.lishp.Function;
import org.openCage.lishp.Symbol;

import java.util.List;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ***** END LICENSE BLOCK *****/
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
