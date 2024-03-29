package org.openCage.outline;

import org.netbeans.swing.outline.RowModel;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.io.File;
import java.util.Date;

public class FileRowModel implements RowModel {

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return Date.class;
            case 1:
                return Long.class;
            case 2:
                return String.class;
            case 3:
                return Icon.class;
            default:
                assert false;
        }
        return null;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Date";
            case 1:
                return "Long";
            case 2:
                return "Flags";
            case 3:
                return "Icon";
            default:
                assert false;
        }
        return null;
    }

    @Override
    public Object getValueFor(Object node, int column) {
        File f = (File) node;
        switch (column) {
            case 0:
                return new Date(f.lastModified());
            case 1:
                return new Long(f.length());
            case 2:
            {
                String fl = "";
                if (f.canRead()) { fl+= "r";}
                if (f.canWrite()) { fl+= "w";};
                if (f.canExecute()) { fl+= "x";}
                return fl;
            }
            case 3:
                return f.canExecute() ? new ImageIcon( getClass().getResource( "add-icon.png" )) :
                        null ;
            default:
                assert false;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        return false;
    }

    @Override
    public void setValueFor(Object node, int column, Object value) {
        //do nothing for now
    }

}