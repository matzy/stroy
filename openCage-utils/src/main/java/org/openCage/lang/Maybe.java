package org.openCage.lang;

public class Maybe<T> {

    public  final T       o;
    public  final boolean is;

    private Maybe( T o, boolean is ) {
        this.o = o;
        this.is = is;
    }

    public static Maybe no() {
        return new Maybe( null, false );
    }

    public static <T> Maybe<T> yes( T t ) {
        return new Maybe( t, true );
    }


    public boolean equals( Object o1 ) {
        if ( this == o1 ) return true;
        if ( o1 == null || getClass() != o1.getClass() ) return false;

        Maybe maybe = (Maybe)o1;

        if ( is != maybe.is ) return false;

        if ( is ) {
            return true;
        }

        if ( o != null ? !o.equals( maybe.o ) : maybe.o != null ) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = ( o != null ? o.hashCode() : 0 );
        result = 31 * result + ( is ? 1 : 0 );
        return result;
    }


    @Override
    public String toString() {
        if ( !is ) {
            return "no";
        }

        return "yes( " + o.toString() + " )";
    }
}
