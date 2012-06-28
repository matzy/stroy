package org.openCage.lispaffair;
import org.openCage.lishp.*;

import java.util.ArrayList;
import java.util.LinkedList;
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
public class BuildinSpecials {
    
    /** Creates a new instance of BuildinSpecials */
    private BuildinSpecials() {
    }
    
    public static Special specialIf = new BuildinSpecial( ":if") {
    
        public Object       apply( List<Object> lst, Environment env ) {

            Object test = Eval.eval( lst.get(1), env );

            if ( !(test instanceof Boolean )) {
                throw new NullPointerException("if needs a boolean first arg not " +test );
            }

            if ( ((Boolean)test ).booleanValue()) {
                return Eval.eval( lst.get(2), env );
            } else {                    
                return Eval.eval( lst.get(3), env );
            }
        }

        public int          argNum() {
            return 3;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "#if" );
            return toAppendTo;
        }

    };
    
    public static Special specialBlock = new BuildinSpecial( ":block") {

        public Object       apply( List lst, Environment env ) {

            Object ret = Symbol.get("#n");
            for ( int i = 1; i < lst.size(); i++ ) {
                ret = Eval.eval( lst.get(i), env );
            }

            return ret;
        }

        public int          argNum() {
            return 2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( ":block" );
            return toAppendTo;
        }

        public String toString() {
            return "; (block a b c) evalutes all returns last";
        }


    };

    public static Special specialFct = new BuildinSpecial( ":fct" ) {
        public Object       apply( List lst, Environment env ) {

            LinkedList body = new LinkedList();

            for ( int i = 2; i < lst.size(); i++) {
                body.addLast( lst.get(i));
            }

            return new Fct( (List)lst.get(1), body, env );

        }

        public int          argNum() {
            return -2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "#fct" );
            return toAppendTo;
        }
        
    };

    public static Special specialCatch = new Special() {
        public Object       apply( List lst, Environment env ) {

            LinkedList body = new LinkedList();

            for ( int i = 2; i < lst.size(); i++) {
                body.addLast( lst.get(i));
            }

            return new Fct( (List)lst.get(1), body, env );

        }

        public int          argNum() {
            return -2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "#fct" );
            return toAppendTo;
        }

    };

    public static Special specialClone = new BuildinSpecial( ":clone" ) {
        public Object       apply( List lst, Environment env ) {

            Environment ret = env.copy();

            for ( int i = 1; i < lst.size(); i++ ) {
                Eval.eval( lst.get(i), ret  );
            }

            return ret;
        }

        public int          argNum() {
            return -2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "#clone" );
            return toAppendTo;
        }
    };


    public static Special specialSet = new BuildinSpecial( ":set" ) {
        public Object       apply( List lst, Environment env ) {

            Object sym = lst.get( 1 );
            Object val = Eval.eval( lst.get( 2 ), env );

            if ( sym instanceof Symbol ) {
                env.bind((Symbol) lst.get(1), val );
            }

            if ( sym instanceof List ) {
                if ( ((List)sym).get(0) instanceof Symbol ) {
                    env = (Environment)Eval.eval(((List)sym).get(1), env );
                    sym = ((List)sym).get(2);

                    env.bind( (Symbol)sym, val );
                }
            }

            return val;
        }

        public int          argNum() {
            return 2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "#set" );
            return toAppendTo;
        }

        public String toString() {
            return "; buildin (set x obj) bind evaled obj to x";
        }

        
    };

    public static Special let = new BuildinSpecial( ":let" ) {
        public Object       apply( List<Object> lst, Environment env ) {

            env.push();
            
            int i = 1;

            while ( true ) {
                
                if ( i + 2 >= lst.size()  ) {
                    throw new LishpException( Symbol.get("SyntaxError"), "let: no pairs or missing ':in > " + lst );
                }

                Object key = lst.get( i );
                Object val = Eval.eval( lst.get( i+1 ), env );

//                System.err.println( "key --- " + key );
//                System.err.println("    val --- " + val );
                
                match(env, key, val);
    
                i += 2;

                Object inp = lst.get(i);
                if ( (inp instanceof Symbol) &&  inp == Symbol.get( ":in")) {
                    break;
                }


            }

            //System.err.println("env --- " + env );

            i++;
            
            Object ret = null;
            
            for ( ; i < lst.size(); ++i ) {
                Object prog = lst.get( i );
                ret = Eval.eval( prog, env );
            }

            env.pop();

            return ret;
        }

        public int          argNum() {
            return -1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "#let" );
            return toAppendTo;
        }

        public String toString() {
            return "; buildin (let x obj) bind evaled obj to x";
        }


    };

    private static void matchList(Environment env, List key, Object val) {
        if ( key.isEmpty() ) {
            System.err.println("match lrh #n");
            return;
        }
        
        if ( !(val instanceof List )) {
            System.err.println("match needs a list not " + val);
            return;
        }

        List vals = (List)val;
        
        Object hd = key.get(0);
        
        if ( hd instanceof Symbol && ( hd == Symbol.get(":"))) {

            if ( key.size() < 3 ) {
                System.err.println("not enough var lhs " + vals );
                return;
            }

            // 3 1
            // 3 2 ...
            //

            if ( key.size() - 2 > (vals.size() ) ) {
                System.err.println("size mismatch " + (key.size() - 1) + " keys but " + vals.size() + " vals" );
                return;
            }

            Object tl = key.get( key.size() -1 );
            if ( !(tl instanceof Symbol )) {
                System.err.println("need tl symbol not " + tl);
                return;
            }

            for ( int i = 1; i < key.size() - 1; ++i ) {
                try {
                match( env, key.get(i), vals.get(i -1 ) );
                } catch ( Exception exp ) {
                        int foo = 0;
                 }
            }

            env.bind((Symbol) tl, vals.subList( key.size() - 2, vals.size() ));
        } else {
            if ( key.size() != (vals.size()) ) {
                System.err.println("size mismatch");
                return;
            }

            for ( int i = 0; i < key.size(); ++i ) {
                match( env, key.get(i), vals.get(i ) );
            }
        }
    }

    private static boolean match( Environment env, Object key, Object val) {
        if ( key instanceof Symbol ) {
            env.bind((Symbol) key, val );
        } else if ( key instanceof List ) {
            matchList( env, (List)key, val );
        } else {
            System.err.println("can't match " + key);
        }

        return true;

    }



//    public static Special specialDefine = new Special() {
//        public Object       apply( List lst, Environment env ) {
//
////            env.bindGlobal( (Symbol)lst.get(1), Eval.eval( lst.get(2), env ));
//            env.bind((Symbol) lst.get(1), Eval.eval(lst.get(2), env));
//            return (Symbol)lst.get(1);
//        }
//
//        public int          argNum() {
//            return 3;
//        }
//
//        public StringBuffer format( StringBuffer toAppendTo ) {
//            toAppendTo.append( "#set" );
//            return toAppendTo;
//        }
//
//        public String toString() {
//            return "; buildin (set x obj) bind evaled obj to x";
//        }
//
//
//    };

    public static Special specialQuote = new Special() {
        public Object       apply( List lst, Environment env ) {

            return lst.get(1);
        }

        public int          argNum() {
            return 1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "#quote" );
            return toAppendTo;
        }
        
    };
    
    private static boolean quoteHelper =  LispFormat.addReaderMacro( "#quote", specialQuote );
    
    public static Special specialBackQuote = new Special() {
        public Object       apply( List lst, Environment env ) {
                        
            Object arg = lst.get(1);
            
            if ( !( arg instanceof List )) {
                return arg;
            }
            
            List list = (List)arg;
            
            List ret = new LinkedList();
            
            return applyBQ( list, env, ret );
            
        }
        
        private Object applyBQ( List lst, Environment env, List ret  ) {

            for( int i = 0; i < lst.size(); i++ ) {
                
                if ( !(lst.get(i) instanceof List)) {
                    ret.add( lst.get(i));
                    continue;
                }
                
                List innerList = (List)lst.get(i);
                
                if ( innerList.size() > 0 ) {
                    if ( innerList.get(0) == BuildinSpecials.specialComma  ) {
                        ret.add( Eval.eval( innerList.get(1), env));                        
                        continue;
                    }
                }                               
                
                
                ret.add( applyBQ( innerList, env, new LinkedList() ));
            }
            
            return ret;            
        }

        public int          argNum() {
            return 1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "#backquote" );
            return toAppendTo;
        }
        
    };

    private static boolean backquoteHelper =  LispFormat.addReaderMacro( "#backquote", specialBackQuote );

    public static Special specialComma = new Special() {
        public Object       apply( List lst, Environment env ) {
             
            return null;
        }

        public int          argNum() {
            return 1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "#comma" );
            return toAppendTo;
        }
        
    };

    private static boolean commaHelper =  LispFormat.addReaderMacro( "#comma", specialComma );

    public static Special specialMacro = new Special() {
    
        public Object  apply( List lst, Environment env ) {

            LinkedList body = new LinkedList();

            for ( int i = 2; i < lst.size(); i++) {
                body.addLast( lst.get(i));
            }

            return new Mcr( (List)lst.get(1), body, env );

        }

        public int          argNum() {
            return -2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "#mcr" );
            return toAppendTo;
        }

    };
    private static boolean macroHelper =  LispFormat.addReaderMacro( "#mcr", specialComma );

    public static Special dot = new Special(){
        public Object apply( List lst, Environment outer ) {

            Object env = Eval.eval( lst.get(1), outer );
            Object sym = lst.get(2);

            if (!( sym instanceof Symbol )) {
                System.err.println("map maps symbols not " + sym );
                return null;
            }


            if ( env instanceof Map ) {
                return ((Map)env).get((Symbol)sym);
            }

            if ( env instanceof Environment ) {
                return ((Environment)env).getBinding((Symbol) sym);
            }

            System.err.println("not a map or environment " + env );

            return null;
        }

        public int argNum() {
            return -1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "." );
            return toAppendTo;
        }
    };

    public static Special trycatch = new BuildinSpecial(":try"){
        public Object apply( List<Object> lst, Environment outer ) {

            // format (try .... *[:catch sym ...]  [:finally ...])
            
            

            List<List<Object>> blocks = Lists.split( lst.subList(1,lst.size()), Symbol.get(":catch"), Symbol.get(":finally"));

            if ( blocks.isEmpty() || blocks.get(0).isEmpty() ) {
                throw new LishpException( Symbol.get("SyntaxError"), "no try block " + lst );
            }
            
            Object ret = null;
            
            try {
                for ( Object obj : blocks.get(0)) {
                    ret = Eval.eval( obj, outer );
                }
                return ret;
            } catch ( LishpException exp ) {
                for ( List<Object> block : blocks ) {
                    if ( block.size() > 1 && block.get(0) == Symbol.get(":catch") && block.get(1) == exp.getExceptionSymbol() ) {
                        for ( int i = 2; i < block.size(); ++i ) {
                            ret = Eval.eval( block.get(i), outer );
                        }
                        return ret;
                    }
                }
                return null;
            } finally {
                for ( List<Object> block : blocks ) {
                    if ( !block.isEmpty() && block.get(0) == Symbol.get(":finally")) {
                        for ( int i  =1; i < block.size(); ++i ) {
                            Eval.eval( block.get(i), outer );
                        }
                    }
                }
            }
            
        }

        public int argNum() {
            return -1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "." );
            return toAppendTo;
        }
    };

    public static Special specialThrow = new BuildinSpecial(":throw"){
        public Object apply( List<Object> lst, Environment outer ) {

            Object sym = lst.get(1);

            if (!( sym instanceof Symbol )) {
                throw new LishpException( Symbol.get("SyntaxError"), "throw without symbol " + lst );
            }

            String msg = "";
            if ( lst.size() > 2 ) {
                msg = (String)Eval.eval( lst.get(2),outer  );;
            }
            
            throw new LishpException( (Symbol)sym, msg );
        }

        public int argNum() {
            return -1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "throw" +
                    "" );
            return toAppendTo;
        }
    };


}
