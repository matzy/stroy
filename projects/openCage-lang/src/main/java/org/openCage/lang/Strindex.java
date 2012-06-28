package org.openCage.lang;

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

public class Strindex implements Comparable {

    public Strindex() {
        idx = "1";
    }
    
    /** Creates a new instance of Strindex */
    public Strindex( String str ) {

        for ( int i = 0; i < str.length(); ++i ) {
            switch ( str.charAt( i )) {
                case '0':
                case '1': break;
                default:
                    
            }
        }
        idx = str;
    }

    public Strindex pp() {

        String str = add( idx, "00001");
        if ( str == null ) {
            str = idx + "1";                
        }
            
        return new Strindex( str );        
    }
    
    public Strindex mm() {
        return new Strindex( idx.substring( 0, idx.length() - 1 ) + "01" ); 
    }
    
    public Strindex between( Strindex other ) {
        int cmp =  toString().compareTo( other.toString() );

        if ( cmp == 0 ) {
            return new Strindex( idx );
        } else if ( cmp > 0 ) {
            return other.between( this );
        }
        
        String ret = idx + "1";
        String add = "1";
        while ( ret.compareTo( other.idx ) >= 0 ) {
            add = "0" + add;
            ret = idx + add;            
        }
        
        return new Strindex( ret );
    }
    
    public boolean leq( Strindex other ) {
        return toString().compareTo( other.toString()) <= 0;
    }
    
    public boolean less( Strindex other ) {
        return toString().compareTo( other.toString()) < 0;
    }
    
    public String toString() {
        return idx;
    }

    private String add( String one, String two ) {
        while ( one.length() < two.length() ) {
            one += "0";
        }

        while ( two.length() < one.length() ) {
            two += "0";
        }
        
        int     count = 0;
        String  ret   = "";
        for ( int i = one.length() -1; i >= 0; --i ) {
            if ( one.charAt( i ) == '1' ) {
                count++;
            }
            if ( two.charAt( i ) == '1' ) {
                count++;
            }
            
            switch ( count ) {
                case 0:
                    ret = "0" + ret;
                    break;
                case 1:
                    count = 0;
                    ret = "1" + ret;
                    break;
                case 2:
                    count = 1;
                    ret = "0" + ret;
                    break;
                case 3:
                    count = 1;
                    ret = "1" + ret;
                    break;
            }
        }

        if ( count > 0 ) {
            return null;
        }
  
        int pos = ret.lastIndexOf( '1' );
        if ( pos > 0 ) {                    
            ret = ret.substring( 0, pos + 1);
        }
        
        return ret;
    }
    
    public int compareTo(Object o) {
        if ( o == null ) {
            return -1;
        }
        
        if ( !(o instanceof Strindex )) {
            return -1;
        }
        
        return idx.compareTo( ((Strindex)o).idx );
    }
    
    private String  idx;
}
