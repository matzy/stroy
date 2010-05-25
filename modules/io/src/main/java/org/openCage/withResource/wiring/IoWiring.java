//package org.openCage.withResource.wiring;
//
//import org.openCage.withResource.impl.WithImpl;
//
//import com.google.inject.Binder;
//import com.google.inject.Module;
//
//public class IoWiring implements Module {
//    private static boolean once;
//
//    public void configure(Binder binder ) {
////        if ( once ) {
////            return;
////        }
////        once = true;
//
//
//		binder.bind( With.class ).to( WithImpl.class );
//	}
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        return obj != null && (obj instanceof IoWiring);
//    }
//}