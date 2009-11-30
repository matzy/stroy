package org.openCage.fspath.impl;

import org.apache.commons.lang.SystemUtils;
import org.openCage.fspath.protocol.FSPath;
import org.openCage.fspath.protocol.FSPathBuilder;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 30, 2009
 * Time: 10:28:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class FSPathBuilderUnix implements FSPathBuilder {

    public FSPath getPath(String str) {
        return new FSPathUnix( str );
    }

    public FSPath getPath(File file) {
        return getPath( file.getAbsolutePath() );
    }

    public FSPath getHome() {
        return new FSPathUnix(SystemUtils.getUserHome());
    }
}
