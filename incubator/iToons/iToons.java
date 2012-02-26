/*
 * iToons.java
 *
 * Created on September 15, 2004, 3:00 PM
 */
import javax.swing.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

import smooth.SmoothLookAndFeelFactory;

import openCage.persistence.*;

/**
 *
 * @author  SPfab
 */
public class iToons extends javax.swing.JFrame {
    
    /** Creates new form iToons */
    public iToons() {

        Broker broker;
        try{
            broker = new Broker( "C:\\Documents and Settings\\SPfab\\iToons" );
            StandardData.setBroker( broker );
        } catch ( Exception e ) {
            e.printStackTrace();
            return;
        }

        
        comicsStore     = new ComicsStore( this );
        initEdi();
        initComponents();
        
        String data = "(";
        data += "\"iamname\"";
        data += "( (\"a Url\" #f) (\"fuh\" #t) )";
        data += "(\"Artistaaa\" \"bbb\" )";
        data += "\"*****\"";
        data += "\"hiatus\"";
        data += "(\"daily\" ())";
        data += "(\"English\" \"German\" \"Japanese\" \"Italian\" \"French\")";
        data += "\"*\"";
        data += "\"*\"";
        data += "\"**\"";
        data += "\"dudA\"";
        data += ")";
        
        List dataList = null;
        try  {        
            LispFormat frmt = new LispFormat();
            dataList = (java.util.List)frmt.parseObject( data );
            System.out.println( dataList );
        } catch ( java.text.ParseException e ) {
            System.out.println( "exception");
        }               

        newManualselection = new NewManualSelection( comicsStore );
        
        initTable();
        initSelections();
        
        //propertyEditor1.setData( dataList );
        
//        languages1.setTopFrame( this );
//        urls1.setTopFrame( this );
//        urls2.setTopFrame( this );
        
        jTable1.setDragEnabled( true );
        
        jFrameAbout.setSize( 500, 400 );
        
//        jTextFieldUrl2.setVisible( false );
//        jButtonGo2.setVisible( false );
//        jCheckBoxPrimaryUrl2.setVisible( false );
        
        jTable1.setModel( comicsStore );
        
        jTable1.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                public void valueChanged( ListSelectionEvent evt ) {
//                    System.out.println( evt.getFirstIndex());
//                    System.out.println( evt.getLastIndex());
                     if (evt.getValueIsAdjusting()) {
                        // The mouse button has not yet been released
                     } else {    
                        int row = jTable1.getSelectedRow();
                        if ( row > 0 ) {
                            comicSelected( comicsStore.getComic( row ));
                        }
                     }
                }
                
            });
            
        jTable1.setColumnSelectionAllowed(false);
        jTable1.setRowSelectionAllowed(true);            
        
        
        jTable1.setAutoCreateColumnsFromModel(false);
        
    
        // Sort all the rows in descending order based on the
        // values in the second column of the model
        //jTable1.sortAllRowsBy(model, 1, false);
        
        jList1.setModel( comicsStore.getSelections());

        jList1.getSelectionModel().addListSelectionListener( 
            new ListSelectionListener() {
                public void valueChanged( ListSelectionEvent evt ) {
                     if (evt.getValueIsAdjusting()) {
                        // The mouse button has not yet been released
                     } else {                     
                         comicsStore.setActive( jList1.getSelectedIndex());                         
                     }
                }
                
        });
              
        comicsStore.dataUpdate();
            
    }
    
    private void initTable() {
  
        // header sorting via decorator
        final SortDecorator decorator = new SortDecorator( comicsStore );
        jTable2.setModel( decorator );
        
        
        comicListColumns = new TableColumn[ jTable2.getColumnCount() ];
        
        for ( int i = 0; i < jTable2.getColumnCount(); ++i ) {
            comicListColumns[i] = jTable2.getColumn( jTable2.getColumnName( i ));
        }
        
//        String na = jTable2.getColumnName( 1 );        
//        jTable2.getColumnModel().removeColumn( jTable2.getColumn( "artist" ));
        
	JTableHeader hdr = (JTableHeader)jTable2.getTableHeader();

        hdr.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                        TableColumnModel tcm = jTable2.getColumnModel();
                        int vc = tcm.getColumnIndexAtX(e.getX());
                        int mc = jTable2.convertColumnIndexToModel(vc);

                        decorator.sort(mc);
                        //table.repaint();
                }
        });
        
        // row selection handling

        jTable2.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                public void valueChanged( ListSelectionEvent evt ) {
                     if (evt.getValueIsAdjusting()) {
                        // The mouse button has not yet been released
                     } else if ( jTable2.getSelectedRowCount() == 0 ) {
                         // no selection anymore
                     } else {    
                         int row = jTable2.getSelectedRow();
                         comicSelected( comicsStore.getComic( decorator.getRealRow(row )));
                     }
                }                
            });
        
        
    }
    
    private void initSelections() {
        jList2.setModel( comicsStore.getSelections());

        jList2.getSelectionModel().addListSelectionListener( 
            new ListSelectionListener() {
                public void valueChanged( ListSelectionEvent evt ) {
                     if (evt.getValueIsAdjusting()) {
                        // The mouse button has not yet been released
                     } else {                     
                         comicsStore.setActive( jList2.getSelectedIndex());                         
                     }
                }
                
        });
        
    }
    
    private void initEdi() {

        propEditTypes = Comic.getPropEditList();        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        buttonGroupUrl = new javax.swing.ButtonGroup();
        jFrameAbout = new javax.swing.JFrame();
        picMe = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable( comicsStore );
        jLabelStats = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        sep = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextAreaComment = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        propertyEditor1 = new PropertyEditor( this, propEditTypes );
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jSplitPane4 = new javax.swing.JSplitPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jLabelCurrentComic = new javax.swing.JLabel();
        jButtonGo = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemNew = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuView = new javax.swing.JMenu();
        jMenuColumns = new javax.swing.JMenu();
        jCheckBoxArtist = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxUrl1 = new javax.swing.JCheckBoxMenuItem();
        jMenuPlaf = new javax.swing.JMenu();
        jRadioButtonMenuItemSmoothMetal = new javax.swing.JRadioButtonMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        jMenuItem1.setText("Info");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });

        jPopupMenu1.add(jMenuItem1);

        jFrameAbout.getContentPane().setLayout(new java.awt.GridBagLayout());

        jFrameAbout.setTitle("About");
        jFrameAbout.setFont(new java.awt.Font("Verdana", 0, 10));
        picMe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picMe.setMaximumSize(new java.awt.Dimension(190, 240));
        picMe.setMinimumSize(new java.awt.Dimension(190, 240));
        picMe.setPreferredSize(new java.awt.Dimension(190, 240));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jFrameAbout.getContentPane().add(picMe, gridBagConstraints);

        jLabel11.setText(" iToons v0.5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 5, 0);
        jFrameAbout.getContentPane().add(jLabel11, gridBagConstraints);

        jLabel12.setText("a comics database");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jFrameAbout.getContentPane().add(jLabel12, gridBagConstraints);

        jLabel13.setText("GUI inspired by Apples (tm) itunes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jFrameAbout.getContentPane().add(jLabel13, gridBagConstraints);

        jLabel14.setText("Author: Stephan Pfab");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jFrameAbout.getContentPane().add(jLabel14, gridBagConstraints);

        jButton4.setText("ok");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 6, 0);
        jFrameAbout.getContentPane().add(jButton4, gridBagConstraints);

        jLabel16.setText("i.e. just like itunes but for comics and no music at all");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jFrameAbout.getContentPane().add(jLabel16, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Verdana", 0, 10));
        jButton1.setText("Contact: openCage@web.de");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        jFrameAbout.getContentPane().add(jButton1, gridBagConstraints);

        setTitle("iToons");
        setFont(new java.awt.Font("Verdana", 0, 10));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.setViewportView(jList1);

        jSplitPane2.setLeftComponent(jScrollPane3);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setShowHorizontalLines(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });

        jScrollPane2.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 2.0;
        jPanel5.add(jScrollPane2, gridBagConstraints);

        jLabelStats.setFont(new java.awt.Font("Verdana", 0, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel5.add(jLabelStats, gridBagConstraints);

        jSplitPane2.setRightComponent(jPanel5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jSplitPane2, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanel3);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel4.setFocusable(false);
        jPanel4.setEnabled(false);
        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\art\\pics\\Baby_Blues-allowance.gif"));
        jScrollPane1.setViewportView(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel4.add(jScrollPane1, gridBagConstraints);

        sep.setText("   ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel4.add(sep, gridBagConstraints);

        jLabel3.setText("    ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jLabel3, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 10));
        jLabel7.setText("Comment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jLabel7, gridBagConstraints);

        jTextAreaComment.setFont(new java.awt.Font("Verdana", 0, 10));
        jTextAreaComment.setLineWrap(true);
        jTextAreaComment.setTabSize(4);
        jTextAreaComment.setBorder(new javax.swing.border.EtchedBorder());
        jTextAreaComment.setMinimumSize(new java.awt.Dimension(150, 50));
        jTextAreaComment.setPreferredSize(new java.awt.Dimension(150, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridheight = 2;
        jPanel4.add(jTextAreaComment, gridBagConstraints);

        jLabel15.setText("jLabel15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        jPanel4.add(jLabel15, gridBagConstraints);

        jScrollPane4.setMinimumSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jScrollPane4, gridBagConstraints);

        propertyEditor1.setFont(new java.awt.Font("Verdana", 0, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(propertyEditor1, gridBagConstraints);

        jSplitPane1.setRightComponent(jPanel4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jSplitPane1, gridBagConstraints);

        jTabbedPane1.addTab("comics", jPanel1);

        jTabbedPane1.addTab("strips", jPanel2);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel7.add(jScrollPane5, gridBagConstraints);

        jSplitPane4.setLeftComponent(jPanel7);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabelCurrentComic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCurrentComicMouseClicked(evt);
            }
        });

        jScrollPane6.setViewportView(jLabelCurrentComic);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jScrollPane6, gridBagConstraints);

        jButtonGo.setText("Go");
        jButtonGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGoActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel9.add(jButtonGo, gridBagConstraints);

        jButtonEdit.setText("Edit");
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel9.add(jButtonEdit, gridBagConstraints);

        jButtonRemove.setText("Remove");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel9.add(jButtonRemove, gridBagConstraints);

        jSplitPane4.setRightComponent(jPanel9);

        jSplitPane3.setRightComponent(jSplitPane4);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jScrollPane7.setViewportView(jList2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jScrollPane7, gridBagConstraints);

        jSplitPane3.setLeftComponent(jPanel8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jSplitPane3, gridBagConstraints);

        jTabbedPane1.addTab("new table", jPanel6);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuItemNew.setText("New");
        jMenuItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewActionPerformed(evt);
            }
        });

        jMenu1.add(jMenuItemNew);

        jMenu2.setText("Import from ...");
        jMenuItem2.setText("old format");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });

        jMenu2.add(jMenuItem2);

        jMenu1.add(jMenu2);

        jMenuItem3.setText("New Selection");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });

        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("New Automatic Selection");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenuView.setText("View");
        jMenuColumns.setText("Columns");
        jCheckBoxArtist.setSelected(true);
        jCheckBoxArtist.setText("Artist");
        jCheckBoxArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxArtistActionPerformed(evt);
            }
        });

        jMenuColumns.add(jCheckBoxArtist);

        jCheckBoxUrl1.setSelected(true);
        jCheckBoxUrl1.setText("Url 1");
        jCheckBoxUrl1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxUrl1ActionPerformed(evt);
            }
        });

        jMenuColumns.add(jCheckBoxUrl1);

        jMenuView.add(jMenuColumns);

        jMenuPlaf.setText("plaf");
        jRadioButtonMenuItemSmoothMetal.setText("RadioButton");
        jRadioButtonMenuItemSmoothMetal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItemSmoothMetalActionPerformed(evt);
            }
        });

        jMenuPlaf.add(jRadioButtonMenuItemSmoothMetal);

        jMenuView.add(jMenuPlaf);

        jMenuBar1.add(jMenuView);

        jMenu3.setText("Help");
        jMenuItem5.setText("About");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });

        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }//GEN-END:initComponents

    private void jRadioButtonMenuItemSmoothMetalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItemSmoothMetalActionPerformed
        try {
            UIManager.setLookAndFeel("smooth.metal.SmoothLookAndFeel");
        } catch ( java.lang.Exception e ) {
            System.out.println( e.getStackTrace() );
        }
    }//GEN-LAST:event_jRadioButtonMenuItemSmoothMetalActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        ComicEditor edi = new ComicEditor( this, true, currentComic );
        edi.show();
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButtonGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGoActionPerformed
        BrowseToUrl.displayURL( currentComic.getUrl() );
    }//GEN-LAST:event_jButtonGoActionPerformed

    private void jCheckBoxUrl1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxUrl1ActionPerformed
        boolean selected = jCheckBoxUrl1.getSelectedObjects() != null;
        
        if ( selected ) {
            jTable2.addColumn( comicListColumns[2]);
        } else {
            jTable2.removeColumn( comicListColumns[2]);
        }

    }//GEN-LAST:event_jCheckBoxUrl1ActionPerformed

    private void jCheckBoxArtistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxArtistActionPerformed
        
        boolean selected = jCheckBoxArtist.getSelectedObjects() != null;
        
        if ( selected ) {
            jTable2.addColumn( comicListColumns[1]);
        } else {
            jTable2.removeColumn( comicListColumns[1] );
        }
    }//GEN-LAST:event_jCheckBoxArtistActionPerformed

    private void jLabelCurrentComicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCurrentComicMouseClicked
        BrowseToUrl.displayURL( currentComic.getUrl() );
    }//GEN-LAST:event_jLabelCurrentComicMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BrowseToUrl.displayURL( "mailto:openCage@web.de" );
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
    jFrameAbout.show();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        newManualselection.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewActionPerformed
        comicsStore.newComic();
        jTable1.getSelectionModel().clearSelection();
        jTable1.getSelectionModel().addSelectionInterval( comicsStore.getRowCount() -1, comicsStore.getRowCount() -1 );

        jTable2.getSelectionModel().clearSelection();
        jTable2.getSelectionModel().addSelectionInterval( comicsStore.getRowCount() -1, comicsStore.getRowCount() -1 );
        
        new ComicEditor( this, true, currentComic ).show();
        
    }//GEN-LAST:event_jMenuItemNewActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.out.println(  evt );
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        comicsStore.importFromLispAffair( "d:\\dot\\homepage\\comics\\comics.la" );
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        switch ( evt.getClickCount() ) {
            case 1:
                
                comicSelected( comicsStore.getComic( jTable1.rowAtPoint( evt.getPoint())));
                break;
            case 2:    
                comicsStore.gotoUrl( jTable1.rowAtPoint( evt.getPoint()));
                break;
            default:
        }
    }//GEN-LAST:event_jTable1MouseClicked
    
    public void setStats( String stats ) {
        jLabelStats.setText( stats );
    }

    public void comicSelected( Comic comic ) {

        currentComic = comic;
  
        System.out.println( comic.getName() );
        
//        String prefix = "D:\\dot\\homepage\\comics\\";
//        String fname  = comic.getBigLogo();
//        jLabel1.setIcon(new javax.swing.ImageIcon( prefix + fname ));        

//        jTextFieldName.setText( comic.getName() );

//        jTextFieldArtist.setText( comic.getArtist(0));
//       jTextFieldArtistsOther.setText( comic.getArtist( jComboBoxArtistNumber.getSelectedIndex() + 1) );
   
//        jTextFieldUrl.setText( comic.getUrl( 0 ));
//        if ( comic.getUrl(1) == "404") {
//            jTextFieldUrl2.setVisible( false );
//            jButtonGo2.setVisible( false );
//            jCheckBoxPrimaryUrl2.setVisible( false );
//        } else {
//            jTextFieldUrl2.setVisible( true );
//            jButtonGo2.setVisible( true );
//            jCheckBoxPrimaryUrl2.setVisible( true );
//            jTextFieldUrl2.setText( comic.getUrl( 1 ));
//        }
        
//	int selectedUrl = jComboBoxUrlNumber.getSelectedIndex();
//        
//        jCheckBoxPrimaryUrl.setSelected( selectedUrl == comic.getPrimaryUrlIdx());
        
//        jComboBoxRating.setSelectedIndex( comic.getRating() );
        
 //       jComboBoxFrequency.setSelectedIndex( comic.getFrequency() );
//        jComboBoxAlive.setSelectedIndex( comic.getAlive() );

        jTextAreaComment.setText( comic.getComment() );
        
        
        String prefix = new Dirs().getBigLogoDir();
        String fname  = comic.getBigLogo();
        jLabelCurrentComic.setIcon( new javax.swing.ImageIcon( prefix + fname ));        
    }
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        comicsStore.store();
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            UIManager.setLookAndFeel("smooth.metal.SmoothLookAndFeel");
        } catch ( java.lang.Exception e ) {
            System.out.println( e.getStackTrace() );
        }
        new iToons().show();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupUrl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonGo;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JCheckBoxMenuItem jCheckBoxArtist;
    private javax.swing.JCheckBoxMenuItem jCheckBoxUrl1;
    private javax.swing.JFrame jFrameAbout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelCurrentComic;
    private javax.swing.JLabel jLabelStats;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuColumns;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItemNew;
    private javax.swing.JMenu jMenuPlaf;
    private javax.swing.JMenu jMenuView;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemSmoothMetal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextAreaComment;
    private javax.swing.JLabel picMe;
    private PropertyEditor propertyEditor1;
    private javax.swing.JLabel sep;
    // End of variables declaration//GEN-END:variables

    private ComicsStore     comicsStore;
    private Comic           currentComic;
    private ComicSelections selection;
    private NewManualSelection newManualselection;
    
    private TableColumn[]   comicListColumns;
    
    private java.util.List   propEditTypes;
}
