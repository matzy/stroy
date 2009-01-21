package org.openCage.utils.io.with;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Jan 19, 2009
 * Time: 10:55:40 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ReaderFunctor<R,A> {

    public R c( A a ) throws IOException;

}
