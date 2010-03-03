package org.openCage.ui.clazz;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class BlockCaret extends DefaultCaret {


	private static final long serialVersionUID = 8640510055440186604L;

	protected synchronized void damage( final Rectangle rec ) {

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

	public void paint( final Graphics graphics ) {
		final JTextComponent textCompo = getComponent();
		final int            dot = getDot();
		final Rectangle            rec;
		final char                 dotChar;

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
//why opensource
//
//a) Different Roles
//
//As profesional software developer I write software for large and long running projects. The
//customer is well shielded through layers of support engineerd, product managers and distance.
//The product is so large that even after years of participation I will have seen only a small part of
//it and an even smaller part will be from me. As backend programmer I can take years before a customer
//sees anything written by me and by then I work on completly different aspect of the product line.
//
//Now as open source developer all this is different. You have to fullfill all the roles.
//
//- Architect
//- Designer (low and highlevel)
//- Gui Designer
//- Build Environment Maintainer
//- Installer builder
//- Legal Adviser
//- Coder
//- Release Manager
//- Advertiser
//- Translater
//- Documentation Writer
//- Support Engineer
//- Manager, time, people
//- reviewer
//- Web Page Designer / Maintainer
//- Distribution ...
//- Communication Pfleger
//- Competition Watch
//- Name designer
//- Product tracker
//
//When I decided to publish stroy as open source I did this for the same reasons as everbody else. I wanted to give back to the community
//and on the way become famous or at least get some feedback. By and by I discovered as probably all other in my shoes that you have to
//a lot more than just code down a neat idea. You have to find a publisher, write documentation worry about legal implications and and and.
//Some of these tasks came up before in my professional line of work a lot were new. In the beginning most of these task were annoying, they
//were just hinderences in my great endeavor to create great software. to my great surprise I found it more and more rewarding to look into
//all this roles. I still don't like all of them but just that there are so many different roles I can and have to fulfill is facinating.
//For some of the roles my approach is to find a definite solution and be done with it. Most you have to do once in a while.
//Many software devlopers don't like writing end user documentation. I am very much one of them, but I see the need so here is one role I can not
//push to someone else.
//But suprisingly same roles are actually fun.
//
//
//
//
//
//
//
