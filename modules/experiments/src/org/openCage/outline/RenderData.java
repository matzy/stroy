package org.openCage.outline;

import org.netbeans.swing.outline.RenderDataProvider;

import javax.swing.UIManager;
import java.io.File;

public class RenderData implements RenderDataProvider {

    @Override
    public java.awt.Color getBackground(Object o) {
        return null;
    }

    @Override
    public String getDisplayName(Object o) {
        return ((File) o).getName();
    }

    @Override
    public java.awt.Color getForeground(Object o) {
        File f = (File) o;
        if (!f.isDirectory() && !f.canWrite()) {
            return UIManager.getColor("controlShadow");
        }
        return null;
    }

    @Override
    public javax.swing.Icon getIcon(Object o) {
        return null;

    }

    @Override
    public String getTooltipText(Object o) {
        File f = (File) o;
        return f.getAbsolutePath();
    }

    @Override
    public boolean isHtmlDisplayName(Object o) {
        return false;
    }

}
