package org.openCage.fspath.protocol;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 30, 2009
 * Time: 10:22:57 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FSPathBuilder {

    public FSPath getPath( String str );
    public FSPath getPath( File file );
    public FSPath getHome();
}
