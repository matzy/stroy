package org.openCage.kleinod.collection;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 9/4/12
 * Time: 10:26 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Reference<T> {
    T get();

    void set(T obj);
}
