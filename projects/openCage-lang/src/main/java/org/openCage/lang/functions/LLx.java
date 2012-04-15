//package org.openCage.lang.functions;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//
//public class LLx<R, X> implements F1<R, X> {
//
//    public static class Gard {
//        private Gard() {}
//    }
//
//    public X x;
//    private R r;
//    public Gard gard;
////    public boolean first;
//
//    // http://sourceforge.net/projects/approvaltests/develop
//
//
//    public LLx( Gard g, X x) {
////        this.gard = g;
//        this.x = x;
//    }
//
//    public R call(X x) {
//        Class<? extends LLx> clazz = this.getClass();
//
//        Constructor<LLx> constructor = (Constructor<LLx>) clazz.getDeclaredConstructors()[0];
//        constructor.setAccessible(true);
//
//        try {
//            return (R)constructor.newInstance( new Object[]{null, new Gard(), x} ).r;
//
//
//        } catch (InstantiationException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//
//        throw new Error("err");
//    }
//
//    public Object return_( R r ) {
//        this.r = r;
//        return null;
//    }
//}
