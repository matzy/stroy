package org.openCage.fspath.protocol;

import java.io.File;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 30, 2009
 * Time: 10:25:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FSPath extends Iterable<String> {
    String toString();

    File toFile();

    FSPath add( String element );

    Iterator<String> iterator();

    int size();
}
