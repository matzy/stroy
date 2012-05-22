package org.openCage.osashosa;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/11/12
 * Time: 8:07 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Factory2<T,A,B> {

    T get( A a, B b );
}
