package org.openCage.lispaffair;
import org.openCage.lishp.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Environment {
    
    public Environment() {
        push();
    }
    
    public Object getBinding( Symbol sym ) {
        
        if ( sym.isSpecial()) {
            return sym.getGlobal();
        }
        
        if ( stack.isEmpty() ) {
            return sym.getGlobal();
        }
        
        for ( int i = stack.size()-1; i >= 0; i-- ) {
            if (stack.get(i).containsKey( sym )) {
                return stack.get(i).get( sym );
            }
                
        }

        return sym;
    }
    
    public int bind( Symbol sym, Object obj ) {
        
//        if ( sym.isSpecial()) {
//            throw new NullPointerException("can't bind special");            
//        }
        
        if ( stack.isEmpty()) {
            sym.setGlobal( obj );
            return 0;
        }
        
        stack.get( stack.size() - 1).put(sym, obj);
        
        return 0;
    }
    
    public void bindGlobal( Symbol sym, Object obj ) {

//        if ( sym.isSpecial()) {
//            throw new NullPointerException("can't bind special");            
//        }
        
        sym.setGlobal( obj );        
    }
    
    public void push() {
        stack.add( new HashMap());
    }
    
    public void pop() {
        stack.remove( stack.size() -1 );
    }
  
    public String toString() {
        String ret = "Env:";
        for( int i = 0; i < stack.size(); i++ ) {

            Map<Symbol,Object> map = stack.get(i);

            for ( Symbol sym : map.keySet() ) {
                ret = ret + "\n    " + sym + " <- " + map.get(sym);
            }
        }
        
        return ret;
    }
    
    public Environment copy() {
        Environment ret = new Environment();
        
        if ( stack.isEmpty() ) {
            return ret;
        }
        
        ret.push();
        
        for( int i = 0; i < stack.size(); i++ ) {
            Map<Symbol,Object> map = stack.get(i);

            for ( Symbol sym : map.keySet() ) {
                ret.bind( sym, map.get( sym) );
            }
        }

        return ret;
    }

//    public Environment up() {
//
//    }
    
    private List<Map<Symbol,Object>> stack = new ArrayList<Map<Symbol, Object>>();
    
}
