/*
 * Buildin.java
 *
 * Created on August 26, 2004, 12:21 PM
 */

package org.openCage.lispaffair;

import org.openCage.lishp.Function;
import org.openCage.lishp.Symbol;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author  SPfab
 */
public class Buildin {
    
    /** Creates a new instance of Buildin */
    public Buildin() {
    }

    private static Hashtable equals = new Hashtable();
    private static Hashtable pluses = new Hashtable();

//    public static Function getNumberSmaller() {
//        return numberSmaller;
//    }
    

    public static Function numberSmaller = new Function(){
        public Object apply( List<Object> lst, Environment env ) {
            
            return new Boolean( ((Integer)lst.get(1)).intValue() <
                                ((Integer)lst.get(2)).intValue()   );
        }
        
        public int argNum() {
            return 2;
        }
        
        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "#<" );
            return toAppendTo;
        }
        

    };

    public static Function numberMinus = new Function(){
        public Object apply( List<Object> lst, Environment env ) {

            return new Integer(((Integer)lst.get(1)).intValue() -
                               ((Integer)lst.get(2)).intValue());
        }
        
        public int argNum() {
            return 2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "-" );
            return toAppendTo;
        }
    };

    public static Function numberTimes = new Function(){
        public Object apply( List lst, Environment env ) {

            return new Integer(((Integer)lst.get(1)).intValue() *
                               ((Integer)lst.get(2)).intValue());
        }
        
        public int argNum() {
            return 2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "*" );
            return toAppendTo;
        }

    };
    
    public static Function equal = new Function(){
        public Object apply( List<Object> lst, Environment env ) {

            Class cl = lst.get(1).getClass();
            
            if ( !equals.containsKey( cl )) {
                return null;
            }
            
            return ((Equals)equals.get( cl )).equals( lst.get(1),
                                                      lst.get(2) );
        }
        
        public int argNum() {
            return 2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "=" );
            return toAppendTo;
        }

    };

    public static Function plus = new Function(){
        public Object apply( List<Object> lst, Environment env ) {

            Class cl1 = lst.get(1).getClass();
            Class cl2 = lst.get(2).getClass();
            
            if ( !pluses.containsKey( cl1 ) ) {
                return null;
            }

	    Hashtable ht = (Hashtable)pluses.get(cl1);
            
            if ( !ht.containsKey( cl2 ) ){
                return null;
            }
            
            return ((Plus)ht.get( cl2 )).plus( lst.get(1),
                                               lst.get(2) );
        }
        
        public int argNum() {
            return 2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "#+" );
            return toAppendTo;
        }


    };

    private static boolean plusHelper =  LispFormat.addReaderMacro( "#+", plus );

    public static Function map = new Function(){
        public Object apply( List<Object> lst, Environment env ) {

            Map<Symbol,Object> map = new HashMap<Symbol,Object>();

            for ( int i = 1; i < lst.size(); i += 2 ) {
                Object sym = lst.get(i);
                Object val = lst.get(i+1);

                if (!( sym instanceof Symbol )) {
                    System.err.println("map maps symbols not " + sym );
                    return null;
                }

                map.put( (Symbol)sym, val );
            }

            return map;

        }

        public int argNum() {
            return -1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "#+" );
            return toAppendTo;
        }


    };

//    private static boolean plusHelper =  LispFormat.addReaderMacro( "#+", plus );

    public static Function accessor = new Function(){
        public Object apply( List<Object> lst, Environment env2 ) {

            Object env = lst.get(1);
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

    public static Function print = new Function(){
        public Object apply( List<Object> lst, Environment env ) {

            Object obj = lst.get(1);

            System.out.println(obj);

            return obj;
        }

        public int argNum() {
            return 1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "print" );
            return toAppendTo;
        }
    };

     public static Function head = new Function(){
         public Object apply( List<Object> lst, Environment env ) {

            Object list = lst.get(1);

            if (!( list instanceof List )) {
                System.err.println("head needs a list not " + list );
                return null;
            }
            if (((List) list).isEmpty()) {
                System.err.println("head needs a non empty list " );
                return null;
            }

            return ((List) list).get(0);
        }

        public int argNum() {
            return 1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "head" );
            return toAppendTo;
        }
    };

    public static Function tail = new Function(){
        public Object apply( List<Object> lst, Environment env ) {

            Object list = lst.get(1);

            if (!( list instanceof List )) {
                System.err.println("tail needs a list not " + list );
                return null;
            }
            if (((List) list).isEmpty()) {
                System.err.println("tail needs a non empty list " );
                return null;
            }

            return ((List)list).subList(1, ((List)list).size());
        }

        public int argNum() {
            return 1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "head" );
            return toAppendTo;
        }
    };

    public static boolean addEqual( Class C, Equals E ) {
        equals.put( C, E);
        return true;
    }
    
    public static boolean addPlus( Class C1, Class C2, Plus P ) {
        if ( !pluses.containsKey( C1 )) {
            pluses.put( C1, new Hashtable() );
        }
        
        ((Hashtable)pluses.get(C1)).put(C2,P);
        return true;
    }
    
    public static boolean intPlusHelper = addPlus(
        new Integer(0).getClass(),
        new Integer(0).getClass(),
        new Plus() {
            public Object plus( Object A, Object B ) {
                return new Integer(((Integer)A).intValue() +
                                   ((Integer)B).intValue());
                
                
            }
        }
    );

    public static boolean stringPlusHelper = addPlus(
        new String().getClass(),
        new String().getClass(),
        new Plus() {
            public Object plus( Object A, Object B ) {
                return (String)A + (String)B;
                
                
            }
        }
    );

    public static boolean stringIntPlusHelper = addPlus(
        new String().getClass(),
        new Integer(0).getClass(),
        new Plus() {
            public Object plus( Object A, Object B ) {
                return (String)A + ((Integer)B).intValue();
                
                
            }
        }
    );

    private static boolean intEqualHelper = addEqual(
            new Integer(0).getClass(),
            new Equals() {
                public Boolean equals( Object A, Object B ) {
                    return new Boolean(((Integer)A).intValue() ==
                            ((Integer)B).intValue());

                }

            });

    private static boolean symbolEqualHelper = addEqual(
            Symbol.class,
            new Equals() {
                public Boolean equals( Object A, Object B ) {
                    return new Boolean( A == B );

                }

            });

    private static boolean stringEqualHelper = addEqual(
            String.class,
            new Equals() {
                public Boolean equals( Object A, Object B ) {
                    return new Boolean( ((String)A).equals(B) );

                }

            });



}
