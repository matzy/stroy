/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.application.impl.pojos;

import org.openCage.application.protocol.Version;
import org.openCage.application.protocol.VersionParser;

/**
 *
 * @author stephan
 */
public class VersionParserImpl implements  VersionParser {

    public Version parse(String str) {
        String[] parts = str.split( "\\." );

        if ( parts.length == 3 ) {
            return new VersionImpl( Integer.parseInt( parts[0] ), Integer.parseInt( parts[1] ), 0, Integer.parseInt( parts[2] ) );
        }

        if ( parts.length == 4 ) {
            return new VersionImpl( Integer.parseInt( parts[0] ), Integer.parseInt( parts[1] ), Integer.parseInt( parts[2] ), Integer.parseInt( parts[3] ));
        }

        throw new IllegalArgumentException( "not a version" );
    }

}
