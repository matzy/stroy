package org.openCage.lishp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author  openCage
 */

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
            throw new LishpException("can't eval global of non special");
        }
        return global;
    }
    
    public void setGlobal( final Object obj ) {
        if ( !isSpecial ) {
            throw new LishpException("can't assign to non special");
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
