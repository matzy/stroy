package org.openCage.fspath.impl;

import org.apache.commons.lang.StringUtils;
import org.openCage.fspath.protocol.FSPath;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Jan 13, 2010
 * Time: 1:15:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class FSPathUNC implements FSPath {

    private final List<String> elements = new ArrayList<String>();


    public FSPathUNC(String absolutePath ) {
        if ( StringUtils.isEmpty( absolutePath )) {
            throw new IllegalArgumentException("an absolute path can't be empty");
        }

        if ( !absolutePath.startsWith( "\\\\")) {
            throw new IllegalArgumentException("no UNC path");
        }

        String[] els = absolutePath.substring(2).split("\\\\");

        for ( String element : els ) {
            if ( !element.trim().isEmpty() ) {
                elements.add( element );
            }
        }
    }

    public String toString() {
        return "\\\\" + StringUtils.join( elements, '\\');
    }

    @Override
    public File toFile() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FSPath add(String... els) {
        FSPathUNC ret = new FSPathUNC("\\\\");
        ret.elements.addAll( elements );
        ret.elements.addAll( Arrays.asList( els ));
        return ret;
    }

    @Override
    public Iterator<String> iterator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int size() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
