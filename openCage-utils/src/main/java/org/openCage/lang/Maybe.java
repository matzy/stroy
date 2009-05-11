//package org.openCage.lang;
//
///**
// *
// */
//public class Maybe<T> {
//
//    private  final T val;
//    public   final boolean is;
//
//    private Maybe( T o, boolean is ) {
//        this.val = o;
//        this.is = is;
//    }
//
//    public static Maybe no() {
//        return new Maybe( null, false );
//    }
//
//    public static <T> Maybe<T> yes( T t ) {
//        return new Maybe( t, true );
//    }
//
//
//    public boolean equals( Object o1 ) {
//        if ( this == o1 ) return true;
//        if ( o1 == null || getClass() != o1.getClass() ) return false;
//
//        Maybe maybe = (Maybe)o1;
//
//        if ( is != maybe.is ) return false;
//
//        if ( is ) {
//            return true;
//        }
//
//        if ( val != null ? !val.equals( maybe.val) : maybe.val != null ) return false;
//
//        return true;
//    }
//
//    public int hashCode() {
//        int result;
//        result = ( val != null ? val.hashCode() : 0 );
//        result = 31 * result + ( is ? 1 : 0 );
//        return result;
//    }
//
//
//    @Override
//    public String toString() {
//        if ( !is ) {
//            return "no";
//        }
//
//        return "yes( " + val.toString() + " )";
//    }
//
//    public T get() {
//        if ( !is ) {
//            throw new IllegalStateException( "maybe: 'no' does not have a value" );
//        }
//
//        return val;
//    }
//}
