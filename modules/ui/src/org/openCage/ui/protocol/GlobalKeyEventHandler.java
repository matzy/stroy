package org.openCage.ui.protocol;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 16, 2009
 * Time: 2:53:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GlobalKeyEventHandler {

    public void addListener( GlobalKeyEventListener listener );
    public void addCloseWindow( final Component compo  );

}
