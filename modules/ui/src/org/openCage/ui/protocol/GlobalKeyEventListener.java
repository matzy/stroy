package org.openCage.ui.protocol;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 16, 2009
 * Time: 11:10:26 AM
 * To change this template use File | Settings | File Templates.
 */
public interface GlobalKeyEventListener {

    public Component getComponent();
    public boolean   keyMatches( KeyEvent event );
    public void      action();
}
