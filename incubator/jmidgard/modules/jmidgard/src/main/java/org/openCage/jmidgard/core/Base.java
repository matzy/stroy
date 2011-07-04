package org.openCage.jmidgard.core;

import org.openCage.io.fspath.FSPath;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 04.07.11
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class Base {

    public FSPath getRootDir() {
        return rootDir;
    }

    public Base setRootDir(FSPath rootDir) {
        this.rootDir = rootDir;
        return this;
    }

    private FSPath rootDir;
}
