package org.openCage.stroy.ui.prefs;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/9/12
 * Time: 7:58 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class PrefsUI extends JFrame {
    protected abstract void createLayout();

    public abstract void showFileType(String fileName);

    public abstract void showUpdatePref();
}
