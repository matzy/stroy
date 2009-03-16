package org.openCage.stroy.mimetype;

import eu.medsea.mimeutil.MimeType;

import java.util.Collection;

public interface MimeFacade {

    public Collection<MimeType> getByExtension( String name );

    public Collection<MimeType> getMimetypes( String path );

    public boolean isText( Collection<MimeType> types );

    public boolean isXML( Collection<MimeType> types );
}
