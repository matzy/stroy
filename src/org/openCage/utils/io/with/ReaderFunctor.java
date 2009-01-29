package org.openCage.utils.io.with;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Jan 19, 2009
 * Time: 10:55:40 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ReaderFunctor<R> {

    public R c( Reader reader ) throws IOException;

}
