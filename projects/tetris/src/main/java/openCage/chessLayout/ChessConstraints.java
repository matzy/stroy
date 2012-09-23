package openCage.chessLayout;

import info.clearthought.layout.TableLayoutConstants;
import info.clearthought.layout.TableLayoutConstraints;

public class ChessConstraints {

	protected ChessColumn colFrom;
	protected ChessColumn colTo;
	protected ChessRow    rowFrom;
	protected ChessRow    rowTo;
	protected int         valign;
	protected int         halign;
	
	
	public ChessConstraints( Object ...objects ) {
		
		valign = TableLayoutConstants.FULL;
		halign = TableLayoutConstants.FULL;
		
		for ( Object obj : objects) {
			if ( obj instanceof ChessRow ) {
				if ( rowFrom == null ) {
					rowFrom = (ChessRow)obj;
				} else if ( rowTo == null ) {
					rowTo = (ChessRow)obj;
				} else {
					throw new IllegalArgumentException( "too many rows" );
				}
			} else if ( obj instanceof ChessColumn ) {
				if ( colFrom == null ) {
					colFrom = (ChessColumn)obj;
				} else if ( colTo == null ) {
					colTo = (ChessColumn)obj;
				} else {
					throw new IllegalArgumentException( "too many cols" );
				}
			} else if ( obj instanceof ChessHorizontalAlign ) {
				halign = ((ChessHorizontalAlign)obj).align;
			} else if ( obj instanceof ChessVerticalAlign ) {
				valign = ((ChessVerticalAlign)obj).align;
			}
		}
		
		if ( rowTo == null ) {
			rowTo = rowFrom;
		}
		
		if ( colTo == null ) {
			colTo = colFrom;
		}
		
		sanityCheck();
	}
	
	public ChessConstraints( ChessColumn col, ChessRow row ) {
		colFrom = col;
		colTo   = col;
		rowFrom = row;
		rowTo   = row;
		
		sanityCheck();
	}

	public ChessConstraints( ChessColumn col1, ChessRow row1, ChessColumn col2, ChessRow row2 ) {
		colFrom = col1;
		colTo   = col2;
		rowFrom = row1;
		rowTo   = row2;
		sanityCheck();
	}
	
	public TableLayoutConstraints getTableLayoutConstraints() {
		return new TableLayoutConstraints( colFrom.idx, 
				                          rowFrom.idx, 
				                          colTo.idx, 
				                          rowTo.idx,
				                          halign,
									    valign );
	}
	
	private void sanityCheck() {
		if ( colFrom == null) {
			throw new IllegalArgumentException( "no col specified" );			
		}
		
		if ( rowFrom == null) {
			throw new IllegalArgumentException( "no row specified" );			
		}
		
		if ( colFrom.idx > colTo.idx ||
		     rowFrom.idx > rowTo.idx ) {
			throw new IllegalArgumentException( "col or row in wrong order" );
		}
	}
}
