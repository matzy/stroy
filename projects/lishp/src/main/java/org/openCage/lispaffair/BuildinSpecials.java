package org.openCage.lispaffair;
import org.openCage.lishp.BuildinSpecial;
import org.openCage.lishp.Special;
import org.openCage.lishp.Symbol;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author  SPfab
 */
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

    public static Special specialFct = new Special() {
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

    public static Special specialClone = new Special() {
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


    public static Special specialSet = new Special() {
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

    public static Special let = new Special() {
        public Object       apply( List lst, Environment env ) {

            env.push();
            
            int i = 1;

            while ( true ) {
                Object key = lst.get( i );
                Object val = Eval.eval( lst.get( i+1 ), env );

//                System.err.println( "key --- " + key );
//                System.err.println("    val --- " + val );
                
                match(env, key, val);
    
                i += 2;

                Object inp = lst.get(i);
                if ( (inp instanceof Symbol) &&  inp == Symbol.get( "in")) {
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



}
