package org.openCage.lishp;

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings({"SerializableHasSerializationMethods", "HardCodedStringLiteral", "StringConcatenation", "DeserializableClassInSecureContext"})
public class LishpException extends RuntimeException {

    private static final long serialVersionUID = -660381123655774868L;
    private static final Logger LOG = Logger.getLogger( LishpException.class.getName());

    private final Symbol exceptionSymbol;

    public LishpException( final Symbol exceptionSymbol, final String message) {
        super( message );

        this.exceptionSymbol = exceptionSymbol;

        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine( "LishpException " + exceptionSymbol + " " + message );
        }
    }

    public Symbol getExceptionSymbol() {
        return exceptionSymbol;
    }
}
