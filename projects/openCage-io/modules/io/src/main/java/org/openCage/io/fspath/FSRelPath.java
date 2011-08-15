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

    private final List<String> elems = new ArrayList<String>();

    public FSRelPath( String ... elems ) {
        // add all to keep the arraylist which allows adding
        // eve if it is only done to add to the newly created, see below
        this.elems.addAll(Arrays.asList(elems));
    }

    public FSRelPath( List<String> elems ) {
        this.elems.addAll( elems );
    }

    public FSRelPath add( String ... more ) {
        FSRelPath ret = new FSRelPath( elems );

        ret.elems.addAll( Arrays.asList(more));

        return ret;
    }

    @Override
    public String toString() {
        return Strings.join( elems ).separator( "/" ).toString();
    }

    public List<String> getAsList() {
        return elems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FSRelPath fsRelPath = (FSRelPath) o;

        if (elems != null ? !elems.equals(fsRelPath.elems) : fsRelPath.elems != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return elems != null ? elems.hashCode() : 0;
    }
}
