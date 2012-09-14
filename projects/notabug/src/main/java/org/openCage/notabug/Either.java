package org.openCage.notabug;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/8/12
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Either<A,B,C> {

    public static class EA<A,B,C> extends Either<A,B,C> {
        public A getA() {
            return a;
        }

        private final A a;
        public EA(A a) {
            this.a = a;
        }
    }

//    public <R> R call( F1<R,A> onA ) {
//        return
//    }

    public boolean isA() {
        return this instanceof EA;
    }

    public A getA() {
        throw new UnsupportedOperationException( "not an A" );
    }
}
