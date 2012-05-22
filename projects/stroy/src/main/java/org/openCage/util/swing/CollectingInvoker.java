package org.openCage.util.swing;

import javax.swing.*;

public class CollectingInvoker {

    private long           last = 0;
    private final Runnable full;
    private Runnable       step;
    private static final long DELAY = 200;

    public CollectingInvoker( Runnable full ) {
        this.full = full;
    }

    public void invokeFull() {
        invoke();
    }

    public void invokeStep( Runnable step ) {
        if ( step == null ) {
            this.step = step;
        } else {
            this.step = null;
        }

        invoke();
    }


    private void invoke() {
        if ( System.currentTimeMillis() - last < DELAY ) {
            return;
        }

        last = System.currentTimeMillis();

        if ( step != null ) {
            SwingUtilities.invokeLater( step );
        } else {
            SwingUtilities.invokeLater( full );
        }       
    }
}
