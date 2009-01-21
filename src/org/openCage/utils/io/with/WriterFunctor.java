package org.openCage.utils.io.with;

import java.io.Writer;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Jan 19, 2009
 * Time: 1:24:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface WriterFunctor {

    public void c( Writer writer ) throws IOException; 
}
