/*
 * Dirs.java
 *
 * Created on September 20, 2004, 10:26 AM
 */

import java.io.*;

/**
 *
 * @author  SPfab
 */
public class Dirs {
    
    /** Creates a new instance of Dirs */
    public Dirs() {
        
        frmt = new LispFormat();

        String home = System.getProperty( "user.home" );        
        
        //home += "\\iToons.la";
        // home += "/.iToons.la";
        

//        FileInputStream in;
//        try {
//            in = new FileInputStream( home );
//            byte buffer[] = new byte[10000];
//            Object result;
//            try{
//                in.read( buffer, 0, 10000 );        
//                Object oin = frmt.parseObject( new String( buffer));
//                result = Eval.eval( oin, lispaffair.getStdEnv() );  
//                baseDir = (String)result;                
//            } catch ( Exception e ) {
//                //
//                return;
//            }
//            
//        } catch ( java.io.FileNotFoundException e ) {
//            // TODO dialog
//            
//            baseDir = "/home/oc/projects/itoons";            
//            storeBaseDir( home, home );                                    
//        }
    }
    
    private void storeBaseDir( String home, String dir ) {
        try{
            FileOutputStream out = new FileOutputStream( home );

//            byte bufferA[] = "'(".getBytes();
//            out.write( bufferA, 0, bufferA.length );

//            byte bufferN[] = "\n".getBytes();

            dir = "\"" + dir + "\"";
            byte buffer[] = dir.getBytes();
            out.write( buffer, 0, buffer.length );
            out.close();
        } catch ( Exception e ) {
            System.out.println( "store problem" );
            e.printStackTrace();
        }
        
    }
    
//    public String getBaseDir() {
//
//        return baseDir;
//    }
    
    public String getConfigFileName() {
        String home = System.getProperty( "user.home" );        
        
        if ( BrowseToUrl.isWindowsPlatform() ) {
            return home + "\\iToons.la";            
        } else {
            return home + "/.iToons.la";
        }
    }
        

    public String getComicsFileName() {
        String home = System.getProperty( "user.home" );        
        
        if ( BrowseToUrl.isWindowsPlatform() ) {
            return home + "\\iToons\\comics.la";            
        } else {
            return home + "/iToons/comics.la";
        }
    }

    public String getManualSelctionsFileName() {
        String home = System.getProperty( "user.home" );        
        
        if ( BrowseToUrl.isWindowsPlatform() ) {
            return home + "\\iToons\\manualSelections.la";            
        } else {
            return home + "/iToons/manualSelections.la";
        }
    }
            
    public String getBigLogoDir() {
        String home = System.getProperty( "user.home" );        
        
        if ( BrowseToUrl.isWindowsPlatform() ) {
            return home + "\\iToons\\bigLogo\\";            
        } else {
            return home + "/iToons/bigLogo/";
        }
    }
            

    private LispFormat frmt = new LispFormat();
    private String     baseDir;

}
