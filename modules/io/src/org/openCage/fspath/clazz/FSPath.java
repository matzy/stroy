package org.openCage.fspath.clazz;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

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
public class FSPath implements Iterable<String> {

    private List<String> elements = new ArrayList<String>();

    public FSPath( String absolutePath ) {
        String[] els = absolutePath.split("/");

        elements.addAll( Arrays.asList( els ));
    }

    public FSPath( File file ) {
        this( file.getAbsolutePath());
    }
    
    public String toString() {
        return "/" + StringUtils.join( elements, '/');
    }

    public File toFile() {
        return new File( toString() );
    }

    public FSPath add( String element ) {
        FSPath ret = new FSPath("");
        ret.elements.addAll( elements );
        ret.elements.add( element );
        return ret;
    }

    public Iterator<String> iterator() {
        return elements.iterator();
    }

    public int size() {
        return elements.size();
    }

    public static FSPath getHome() {
        return new FSPath(SystemUtils.getUserHome());
    }

}
