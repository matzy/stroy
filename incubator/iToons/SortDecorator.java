/*
 * SortDecorator.java
 *
 * Created on November 18, 2004, 10:36 PM
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 *
 * @author  oc
 */
class SortDecorator implements TableModel, TableModelListener {
	private  TableModel realModel;
	private int indexes[];

	public SortDecorator(TableModel model) {
		if(model == null) {
			throw new IllegalArgumentException( "null models are not allowed");
                }
                
		this.realModel = model;

		realModel.addTableModelListener(this);
		allocate();
	}
        
	public Object getValueAt(int row, int column) {
		return realModel.getValueAt(indexes[row], column);
	}
        
	public void setValueAt(Object aValue, int row, int column) {
		realModel.setValueAt(aValue, indexes[row], column);
	}
        
	public void tableChanged(TableModelEvent e) {
		allocate();
	}
        
	public void sort( int column ) {
		int rowCount = getRowCount();

		for(int i=0; i < rowCount; i++) {
			for(int j = i+1; j < rowCount; j++) {
				if(compare(indexes[i], indexes[j], column) < 0) {
					swap(i,j);
				}
			}
		}
	}
           
        public void swap(int i, int j) {
		int tmp = indexes[i];
		indexes[i] = indexes[j];
		indexes[j] = tmp;
        }
        
	public int compare(int i, int j, int column) {
		Object io = realModel.getValueAt(i,column);
		Object jo = realModel.getValueAt(j,column);

		int c = jo.toString().compareTo(io.toString());
		return (c < 0) ? -1 : ((c > 0) ? 1 : 0);
	}
        
	private void allocate() {
		indexes     = new int[getRowCount()];

		for(int i=0; i < indexes.length; ++i) {
			indexes[i]    = i;
		}
	}
        
        public int getRealRow( int row ) {
            return indexes[ row ];
        }

	// TableModel pass-through methods follow ...

	public int getRowCount() {
		return realModel.getRowCount();
	}
        
	public int getColumnCount() {
		return realModel.getColumnCount();
	}
        
	public String getColumnName(int columnIndex) {
		return realModel.getColumnName(columnIndex);
	}
        
	public Class getColumnClass(int columnIndex) {
		return realModel.getColumnClass(columnIndex);
	}
        
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return realModel.isCellEditable(rowIndex, columnIndex);
	}
        
	public void addTableModelListener(TableModelListener l) {
		realModel.addTableModelListener(l);
	}
        
	public void removeTableModelListener(TableModelListener l) {
		realModel.removeTableModelListener(l);
	}
        
}
