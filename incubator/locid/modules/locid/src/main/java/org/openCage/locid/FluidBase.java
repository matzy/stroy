package org.openCage.locid;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 7/19/11
 * Time: 12:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class FluidBase<T extends FluidBase> {



    public T print( String str ) {
        System.out.println(str);
        return (T) this;
    }
}
