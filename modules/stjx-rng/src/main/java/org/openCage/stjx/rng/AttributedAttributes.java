package org.openCage.stjx.rng;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Sep 13, 2010
 * Time: 10:33:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class AttributedAttributes {

    private final Attributes attis;
    private List<Integer> idxes = new ArrayList<Integer>();

    public AttributedAttributes( Attributes attis ) {
        this.attis = attis;
    }

    public String getValue( String name ) {
        String val = attis.getValue( name );

        if ( val != null ) {
            idxes.add( attis.getIndex( name ));
        }

        return val;
    }

    public void check() {
        for ( int idx = 0; idx < attis.getLength(); ++idx ) {
            if ( !idxes.contains( idx )) {
                throw new IllegalArgumentException( "Unknown Attribute: " + attis.getQName( idx ) + "=" + attis.getValue(idx));
            }
        }
    }

}
