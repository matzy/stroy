package openCage.chessLayout;

import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstants;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Vector;

public class ChessLayout implements
	java.awt.LayoutManager2 {
	
	public static ChessVerticalAlign   top               = new ChessVerticalAlign( TableLayoutConstants.TOP );
	public static ChessVerticalAlign   vertical_full     = new ChessVerticalAlign( TableLayoutConstants.FULL );
	public static ChessVerticalAlign   vertical_center   = new ChessVerticalAlign( TableLayoutConstants.CENTER );
	public static ChessVerticalAlign   botton            = new ChessVerticalAlign( TableLayoutConstants.BOTTOM );

	public static ChessHorizontalAlign left              = new ChessHorizontalAlign( TableLayoutConstants.LEFT );
	public static ChessHorizontalAlign horizontal_full   = new ChessHorizontalAlign( TableLayoutConstants.FULL );
	public static ChessHorizontalAlign horizontal_center = new ChessHorizontalAlign( TableLayoutConstants.CENTER );
	public static ChessHorizontalAlign right             = new ChessHorizontalAlign( TableLayoutConstants.RIGHT );

	private TableLayout           tableLayout;
	private Vector< ChessRow >    rows;
	private Vector< ChessColumn > cols;

	public ChessLayout() {
		rows        = new Vector<ChessRow>();
		cols        = new Vector<ChessColumn>();		
		tableLayout = new TableLayout();
	}
	
	public ChessRow addRow( double size ) {
		
		ChessRow row = new ChessRow( rows.size() );
		tableLayout.insertRow( row.idx, size );
		rows.add( row );
		
		return row;
	}
	
	public ChessRow addRowFill() {
		return addRow( TableLayoutConstants.FILL );
	}
	
	public ChessRow addRowPrefered() {
		return addRow( TableLayoutConstants.PREFERRED );		
	}

	public ChessRow addRowMinimum() {
		return addRow( TableLayoutConstants.MINIMUM );		
	}
	
//	public ChessRow insert( ChessRow beforeRow, double size ) {
//		return new ChessRow();
//	}
	
	public ChessColumn addColumn( double size ) {
		
		ChessColumn col = new ChessColumn( cols.size() );
		tableLayout.insertColumn( col.idx, size );
		cols.add( col );
		
		return col;
	}

	public ChessColumn addColumnFill() {
		return addColumn( TableLayoutConstants.FILL );
	}
	
	public ChessColumn addColumnPrefered() {
		return addColumn( TableLayoutConstants.PREFERRED );		
	}

	public ChessColumn addColumnMinimum() {
		return addColumn( TableLayoutConstants.MINIMUM );		
	}
	
//	public ChessColumn insert( ChessColumn beforeCol, int constraint ) {
//		
//	}
	
	public void addLayoutComponent(Component comp, Object cnstr ) {
		if ( !(cnstr instanceof ChessConstraints)) {
	        throw new IllegalArgumentException( "Cannot accept a constraint of class " + cnstr.getClass());			
		};
		
		
  		tableLayout.addLayoutComponent( comp, ((ChessConstraints)cnstr).getTableLayoutConstraints() );
	}

	public Dimension maximumLayoutSize(Container arg0) {
		// TODO Auto-generated method stub
		return tableLayout.maximumLayoutSize(arg0);
	}

	public float getLayoutAlignmentX(Container arg0) {
		return tableLayout.getLayoutAlignmentX(arg0);
	}

	public float getLayoutAlignmentY(Container arg0) {
		return tableLayout.getLayoutAlignmentY(arg0);
	}

	public void invalidateLayout(Container arg0) {
		tableLayout.invalidateLayout(arg0);
	}

	public void addLayoutComponent(String  cnstr, Component comp ) {
		addLayoutComponent(comp, cnstr);
	}

	public void removeLayoutComponent(Component arg0) {
		// TODO Auto-generated method stub
		
	}

	public Dimension preferredLayoutSize(Container arg0) {
		return tableLayout.preferredLayoutSize(arg0);
	}

	public Dimension minimumLayoutSize(Container arg0) {
		return tableLayout.minimumLayoutSize(arg0);
	}

	public void layoutContainer(Container arg0) {
//		System.out.println("chess: layoutContainer called");
		tableLayout.layoutContainer(arg0);
	}
}
