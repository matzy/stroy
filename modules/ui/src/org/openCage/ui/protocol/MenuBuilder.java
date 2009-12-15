package org.openCage.ui.protocol;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 14, 2009
 * Time: 5:50:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MenuBuilder {

    public void setMenuOnFrame( JFrame frame );
    public MenuBuilder withExit();
    public MenuBuilder withView();
    public MenuBuilder withAbout();
    public MenuBuilder withHelp();
    public MenuBuilder withPreferences();
}
