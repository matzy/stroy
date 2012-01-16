package org.openCage.lispaffair;

import org.openCage.lishp.BuildinFunction;
import org.openCage.lishp.Function;

import java.util.ArrayList;
import java.util.List;

public class BuildinList {

    public static Function head = new BuildinFunction( ":head"){
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

    public static Function tail = new BuildinFunction( ":tail"){
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

            return ((List)list).subList(1, ((List) list).size());
        }

        public int argNum() {
            return 1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "head" );
            return toAppendTo;
        }
    };

    public static Function size = new Function(){
        public Object apply( List<Object> lst, Environment env ) {

            Object list = lst.get(1);

            if (!( list instanceof List )) {
                System.err.println("list size needs a list not " + list );
                return null;
            }

            return ((List)list).size();
        }

        public int argNum() {
            return 1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "size" );
            return toAppendTo;
        }
    };

    public static Function cons = new Function(){
        public Object apply( List<Object> lst, Environment env ) {

            Object hd = lst.get(1);
            Object list = lst.get(2);

            if (!( list instanceof List )) {
                System.err.println("list size needs a list not " + list );
                return null;
            }

            ArrayList<Object> al = new ArrayList<Object>();

            return al;
        }

        public int argNum() {
            return 2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "cons" );
            return toAppendTo;
        }
    };

    public static Function forEach = new Function(){
        public Object apply( List<Object> lst, Environment env ) {

            Object list = lst.get(1);
            Object fct = lst.get(2);

            if (!( list instanceof List )) {
                System.err.println("list size needs a list not " + list );
                return null;
            }

            Object ret = null;

            for ( Object obj : (List)list) {
                List tmp = new ArrayList();
                tmp.add( fct );
                tmp.add( obj );
                ret = ((Fct)fct).apply(tmp, null );
            }

            return ret;
        }

        public int argNum() {
            return 2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "for-each" );
            return toAppendTo;
        }
    };

    public static Function fold = new Function(){
        public Object apply( List<Object> lst, Environment env ) {

            Object list = lst.get(1);
            Object fct = lst.get(2);
            Object start = lst.get(3);

            if (!( list instanceof List )) {
                System.err.println("list size needs a list not " + list );
                return null;
            }

            Object ret = null;

            for ( Object obj : (List)list) {
                List tmp = new ArrayList();
                tmp.add( fct );
                tmp.add( obj );
                ret = ((Fct)fct).apply(tmp, null );
            }

            return ret;
        }

        public int argNum() {
            return 3;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "fold" );
            return toAppendTo;
        }
    };

    public static Function find = new Function(){
        public Object apply( List<Object> lst, Environment env ) {

            Object list = lst.get(1);
            Object fct = lst.get(2);
            Object thn = null;

            if ( !(fct instanceof Fct )) {
                System.err.println("second arg needs to be a function not " + fct );
                return null;
            }

            if (lst.size() > 3 ) {
                thn = lst.get(3);
                if ( !(thn instanceof Fct )) {
                    System.err.println("third arg needs to be a function not " + thn );
                    return null;
                }
            }
            

            if (!( list instanceof List )) {
                System.err.println("list size needs a list not " + list );
                return null;
            }

            Object ret = null;

            for ( Object obj : (List)list) {
                List tmp = new ArrayList();
                tmp.add( fct );
                tmp.add( obj );
                ret = ((Fct)fct).apply(tmp, null );
                
                if ( (Boolean)ret ) {
                    if ( thn == null ) {
                        return ret;
                    } else {
                        List tmp2 = new ArrayList();
                        tmp2.add( thn );
                        tmp2.add( obj );
                        ret = ((Fct)thn).apply(tmp, null );

                        List rr = new ArrayList();
                        rr.add( true );
                        rr.add( ret );

                        return rr;
                    }
                }
            }

            if ( thn == null ) {
                return false;
            } else {
                List tmp = new ArrayList();
                tmp.add( false );
                tmp.add( null );
                return tmp;
            }
        }

        public int argNum() {
            return 2;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "find" );
            return toAppendTo;
        }
    };

    public static Function isNil = new Function(){
        public Object apply( List<Object> lst, Environment env ) {
            
            return lst.get(1) == null;
        }

        public int argNum() {
            return 1;
        }

        public StringBuffer format( StringBuffer toAppendTo ) {
            toAppendTo.append( "isNil" );
            return toAppendTo;
        }
    };

}
