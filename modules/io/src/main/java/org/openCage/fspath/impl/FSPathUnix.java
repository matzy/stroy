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

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/


// UNIX only for now, split in builder and path and xplatformize
public class FSPathUnix implements FSPath {

    private final List<String> elements = new ArrayList<String>();

    public FSPathUnix( String absolutePath ) {

        if ( StringUtils.isEmpty( absolutePath )) {
            throw new IllegalArgumentException("an absolute path can't be empty");
        }

        String[] els = absolutePath.split("/");

        for ( String element : els ) {
            if ( !element.trim().isEmpty() ) {
                elements.add( element );
            }
        }
        //elements.addAll( Arrays.asList( els ));
    }

    public FSPathUnix( File file ) {
        this( file.getAbsolutePath());
    }

    private FSPathUnix() {}

    public String toString() {
        return "/" + StringUtils.join( elements, '/');
    }

    public File toFile() {
        return new File( toString() );
    }

    @Override
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

    @Override
    public URI toURI() {
        try {
            return new URI("file:" + toString());
        } catch (URISyntaxException e) {
            throw Unchecked.wrap(e);
        }
    }

    @Override
    public FSPath parent(int i) {
        if ( i > elements.size() ) {
            throw new IllegalArgumentException( "to manny .." );
        }

        FSPathUnix ret = new FSPathUnix();
        ret.elements.addAll( elements.subList( 0, elements.size() - i ));

        return ret;

    }


}
