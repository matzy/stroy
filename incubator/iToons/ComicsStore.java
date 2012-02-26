/*
 * ComicsStore.java
 *
 * Created on September 15, 2004, 4:28 PM
 */

import javax.swing.table.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author  SPfab
 */
public class ComicsStore extends AbstractTableModel {
    
    /** Creates a new instance of ComicsStore */
    public ComicsStore( iToons itoons ) {
        this.itoons = itoons;
        comicList = new Vector();
        dirs = new Dirs();
        
        comicListLocation         = dirs.getComicsFileName();
        manualSelectionsLocation  = dirs.getManualSelctionsFileName();
 
        selections = new ComicSelections();
    
        selections.add( new AbstractAutomaticSelection( "Library", comicList ) {
            public boolean isSystem() {
                return true;
            }
            
            public boolean belongs( Comic comic ) {
                return true;
            }
        });        
        
        selections.add( new AbstractAutomaticSelection( "best daily", comicList ) {
            public boolean isSystem() {
                return true;
            }
            
            public boolean belongs( Comic comic ) {
                return comic.getRating() > 5 &&
                       comic.getFrequency() == 2;                         
                }
            
        });        

        selections.add( new AbstractAutomaticSelection( "best weekly", comicList ) {
            public boolean isSystem() {
                return true;
            }
            
            public boolean belongs( Comic comic ) {
                return comic.getRating() > 5 &&
                       comic.getFrequency() == 1;                         
                }
            
        });        

        selections.add( new AbstractAutomaticSelection( "best rest", comicList ) {
            public boolean isSystem() {
                return true;
            }
            
            public boolean belongs( Comic comic ) {
                return comic.getRating() > 5 &&
                       comic.getFrequency() == 0;                         
                }
            
        });        

        selections.add( new AbstractAutomaticSelection( "unrated", comicList ) {
            public boolean isSystem() {
                return true;
            }
            
            public boolean belongs( Comic comic ) {
                return comic.getRating() == 2;
            }
            
        });        
        
        load();
                
    }
    
    public int getColumnCount() {
        return 4;
    }
    
    public int getRowCount() {
        return selections.getSelection( activeSelection ).size();
    }
    
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        if ( columnIndex == 0 ) {
            return selections.getSelection( activeSelection ).get( rowIndex ).getName();
        } else if ( columnIndex == 1 ) {
            return selections.getSelection( activeSelection ).get( rowIndex ).getArtistsText();
        } else if ( columnIndex == 2 ) {
            return selections.getSelection( activeSelection ).get( rowIndex ).getUrl();
        } else if ( columnIndex == 3 ) {
            return selections.getSelection( activeSelection ).get( rowIndex ).getRatingText();
        }
        
        return "duh";
    }
        
    
    public String getColumnName(int column) {
        if ( column == 0 ) {
            return "name";
        } else if ( column == 1 ) {
            return "artist";        
        } else if ( column == 2 ) {
            return "url";        
        } else if ( column == 3 ) {
            return "rating";            
        }
        
        return "huh";
    }
    
    public void newComic() {
        Comic newC = new Comic( "--unanmed--", "404");
        comicList.add( newC );
        selections.update();
        
        fireTableRowsInserted( comicList.size() -2, comicList.size() - 1 );
        
    }
    
    public void gotoUrl( int row ) {

        BrowseToUrl.displayURL( selections.getSelection( activeSelection ).get(row ).getUrl() );
    }
    
    public String getBigLogo( int row ) {

        return selections.getSelection( activeSelection ).get( row ).getBigLogo();
    }
    
    public void importFromFastmdb( String fileName ) {
        
    }
    
    public void importFromLispAffair( String fileName ) {
        
        int oldSize = comicList.size();
        
        LispFormat frmt = new LispFormat();
        try{
            Object obj = frmt.parseObject( "(fct (x) x)" );
            System.out.println( frmt.format( obj ));
        } catch ( java.text.ParseException e ) {
            System.out.println( "exception");
        }

        FileInputStream in;
        try {
            in = new FileInputStream( fileName );
        } catch ( java.io.FileNotFoundException e ) {
            System.out.println( "la import: file " + fileName + " not found" );            
            return;
        }
        
        byte buffer[] = new byte[1000000];
        Object result;
        try{
            in.read( buffer, 0, 1000000 );        
        
            Object oin = frmt.parseObject( new String( buffer));
            result = Eval.eval( oin, lispaffair.getStdEnv() );  
        } catch ( Exception e ) {
            //
            return;
        }
        
//        System.out.println( result );
        
        java.util.List lst = (java.util.List)result;
        
        for ( int i = 0; i < lst.size(); i++) {
//            addLAComic( (java.util.List)lst.get(i));
            comicList.add( new Comic( 5, (java.util.List)lst.get(i)));
        }
            
        selections.update();
        fireTableRowsInserted( oldSize, comicList.size() - 1 );

    }
    
    public Comic getComic( int idx ) {
        return selections.getSelection( activeSelection ).get( idx );
    }
    
    private void addLAComic( java.util.List laComic ) {
        // find name
        Symbol sName = Symbol.get( "Name");
        Symbol sUrl  = Symbol.get( "Url");
        String name  = "";
        String url   = "";
        
        for ( int i = 1; i < laComic.size(); i++) {
            java.util.List tag = (java.util.List)laComic.get(i);
            
            if ( sName == tag.get(0)) {
                name = (String)tag.get(1);
            } else if ( sUrl == tag.get(0)){
                url = (String)tag.get(1);
            }
        }
        
        comicList.add( new Comic( name, url ));            
        
    }
    
    public void store() {

        try{
            FileOutputStream out = new FileOutputStream( comicListLocation );

            byte bufferA[] = "'(".getBytes();
            out.write( bufferA, 0, bufferA.length );

            byte bufferN[] = "\n".getBytes();
                        
            for ( int i = 0; i < comicList.size(); ++i ) {
                String comicsString = "" + (Comic)comicList.get(i);
                byte buffer[] = comicsString.getBytes();
                out.write( buffer, 0, buffer.length );
                out.write( bufferN, 0, bufferN.length );
            }
            
            byte bufferB[] = ")".getBytes();
            out.write( bufferB, 0, bufferB.length );


            out.close();
        } catch ( Exception e ) {
            System.out.println( "store problem" );
            e.printStackTrace();
        }
        try{
            FileOutputStream out = new FileOutputStream( manualSelectionsLocation );

            byte bufferA[] = "'(".getBytes();
            out.write( bufferA, 0, bufferA.length );

            byte bufferN[] = "\n".getBytes();
                        
            for ( int i = 0; i < selections.getSize(); ++i ) {
                Selection sel = selections.getSelection( i );
                
                if ( !sel.isSystem() ) {
                    String outStr = "" + sel;
                    byte buffer[] = outStr.getBytes();
                    out.write( buffer, 0, buffer.length );
                    out.write( bufferN, 0, bufferN.length );                    
                }
            }
            
            byte bufferB[] = ")".getBytes();
            out.write( bufferB, 0, bufferB.length );


            out.close();
        } catch ( Exception e ) {
            System.out.println( "store problem" );
            e.printStackTrace();
        }

    }
    
    public void load() {        
        LispFormat frmt = new LispFormat();

        FileInputStream in;
        try {
            in = new FileInputStream( comicListLocation );
        } catch ( java.io.FileNotFoundException e ) {
            System.out.println( "load: file " + comicListLocation + " not found" );            
            return;
        }
        
        byte buffer[] = new byte[1000000];
        Object result;
        try{
            in.read( buffer, 0, 1000000 );        
        
            Object oin = frmt.parseObject( new String( buffer));
            result = Eval.eval( oin, lispaffair.getStdEnv() );  
        } catch ( Exception e ) {
            //
            return;
        }
        
        java.util.List lst = (java.util.List)result;
        
        if ( lst != null ) {
            for ( int i = 0; i < lst.size(); i++) {
                comicList.add( new Comic( (java.util.List)lst.get(i) ));
            }
        }

        // manual selections
        try {
            in = new FileInputStream( manualSelectionsLocation );
        } catch ( java.io.FileNotFoundException e ) {
            System.out.println( "load manual selections: file " + manualSelectionsLocation + " not found" );            
            return;
        }
        
        try{
            in.read( buffer, 0, 1000000 );        
        
            Object oin = frmt.parseObject( new String( buffer));
            result = Eval.eval( oin, lispaffair.getStdEnv() );  
        } catch ( Exception e ) {
            //
            return;
        }
        
        lst = (java.util.List)result;
        
        for ( int i = 0; i < lst.size(); i++) {
            selections.add( new ManualSelection( (java.util.List)lst.get(i) ));
        }

        
        
        selections.update();
        fireTableRowsInserted( 0, comicList.size() - 1 );

        
    }
    
    public void setActive( int idx ) {
        activeSelection = idx;
        dataUpdate();        
    }
    
    public void dataUpdate() {
        selections.update();
        
        String stats = "" + 
                       selections.getSelection( activeSelection ).size() +
                       " comics";
        
        itoons.setStats( stats );
        fireTableDataChanged();                
        store();        
    }
    
    public ComicSelections getSelections() {
        return selections;
    }
    
    public void addSelection( Selection sel ) {
        selections.add( sel );
    }
    
    public Comic getComic( GUID guid ) {
        for ( int i = 0; i < comicList.size(); i++ ) {
            if ( ((Comic)comicList.get(i)).getID() == guid ) {
                return (Comic)comicList.get(i);
            }
        }
        
        return null;
    }
    
    
    private Vector  comicList;
    private String  comicListLocation;
    private String  manualSelectionsLocation;    
    
    private ComicSelections selections;
    private int             activeSelection;
    private Dirs            dirs;
    
    private iToons          itoons;

}

