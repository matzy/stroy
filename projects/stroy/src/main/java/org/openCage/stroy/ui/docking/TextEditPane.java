package org.openCage.stroy.ui.docking;

import net.java.dev.designgridlayout.DesignGridLayout;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;


/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/14/12
 * Time: 7:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class TextEditPane extends JComponent {

    private RSyntaxTextArea textContent = new RSyntaxTextArea();
    private JScrollPane textWin = new JScrollPane( textContent );

    public TextEditPane() {

        DesignGridLayout dgl = new DesignGridLayout( this );

        dgl.row().grid().add( new JButton("here be text"));
        dgl.row().grid().add( textWin );

        ShowText.set(this);

    }

    public void setText(String str) {
        textContent.setText( str );
    }
}
