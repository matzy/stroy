package other.org.openCage.ui;

import com.google.inject.Inject;
import org.openCage.ui.protocol.MenuBuilder;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 15, 2009
 * Time: 11:29:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class MiniApp extends JFrame {

    @Inject
    public MiniApp( MenuBuilder mbuilder ) {

        mbuilder.setMenuOnFrame( this );

        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( new JButton("woooo"), BorderLayout.CENTER  );
        pack();



        setVisible( true );

    }

}
