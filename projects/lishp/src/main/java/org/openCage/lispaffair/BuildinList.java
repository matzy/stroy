package org.openCage.lispaffair;

import org.openCage.lishp.BuildinFunction;
import org.openCage.lishp.Function;

import java.util.ArrayList;
import java.util.List;

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
