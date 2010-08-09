package org.openCage.io.fspath;

import org.apache.commons.lang.StringUtils;
import org.openCage.lang.errors.Unchecked;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FSPathUNC implements FSPath {

    private static final String PREFIX = "\\\\";

    private final List<String> elements = new ArrayList<String>();

    private FSPathUNC() {}

    public FSPathUNC(String absolutePath ) {
        if ( StringUtils.isEmpty( absolutePath )) {
            throw new IllegalArgumentException("an absolute path can't be empty");
        }

        if ( !absolutePath.startsWith( "\\\\")) {
            throw new IllegalArgumentException("no UNC path");
        }

        String[] els = absolutePath.substring(2).split(PREFIX);
        ElementRules.check( els );

        for ( String element : els ) {
            if ( !element.trim().isEmpty() ) {
                elements.add( element );
            }
        }

        if ( elements.size() == 0 ) {
            throw new IllegalArgumentException( "UNC paths have no root" );
        }
    }

    public String toString() {
        return PREFIX + StringUtils.join( elements, '\\');
    }

    @Override
    public File toFile() {
        return new File( toString() );
    }

    @Override
    public FSPath add(String... els) {
        ElementRules.check( els );

        FSPathUNC ret = new FSPathUNC( toString() );
        ret.elements.addAll( Arrays.asList( els ));
        return ret;
    }

    @Override
    public FSPath addPackage(String packageDescr) {
        String[] elems = packageDescr.split("\\\\.");

        return add( elems );
    }

    @Override
    public Iterator<FSPath> iterator() {
        List<FSPath> ret = new ArrayList<FSPath>();
        FSPath path =  null;
        for ( String el : elements ) {
            if ( path == null ) {
                path = new FSPathUNC( "\\\\" + el );
            } else {
                path = path.add( el );
            }
            ret.add( path );
        }
        return ret.iterator();
    }

    @Override
    public int size() {
        return elements.size();
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

    @Override
    public FSPath parent() {
        return parent(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof FSPathUNC)) { return false; }

        FSPathUNC fsPathUNC = (FSPathUNC) o;

        if (elements != null ? !elements.equals(fsPathUNC.elements) : fsPathUNC.elements != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return elements != null ? elements.hashCode() : 0;
    }
}
