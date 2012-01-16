package org.openCage.lishp;

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings({"ThisEscapedInObjectConstruction", "HardCodedStringLiteral", "StringConcatenation"})
public abstract class BuildinFunction extends Function {

    private static final Logger LOG = Logger.getLogger(BuildinFunction.class.getName());
    
    protected BuildinFunction(final Symbol special) {
        special.setGlobal( this );

        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine( "new buildin Function " + special );
        }
    }

    protected BuildinFunction(final String name) {
        this( Symbol.get(name));
    }
}
