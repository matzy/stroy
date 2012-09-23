//package org.openCage.stroy.todo;
//
//
//import net.java.dev.designgridlayout.DesignGridLayout;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
///**
// * Created by IntelliJ IDEA.
// * User: spfab
// * Date: Jul 10, 2007
// * Time: 3:53:58 PM
// * To change this template use File | Settings | File Templates.
// */
//public class DG extends JFrame {
//
//    public DG() {
////        DesignGridLayout layout = new DesignGridLayout( this );
////		this.setLayout( layout );
//
////        layout.row().label( "Last Name" ).add( new JTextField(), 2 );
//
//        JFrame frame = new JFrame( "Example 1a" );
//        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//        JPanel top = new JPanel();
//        DesignGridLayout layout = new DesignGridLayout( top );
//        frame.setLayout( layout );
//
//        // You can add components one line at a time
//        Row row = layout.row();
//        row.add( new JButton( "1" ));
//        row.add( new JButton( "1" ) );
//
//        // Or using method chaining
//        layout.row().add( new JButton( "1" ),3 ).add( new JLabel( "dudaaaaaaaaa drthg" )).add( new JButton( "1" ), 2 );
//
//        // Or, even better, using variable arguments
//        layout.row().add( new JButton( "1" ), new JButton( "1" ) );
//
//        frame.add( top );
//        frame.pack();
//        frame.setVisible( true );
//
//    }
//
//    public static void main(String[] args) {
//
//        new DG();
//    }
//}
