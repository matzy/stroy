package org.openCage.stroy.ui.difftree.ep;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.awt.*;

public class EPJTree extends JTree {

    public EPJTree( TreeNode root) {
        super(root );
        setLargeModel( true );
    }

    public EPJTree( Object[] objs) {
        super(objs );
        setLargeModel( true );
    }


    @Override
    protected void paintComponent( Graphics g) {

        Graphics2D graphics2D = (Graphics2D) g.create();
        //        BackgroundPainter ff;
        //        fBackgroundPainter.paint(graphics2D,this,getWidth(),getHeight());
//        graphics2D.clearRect( 0, 0, getWidth(), getHeight() );


        graphics2D.dispose();

        // paint the background for the selected entry, if there is one.
        // NOTE: this code assumes there is only one selected row, but
        // you could loop over the selected row indicies if desired.
        int selectedRow = getSelectionModel().getLeadSelectionRow();
        // get the bounds of the selected row.
        Rectangle bounds = getRowBounds(selectedRow);

        if (selectedRow > 0 && isVisible(getPathForRow(selectedRow))) {
            graphics2D = (Graphics2D) g.create();
            graphics2D.translate(0, bounds.y);


            // create a GraidentPaint here and set the graphics context to
            // use it (my code was using SwingX Painters, so IÕve not
            // included this.

//            WatermarkPainter
            int width = getWidth();
            int height = getHeight();

            // Create the gradient paint
            GradientPaint paint = new GradientPaint(0, 0, Color.GRAY, 0, height / 2, Color.BLUE, false);
            graphics2D.setPaint(paint);

            // fill a rectangle with the gradient paint. notice how we always
            // start from the left edge and fill all the way to the right edge,
            // ignoring the bounds x-values.
            graphics2D.fillRect(0, bounds.y, getWidth(), bounds.height);

            graphics2D.dispose();
        }

         super.paintComponent(g);
    }
}
