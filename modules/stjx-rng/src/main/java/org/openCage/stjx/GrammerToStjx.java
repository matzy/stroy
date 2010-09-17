package org.openCage.stjx;

import org.openCage.stjx.rng.*;
import org.openCage.stjx.rng.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Sep 13, 2010
 * Time: 9:43:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class GrammerToStjx {

//    public GrammerToStjx( Grammer grammer ) {
//        this.grammer = grammer;
//    }

    public Stjx getStjx( Grammer grammer ) {
        Stjx stjx = new Stjx( grammer.getStart().getRef().getName() );

        for ( Define def : grammer.getDefineList() ) {
            addDefine( stjx, def );
        }

        return stjx;
    }

    private void addDefine(Stjx stjx, Define def) {
        Struct str = stjx.struct( def.getName() );

        Element el = def.getElement();

        if ( !el.getName().equals( def.getName() )) {
            throw new IllegalArgumentException("argument and definition must have same name: " + def.getName() + "!=" + el.getName() );
        }

        // required attis

        for (Attribute atti : el.getAttributeList() ) {
            if ( atti.getText() != null ) {
                str.string( atti.getName() );
            } else {
                str.enm( atti.getName() );
            }
        }

        // optional attis

        for ( Optional opt : el.getOptionalList() ) {
            if ( opt.getAttribute() != null ) {

                if ( opt.getRef() != null ) {
                    throw new IllegalArgumentException( "optional must be either Attribute or Ref but not both" );
                }

                Attribute atti = opt.getAttribute();

                if ( atti.getText() != null ) {
                    str.optional().string( atti.getName() );
                } else {
                    str.optional().enm( atti.getName() );
                }
            }
        }

//        def.getElement().getAttributeList().
    }
}
