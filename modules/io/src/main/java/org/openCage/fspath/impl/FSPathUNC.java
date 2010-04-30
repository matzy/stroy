package org.openCage.fspath.impl;

import org.apache.commons.lang.StringUtils;
import org.openCage.fspath.protocol.FSPath;
import org.openCage.lang.errors.Unchecked;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
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

    private FSPathUNC() {}

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
        return new File( toString() );
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
        throw new Error( "TODO" );
    }

    @Override
    public int size() {
        throw new Error( "TODO" );
    }

    @Override
    public URI toURI() {
        try {
            return new URI( "file://" + toString().replace( '\\', '/') );
        } catch (URISyntaxException e) {
            throw Unchecked.wrap(e);
        }
    }

    @Override
    public FSPath parent(int i) {

        if ( i > elements.size() ) {
            throw new IllegalArgumentException( "to manny .." );
        }

        FSPathUNC ret = new FSPathUNC();
        ret.elements.addAll( elements.subList( 0, elements.size() - i ));

        return ret;
    }
}
