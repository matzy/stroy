package org.openCage.util.external;

import java.io.File;

import org.jdesktop.jdic.desktop.Desktop;
import org.jdesktop.jdic.desktop.DesktopException;

public class TestOpen {
    public static void main(String[] args) {
        try {
            Desktop.open(new File("C:\\s p  ce\\fo  o.txt"));
        } catch (DesktopException e) {
            e.printStackTrace();
        }
    }
}