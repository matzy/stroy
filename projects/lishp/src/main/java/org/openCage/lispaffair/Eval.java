/*
 * Eval.java
 *
 * Created on August 26, 2004, 10:47 AM
 */
package org.openCage.lispaffair;
import org.openCage.lishp.Function;
import org.openCage.lishp.Special;
import org.openCage.lishp.Symbol;

import java.util.*;

/**
 *
 * @author  SPfab
 */
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
