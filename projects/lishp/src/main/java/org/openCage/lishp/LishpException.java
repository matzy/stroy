package org.openCage.lishp;

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings({"SerializableHasSerializationMethods", "HardCodedStringLiteral", "StringConcatenation", "DeserializableClassInSecureContext"})
public class LishpException extends RuntimeException {

    private static final long serialVersionUID = -660381123655774868L;
    private static final Logger LOG = Logger.getLogger( LishpException.class.getName());

    public LishpException(final String message) {        
        super( message );

        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine( "Lishpexception " + message );
        }
    }
}
