/*
 * lispaffair.java
 *
 * Created on August 24, 2004, 6:59 PM
 */

package org.openCage.lispaffair;

import org.openCage.lishp.Symbol;

/**
 *
 * @author  SPfab
 */
public class Lispaffair {
    
    /** Creates a new instance of Lispaffair */
    public Lispaffair() {
        init();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        new Lispaffair().repl();

        
    }

    public void repl() {
        new Repl( env ).repl();
    }

    private void init() {
        env.bind( Symbol.get( "<"), Buildin.numberSmaller );        
        env.bind( Symbol.get( "-"), Buildin.numberMinus );        
        env.bind( Symbol.get( "*"), Buildin.numberTimes );

        env.bind( Symbol.get( "if"), BuildinSpecials.specialIf );
        LispFormat.addReaderMacro( "#if", BuildinSpecials.specialIf );
        env.bind( Symbol.get( "try"), BuildinSpecials.trycatch );
        LispFormat.addReaderMacro( ":try", BuildinSpecials.trycatch );
        env.bind( Symbol.get( "throw"), BuildinSpecials.specialThrow );
        LispFormat.addReaderMacro( ":throw", BuildinSpecials.specialThrow );

        env.bind( Symbol.get( "set"), BuildinSpecials.specialSet );
        LispFormat.addReaderMacro( "#set", BuildinSpecials.specialSet );
        
        env.bind( Symbol.get( "fct"), BuildinSpecials.specialFct );
        LispFormat.addReaderMacro( "#fct", BuildinSpecials.specialFct );

        env.bind( Symbol.get( "true"), new Boolean( true ) );              
        LispFormat.addReaderMacro( "#t", new Boolean( true ) );

        env.bind( Symbol.get( "false"), new Boolean( false ) );              
        LispFormat.addReaderMacro( "#f", new Boolean( false ) );

        env.bind( Symbol.get( "quote"), BuildinSpecials.specialQuote );
        env.bind( Symbol.get( "backquote"), BuildinSpecials.specialBackQuote );
        env.bind( Symbol.get( "comma"), BuildinSpecials.specialComma );

        env.bind( Symbol.get( "="), Buildin.equal );              
        env.bind( Symbol.get( "+"), Buildin.plus );              

        env.bind( Symbol.get( "mcr"), BuildinSpecials.specialMacro );

        env.bind( Symbol.get( "block"), BuildinSpecials.specialBlock );



        env.bind( Symbol.get( "clone"), BuildinSpecials.specialClone );
        env.bind( Symbol.get( "map"), Buildin.map );
        env.bind( Symbol.get( "get"), Buildin.accessor );
        env.bind( Symbol.get( "."), BuildinSpecials.dot );

        env.bind( Symbol.get( "head"), Buildin.head );
        env.bind( Symbol.get( "tail"), Buildin.tail );
        env.bind( Symbol.get( "isNil"), BuildinList.isNil );
        env.bind( Symbol.get( "find"), BuildinList.find );

        env.bind( Symbol.get( "print"), Buildin.print );
        env.bind( Symbol.get( "let"), BuildinSpecials.let );

        // list
        Environment listEnv = new Environment();
        env.bind( Symbol.get( "list" ), listEnv );
        listEnv.bind( Symbol.get("size"), BuildinList.size );
        listEnv.bind( Symbol.get(":"), BuildinList.cons );
        listEnv.bind( Symbol.get("for-each"), BuildinList.forEach );

        // string
        Environment strEnv = new Environment();
        env.bind( Symbol.get( "string" ), strEnv );
        strEnv.bind( Symbol.get("split"), BuildinString.split );


    }
    
    private Environment env = new Environment();

//    public static Environment getStdEnv() {
//        init();
//        return env;
//    }

    public Environment getEnv() {
        return env;
    }
}
