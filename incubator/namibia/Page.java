/*
 * Page.java
 *
 * Created on November 23, 2004, 7:25 PM
 */
import java.util.*;
import java.io.*;

/**
 *
 * @author  SPfab
 */
public class Page {
    
    /** Creates a new instance of Page */
    public Page( String image, String text ) {
        this.image = image;
        this.text  = text;
        chapter    = "";

        images = new Vector();
        images.add( image );
    }

    public Page( String image, String text, String chapter ) {        
        this.image = image;
        this.text  = text;
        this.chapter = chapter;
        
        images = new Vector();
        images.add( image );
    }


    public void addImage( String img ) {
        images.add( img );
    }
    
    String getChapterTitle() {
        return chapter;
    }
    
    public void print1( int i, String dir ) {
        try{

            FileOutputStream out = new FileOutputStream( dir + "jta" + i + ".html" );

            byte bufferA[] = ("'<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">" +
                "<html>" +
                  "<head>" +
//                    "<title>" + title + "</title>" +
                  "</head>" +
                  "<body>" +
                  "<a href=jta" + (i+1) +".html><img src=\"" + image + "\" height=400 alt=" + image + ">" +
                  "</body>" +
                  "</html>"
                ).getBytes();
            out.write( bufferA, 0, bufferA.length );

            byte bufferN[] = "\n".getBytes();                       
            out.write( bufferN, 0, bufferN.length );

            out.close();
        } catch ( Exception e ) {
            System.out.println( "store problem" );
            e.printStackTrace();
        }
        
    }
    
    public void print( int i, String dir, int picSize, String title ) {
        
        if ( images.size() > 1 ) {
            printN( i, dir, picSize, title );
            return;
        }
        try{

            FileOutputStream out = new FileOutputStream( dir + "jta" + i + ".html" );

            byte bufferA[] = ("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n" +
                "<html>\n" +
                  "<head>\n" +
//                    "<title>" + title + "</title>" +
                  "</head>\n" +                  
                  "<body>\n" +
                    "<table>\n" +
                      "<tr>\n" +
                        "<td>\n" +
                          "<a href=jta" + (i+1) +".html><img src=\"" + image + "\" width=" + picSize + " alt=" + image + ">" +
                        "</td>\n" +
                        "<td>\n" +
                          "<h2>" + text + "</h2>\n" +
                        "</td>\n" +
                      "</tr>\n" +
                      "<tr>\n" +
                        "<td>\n" +
                          "<a href=" + image + ">orignal</a>" +
                          "<a href=" + title + ".html><img src=\"top.JPG\" alt=anfang></a>" +
                          "<a href=jta" + (i-1) + ".html><img src=\"left.JPG\" alt=zurück></a>" +
                        "</td>\n" +
                      "</tr>\n" +
                    "</table>\n" +
                  "</body>" +
                  "</html>"
                ).getBytes();
            out.write( bufferA, 0, bufferA.length );

            byte bufferN[] = "\n".getBytes();                       
            out.write( bufferN, 0, bufferN.length );

            out.close();
        } catch ( Exception e ) {
            System.out.println( "store problem for page " + i  );
            e.printStackTrace();
        }
        
    }
    
    public void printN( int i, String dir, int picSize, String title ) {
        try{

            FileOutputStream out = new FileOutputStream( dir + "jta" + i + ".html" );

            byte bufferA[] = ("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n" +
                "<html>\n" +
                  "<head>\n" +
//                    "<title>" + title + "</title>" +
                  "</head>\n" +                  
                  "<body>\n" +
                    "<table>\n" +
                      "<tr>\n" +
                        "<td>\n" ).getBytes();
            out.write( bufferA, 0, bufferA.length );
            
            for ( int m = 0; m < images.size(); ++m ) {
                byte bufferI[] = ( "<a href=jta" + (i+1) + ".html><img src=\"" + images.get(m) + "\" width=" + (picSize / images.size()) + " alt=" + images.get(m) + 
                                   ">" ).getBytes();
                out.write( bufferI, 0, bufferI.length );                
            }
            
            byte bufferE[] = ( 
                        "</td>\n" +
                        "<td>\n" +
                          "<h2>" + text + "</h2>\n" +
                        "</td>\n" +
                      "</tr>\n" +
                      "<tr>\n" +
                        "<td>\n" +
                          "<a href=" + image + ">orignal</a>" +
                          "<a href=" + title + ".html><img src=\"top.JPG\" alt=anfang></a>" +
                          "<a href=jta" + (i-1) + ".html><img src=\"left.JPG\" alt=zurück></a>" +
                        "</td>\n" +
                      "</tr>\n" +
                    "</table>\n" +
                  "</body>" +
                  "</html>"
                ).getBytes();
            out.write( bufferE, 0, bufferE.length );

            byte bufferN[] = "\n".getBytes();                       
            out.write( bufferN, 0, bufferN.length );

            out.close();
        } catch ( Exception e ) {
            System.out.println( "store problem for page " + i  );
            e.printStackTrace();
        }
        
    }
    
    String image;
    Vector images;
    String text;
    String chapter;
    
}
