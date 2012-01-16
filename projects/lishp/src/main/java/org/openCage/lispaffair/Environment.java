package org.openCage.lispaffair;
import org.openCage.lishp.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
