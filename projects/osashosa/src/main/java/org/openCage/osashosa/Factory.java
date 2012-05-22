package org.openCage.osashosa;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/9/12
 * Time: 11:52 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Factory<T,A> {

    T get( A a );
}
