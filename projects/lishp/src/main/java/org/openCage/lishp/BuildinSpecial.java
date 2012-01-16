package org.openCage.lishp;

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("ThisEscapedInObjectConstruction")
public abstract class BuildinSpecial extends Special {

    private static final Logger LOG = Logger.getLogger(BuildinSpecial.class.getName());


    protected BuildinSpecial(final Symbol sym) {
        sym.setGlobal( this );

        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine( "new buildin Special " + sym );
        }
    }

    public BuildinSpecial(String name) {
        this( Symbol.get(name));
    }
}
