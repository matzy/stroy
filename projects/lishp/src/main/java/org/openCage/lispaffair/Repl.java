/*
 * Repl.java
 *
 * Created on August 26, 2004, 6:57 PM
 */

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

package org.openCage.lispaffair;

import org.openCage.lishp.Symbol;

import java.text.ParseException;

public class Repl {
    
    /** Creates a new instance of Repl */
    public Repl( Environment env) {
        this.env = env;
    }
    
    public Object repl() {
         
        System.out.println( "welcome to lisp affair" );
        System.out.println( "   (:help :exit :list)" );
        
        go          = true;
        showDetails = false;
        byte buffer[] = new byte[255];
        LispFormat frmt = new LispFormat();
        while ( true ) {        
            buffer[0] = 0;
            System.out.print( "> " );
            try
            {
              System.in.read( buffer, 0, 255 );
              String input = new String( buffer );          
              if (  showDetails ) {
                System.out.println( "\n" + frmt.format( input ));
              }
              Object oin = frmt.parseObject( input );
              if (  showDetails ) {
                  System.out.println( frmt.format( oin ));
              }
              
              if ( !replCommands( oin, env )) {
                  System.out.println( frmt.format( Eval.eval( oin, env )));  
              }
              
              if ( !go ) {
                  return null;
              }
            }
            catch ( Exception e ) {
                e.printStackTrace();
              System.out.println( e );
            }        
        }
        
        // return null;
    }
    
    public Object eval( String str ) throws ParseException {
        LispFormat frmt = new LispFormat();
        Object oin = frmt.parseObject( str );
        return frmt.format( Eval.eval( oin, env ));

    }
    
    private boolean replCommands(Object command, Environment env) {
        
        if ( ! (command instanceof Symbol )) {
            return false;            
        }
        
        if ( command == replExit ) {
            System.out.println( "bye" );
            go = false;
            return true;
        }

        if ( command == replHelp ) {
            System.out.println( "you are in the lisp affair repl" );
            System.out.println( "lisp expressions are evaluated" );
            System.out.println( ":help prints this help" );
            System.out.println( ":exit leaves the loop" );
            System.out.println( "have fun" );
            return true;
        }
        
        if ( command == replDetails ) {
            showDetails = !showDetails;
            return true;
        }

        if ( command == replList ) {
            System.out.println( env );
            return true;

        }
        return false;
        
    }
    
    private Environment env;
    private boolean     go;
    private boolean     showDetails;
    
    public static Symbol replExit       = Symbol.get( ":exit" );
    public static Symbol replHelp       = Symbol.get( ":help" );
    public static Symbol replDetails    = Symbol.get( ":details" );
    public static Symbol replList       = Symbol.get(":list");

}
