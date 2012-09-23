package org.openCage.kleinod.ui;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

public class BlockCaret extends DefaultCaret {


	private static final long serialVersionUID = 8640510055440186604L;

	protected synchronized void damage( Rectangle rec ) {
		
		if ( rec == null ) {
			return;
		}
		
		x = rec.x;
		y = rec.y;
		height = rec.height;
		if ( width <= 0 ) {
			width = getComponent().getWidth(); 
		}
		repaint();
	}
	
	public void paint( Graphics graphics ) {
		final JTextComponent textCompo = getComponent();
		final int            dot = getDot();
		Rectangle            rec = null;
		char                 dotChar;
		
		if ( textCompo == null ) {
			return;
		}
		
		try {
			rec = textCompo.modelToView(dot);
			if ( rec == null ) {
				return;
			}
			dotChar = textCompo.getText( dot,1).charAt(0);
		} catch ( BadLocationException exp ) {
			return;
		}
		
		graphics.setColor( textCompo.getCaretColor() );
		graphics.setXORMode(textCompo.getBackground());

		// cover the current char or at least 3 pixels
		width = Math.max( 3, graphics.getFontMetrics().charWidth( dotChar ));
		
		if ( isVisible()) {
			graphics.fillRect( rec.x, rec.y, width, rec.height);
		}
	}
}
