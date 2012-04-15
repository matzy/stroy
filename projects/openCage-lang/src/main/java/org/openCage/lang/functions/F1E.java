package org.openCage.lang.functions;

import org.openCage.lang.errors.Unchecked;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 4/12/12
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class F1E<R,A> implements F1<R,A> {

    @Override public R call( A a ) {
        try {
            return callWithException( a );
        } catch (Exception e) {
            throw new Unchecked( e );
        }
    }

    public abstract R callWithException( A a ) throws Exception;

}
