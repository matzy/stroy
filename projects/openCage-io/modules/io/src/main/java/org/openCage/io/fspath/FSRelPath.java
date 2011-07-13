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

    List<String> elems = new ArrayList<String>();

    public FSRelPath( String ... elems ) {
        this.elems = Arrays.asList( elems );
    }

    public FSRelPath( List<String>elems ) {
        this.elems = elems;
    }

    public FSRelPath add( String ... more ) {
        FSRelPath ret = new FSRelPath( elems );

        ret.elems.addAll( Arrays.asList(more));

        return ret;
    }

    @Override
    public String toString() {
        return Strings.join( elems ).separator( ":" ).toString();
    }

    public List<String> getAsList() {
        return elems;
    }
}

