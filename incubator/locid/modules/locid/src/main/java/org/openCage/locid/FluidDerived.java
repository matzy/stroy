package org.openCage.locid;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 7/19/11
 * Time: 12:09 PM
 * To change this template use File | Settings | File Templates.
 */
public  class FluidDerived<T extends FluidDerived> extends FluidBase<T>{

    public T more() {
        System.out.println("more");
        return (T) this;
    }
}
