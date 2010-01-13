package org.openCage.fspath.impl;

import org.apache.commons.lang.StringUtils;
import org.openCage.fspath.protocol.FSPath;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Jan 13, 2010
 * Time: 1:02:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class FSPathWindows implements FSPath {

    private String drive;
    private List<String> elements = new ArrayList<String>();

    public FSPathWindows(String absolutePath) {
        if ( StringUtils.isEmpty( absolutePath )) {
            throw new IllegalArgumentException("an absolute path can't be empty");
        }

        if ( !absolutePath.startsWith( "C:" )) { // TODO
            throw new IllegalArgumentException("no drive letter: " + absolutePath);
        }

        drive = absolutePath.substring( 0, 1 );

        String[] els = absolutePath.substring(2).split("\\\\");

        for ( String element : els ) {
            if ( !element.trim().isEmpty() ) {
                elements.add( element );
            }
        }



    }

    public String toString() {
        return drive + ":\\" + StringUtils.join( elements, '\\');
    }

    @Override
    public File toFile() {
        return new File( toString() );
    }

    @Override
    public FSPath add(String... elements) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
