package org.openCage.utils.io.with;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Jan 28, 2009
 * Time: 11:33:35 AM
 * To change this template use File | Settings | File Templates.
 */
public interface OutputStreamFunctor<T> {

    public T c( OutputStream os ) throws IOException;
}
