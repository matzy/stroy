package org.openCage.lishp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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

/**
 * Symbol ist the class of names used for Lishp
 * A symbol is a word composed of [A-Z][a-z][0-9].: ...
 * Symbols starting with : are special, they have only global binding or none, they can only be bound by java
 * they act as extra syntax notions 
 */
@SuppressWarnings("PublicMethodNotExposedInInterface")
public final class Symbol {
    
    private static final String SPECIAL_START = ":";
    @SuppressWarnings("ConstantNamingConvention")
    private static final Map<String,Symbol> symbols = new HashMap<String, Symbol>( 100 );
    
    private static final Logger LOG = Logger.getLogger( Symbol.class.getName() );

    private final String   name;
    private Object   global;
    private final boolean  isSpecial;



    private Symbol( final String name ) {
        this.name        = name;
        this.global      = this;
        this.isSpecial   =  name.startsWith(SPECIAL_START);
    }
    
//    private static Symbol makeSpecial( String name ) {
//        Symbol newSpecial = new Symbol( name );
//        
//        newSpecial.isSpecial = true;
//        symbols.put( name, newSpecial );
//        
//        return newSpecial;
//    }
    
    public static Symbol get( final String name ) {

        final Symbol sym = symbols.get(name);
        if (sym != null) {
            return sym;
        }

        if (LOG.isLoggable(Level.FINEST)) {
            LOG.finest( "creating new Symobl " + name );
        }

        final Symbol newSym = new Symbol( name );
        symbols.put( name, newSym );
        
        return newSym;    
        
    }
    
    public boolean isSpecial() {
        return isSpecial;
    }
    
    public Object getGlobal() {
        if ( !isSpecial ) {
            throw new LishpException( Symbol.get("SyntaxError"), "can't eval global of non special");
        }
        return global;
    }
    
    public void setGlobal( final Object obj ) {
        if ( !isSpecial ) {
            throw new LishpException(Symbol.get("SyntaxError"), "can't assign to non special");
        }
        
        global = obj;
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return name;
    }
    

    
    

}
