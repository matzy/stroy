package org.openCage.utils.io.with;

import java.io.InputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Jan 28, 2009
 * Time: 9:31:51 AM
 * To change this template use File | Settings | File Templates.
 */
public interface InputStreamFunctor<R> {

    public R c( InputStream is ) throws IOException;  
}
