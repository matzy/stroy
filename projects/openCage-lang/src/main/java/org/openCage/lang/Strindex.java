package org.openCage.lang;

/**
 *
 * @author  Stephan
 */
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
