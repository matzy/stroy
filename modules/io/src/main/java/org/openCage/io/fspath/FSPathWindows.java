package org.openCage.io.fspath;

import org.openCage.lang.Constants;
import org.openCage.lang.Strings;
import org.openCage.lang.errors.Unchecked;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class FSPathWindows extends FSPathBase {

    private final String drive;
    private List<String> elements = new ArrayList<String>();
    private Pattern driveLetter = Pattern.compile( "[a-zA-Z]\\:" );

    public FSPathWindows( String absolutePath ) {
        if ( Strings.isEmpty( absolutePath )) {
            throw new IllegalArgumentException("an absolute path can't be empty");
        }


        if ( absolutePath.length() < 2 || !driveLetter.matcher( absolutePath.substring(0,2)).matches() ) { 
            throw new IllegalArgumentException("no drive letter: " + absolutePath);
        }

        drive = absolutePath.substring( 0, 1 ).toUpperCase();

        String[] els = absolutePath.substring(2).split("\\\\");
        ElementRules.check( els );

        for ( String element : els ) {
            if ( !element.trim().isEmpty() ) {
                elements.add( element );
            }
        }
    }

    private FSPathWindows() {
        drive = "Z";
        throw new IllegalStateException( "need a path" );
    }

    public String toString() {
        return drive + ":\\" + Strings.join( elements ).separator("\\").toString();
    }


    @Override
    public FSPath add(String... elements) {
        ElementRules.check( elements );        
        // clone
        FSPathWindows ret = new FSPathWindows( toString() );
        ret.elements.addAll( Arrays.asList( elements ));
        return ret;
    }

    @Override
    public Iterator<FSPath> iterator() {
        List<FSPath> ret = new ArrayList<FSPath>();
        FSPath path = new FSPathWindows( drive + ":");
        ret.add( path );
        for ( String el : elements ) {
            path = path.add( el );
            ret.add( path );
        }
        return ret.iterator();
    }

    @Override
    public int size() {
        return elements.size() + 1;
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


    @Override
    public String getFileName() {
        return elements.get( elements.size() - 1 );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof FSPathWindows)) { return false; }

        FSPathWindows that = (FSPathWindows) o;

        if (drive != null ? !drive.equals(that.drive) : that.drive != null) { return false; }
        if (elements != null ? !elements.equals(that.elements) : that.elements != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = drive != null ? drive.hashCode() : 0;
        result = Constants.HASHFACTOR * result + (elements != null ? elements.hashCode() : 0);
        return result;
    }

    
}
