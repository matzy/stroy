package org.openCage.lispaffair;

import org.openCage.lishp.Function;
import org.openCage.lishp.Special;
import org.openCage.lishp.Symbol;

import java.util.*;

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
public class Eval {
    
    /** Creates a new instance of Eval */
    public Eval() {
    }
    
    public static Object eval( Object obj, Environment env ) {
        
        if ( obj == null ) {
            return null;
        }
        
        if ( obj instanceof Symbol ) {
            return env.getBinding( (Symbol)obj );
        }

        if ( !(obj instanceof List )) {
            return obj;
        }

        List lst = (List)obj;
        
        if ( lst.isEmpty() ) {
            return lst;
        }

        Object hd = eval( lst.get(0), env );              

        // System.out.println( new LispFormat().format( hd ));
        
        if ( hd instanceof Special ) {
            return ((Special)hd).apply( lst, env );
        }

        if ( hd instanceof Function ) {
            
            LinkedList evaledList = new LinkedList();

            evaledList.addLast( hd );
            for ( int i = 1; i < lst.size(); i++) {
                evaledList.addLast( eval( lst.get(i), env ));
            }
            
            //System.out.println( new LispFormat().format( evaledList ));
            
            return ((Function)hd).apply( evaledList, null );
        }
        
        if ( hd instanceof Macro ) {
            return eval( ((Macro)hd).expand( lst ), env );
        }
     
        System.out.println( "huh can eval " + obj  );
        return null;
    }
}
