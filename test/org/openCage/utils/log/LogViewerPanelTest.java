package org.openCage.utils.log;

import org.junit.Test;

import javax.swing.*;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Jan 16, 2009
 * Time: 7:04:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogViewerPanelTest {

    class MiniFrame extends JFrame {
        public MiniFrame() {
            getContentPane().add( new LogViewerPanel());
        }
    }


    @Test
    public void testStd() {
        new MiniFrame().setVisible(true);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Logger.getLogger("foo.foo").warning("a warning");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
