package org.openCage.fspath.impl;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.openCage.fspath.protocol.FSPath;

import java.io.File;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 30, 2009
 * Time: 5:54:04 PM
 * To change this template use File | Settings | File Templates.
 */

// UNIX only for now, split in builder and path and xplatformize
public class FSPathUnix implements FSPath {

    private List<String> elements = new ArrayList<String>();

    public FSPathUnix( String absolutePath ) {

        if ( StringUtils.isEmpty( absolutePath )) {
            throw new IllegalArgumentException("an absolute path can't be empty");
        }

        String[] els = absolutePath.split("/");

        elements.addAll( Arrays.asList( els ));
    }

    public FSPathUnix( File file ) {
        this( file.getAbsolutePath());
    }
    
    public String toString() {
        return "/" + StringUtils.join( elements, '/');
    }

    public File toFile() {
        return new File( toString() );
    }

    public FSPath add( String ... els ) {
        FSPathUnix ret = new FSPathUnix("/");
        ret.elements.addAll( elements );
        ret.elements.addAll( Arrays.asList( els ));
        return ret;
    }

    public Iterator<String> iterator() {
        return elements.iterator();
    }

    public int size() {
        return elements.size();
    }


}
