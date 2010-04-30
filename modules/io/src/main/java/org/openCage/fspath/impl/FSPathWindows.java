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
import java.util.regex.Pattern;

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
    private Pattern driveLetter = Pattern.compile( "[a-zA-Z]\\:" );

    public FSPathWindows(String absolutePath) {
        if ( StringUtils.isEmpty( absolutePath )) {
            throw new IllegalArgumentException("an absolute path can't be empty");
        }


        if ( absolutePath.length() < 2 || !driveLetter.matcher( absolutePath.substring(0,2)).matches() ) { 
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

    private FSPathWindows() {}

    public String toString() {
        return drive + ":\\" + StringUtils.join( elements, '\\');
    }

    @Override
    public File toFile() {
        return new File( toString() );
    }

    @Override
    public FSPath add(String... elements) {
        // clone
        FSPathWindows ret = new FSPathWindows( toString() );
        ret.elements.addAll( Arrays.asList( elements ));
        return ret;
    }

    @Override
    public Iterator<String> iterator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public URI toURI() {
        try {
            return new URI( "file:///" + toString().replace( '\\', '/') );
        } catch (URISyntaxException e) {
            throw Unchecked.wrap(e);
        }
    }

    @Override
    public FSPath parent(int i) {
        if ( i > elements.size() ) {
            throw new IllegalArgumentException( "to manny .." );
        }

        FSPathWindows ret = new FSPathWindows();
        ret.elements.addAll( elements.subList( 0, elements.size() - i ));

        return ret;
    }
}
