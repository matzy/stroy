package org.openCage.io.fspath;

import org.openCage.lang.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 08.07.11
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
public class FSRelPath {

    private char separator;

    List<String> elems = new ArrayList<String>();

    public FSRelPath( char separator, String ... elems ) {
        this.elems = Arrays.asList( elems );
        this.separator = separator;
    }

    public FSRelPath( char separator, List<String>elems ) {
        this.elems = elems;
        this.separator = separator;
    }

    public FSRelPath add( String ... more ) {
        FSRelPath ret = new FSRelPath( separator, elems );

        ret.elems.addAll( Arrays.asList(more));

        return ret;
    }

    @Override
    public String toString() {
        return Strings.join( elems ).separator( "" + separator ).toString();
    }
}

