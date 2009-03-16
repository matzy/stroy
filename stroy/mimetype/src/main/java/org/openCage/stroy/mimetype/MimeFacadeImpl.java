package org.openCage.stroy.mimetype;

import eu.medsea.mimeutil.MimeType;
import eu.medsea.mimeutil.MimeUtil;

import java.util.Collection;

public class MimeFacadeImpl implements MimeFacade {

    public Collection<MimeType> getByExtension( String name ) {
        return MimeUtil.getMimeTypes( "afgarg." + MimeUtil.getExtension( name ));
    }

    public Collection<MimeType> getMimetypes( String path ) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isText( Collection<MimeType> types ) {

        if ( isXML( types )) {
            return true;
        }

        for ( MimeType mt : types ) {
            if ( mt.getMediaType().equals( "text" ) ||
                    mt.toString().equals( "application/x-latex"  ) ||
                    mt.toString().equals( "application/x-tex"  )) {
                return true;
            }

        }

        return false;
    }

    public boolean isXML( Collection<MimeType> types ) {
        for ( MimeType mt : types ) {
            if ( mt.toString().contains( "xml"  )) {
                return true;
            }
        }

        return false;
    }
}
