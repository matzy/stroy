package org.openCage.ui.impl;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 17, 2009
 * Time: 7:42:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class BrowseTmp {

    public void browse( URI txt ) {
        try {
            Desktop.getDesktop().browse( txt );
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
