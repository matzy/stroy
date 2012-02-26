/*
 * JTravelAlbum.java
 *
 * Created on November 23, 2004, 7:27 PM
 */

import java.util.*;
import java.io.*;

/**
 *
 * @author  SPfab
 */
public class JTravelAlbum {
    
    /** Creates a new instance of JTravelAlbum */
    public JTravelAlbum( String title, String dir, int picSize ) {
        
        this.title       = title;
        this.dir         = dir;
        this.pictureSize = picSize;
        
        pages = new Vector();
        
        pages.add( new Page( "../collage/munich.JPG", "Es geht los<p>Morgens um 17.00h vom kalten Muenchen ins warme Windhoek per Flugzeug", "Es geht los" ));
        pages.add( new Page( "../collage/windhoek1.JPG", "", "Windhoek" ));
        pages.add( new Page( "../sd0024/dcim/100mlt18/pict0018.jpg", "Fahrt ins Windhoek Tal." ));
        pages.add( new Page( "../sd0006/dcim/100mlt18/pict0002.jpg", "Windhoek Safarie Hotel" ));
        pages.add( new Page( "../sd0006/dcim/100mlt18/pict0012.jpg", "Nach dem Petra endlich ausgeschlafen war un ich gepoolt habe sind wir in Namibias Hauptstadt mit 250000 Einwohner aufgebrochen, oder wie wir Muenchner sagen: ins Dorf.<p>" + 
            "Die Hitze war ein bischen viel fuer den Anfang, aber ueber die Temperatur reden ist unfein. Die erste deutsche Kirche."));
        pages.add( new Page( "../sd0006/dcim/100mlt18/pict0008.jpg", "Die ersten Webervoegel" ));
        pages.add( new Page( "../sd0006/dcim/100mlt18/pict0006.jpg", "Die ersten Jakaranda" ));
        pages.add( new Page( "../sd0006/dcim/100mlt18/pict0018.jpg", "Die ersten Geckos" ));
        pages.add( new Page( "../sd0006/dcim/100mlt18/pict0013.jpg", "Die ersten Photos von uns, ok mir" ));
        pages.add( new Page( "", "Der erste deutsche Kuchen, gut" ));
        pages.add( new Page( "../sd0006/dcim/100mlt18/pict0024.jpg", "Der erste Sonnenuntergang" ));
// sundown ?
        pages.add( new Page( "karte", "Erster Tag der Fahrt", "In den Bus" ));
        pages.add( new Page( "petras koffer", "Der wollte ihn einen anderen Bus" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0037.jpg", "Die Landschaft fuer die naechsten Tage" ));
                                                           //19, 46
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0007.jpg", "" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0020.jpg", "Lodge: Mokuti" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0021.jpg", "Mokuti" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0023.jpg", "Mokuti" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0026.jpg", "Mokuti" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0048.jpg", "Mokuti: Köcherbaum" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0021.jpg", "" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0051.jpg", "" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0001.jpg", "Mokuti: Webervögel" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0011.jpg", "" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0005.jpg", "" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0007.jpg", "" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0011.jpg", "" ));
        
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0036.jpg", "Buh<p>Die Tierschau faengt an" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0037.jpg", "Strausse" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0037b.jpg", "Strausse" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0038.jpg", "Klippsliefer" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0040.jpg", "" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0010.jpg", "" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0045.jpg", "Warzenschweine<p>die den Futterwagen gehört haben" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0053.jpg", "gefunden" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0009.jpg", "Warzenschweine am Hotel" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0018.jpg", "Tausendfüssler beim Essen" ));
        
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0014.jpg", "Kudus: Guck mal Trouristen", "Wildfahrt" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0018.jpg", "" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0054.jpg", "Perlhühner" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0062.jpg", "Nashörner" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0069.jpg", "" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0075.jpg", "Giraffe" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0090.jpg", "" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0091.jpg", "" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0083.jpg", "Dikdik" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0086.jpg", "Dikdik" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0110.jpg", "meins" ));
        ((Page)pages.get( pages.size() - 1 )).addImage( "../sd0007/dcim/100mlt18/pict0112.jpg" );
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0114.jpg", "" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0115.jpg", "" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0118.jpg", "Gar nicht scheu" ));
        pages.add( new Page( "../sd0007/dcim/100mlt18/pict0120.jpg", "" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0026.jpg", "Oryx Antilopen" ));
        
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0014.jpg", "Wieder Sonneruntergang" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0015.jpg", "Wieder Sonneruntergang" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0016.jpg", "Wieder Sonneruntergang" ));

        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0024.jpg", "Es geht weiter", "Fahrt zur Etosha Pfanne" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0028.jpg", "" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0032.jpg", "" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0034.jpg", "Paviane" ));

        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0038.jpg", "??", "Lodge: mit Terrarium" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0047.jpg", "" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0039.jpg", "Eichhörn" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0003.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0004.jpg", "Star" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0040.jpg", "Meckervogel<p>Der Vogel der am häufigsten mit grossen Kaliber beschossen wird" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0043.jpg", "irgend was giftges" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0044.jpg", "so schnell das Moos drauf wächst" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0045.jpg", "Korallenbaum<p>nicht heimisch" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0048.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0001.jpg", "Tukan" ));
        pages.add( new Page( "../sd0010/dcim/100mlt18/pict0051.jpg", "" ));

        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0006.jpg", "Springböcke", "Etosha" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0007.jpg", "Dikdik" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0011.jpg", "Kudu" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0012.jpg", "Kudu" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0013.jpg", "Namutoni<p>Ritterburg in Afrika" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0014.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0015.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0016.jpg", "Wildhund?" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0017.jpg", "Kraniche<p>eins von wenigen paaren in der Etosha" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0018.jpg", "uns Springböcke" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0020.jpg", "uns Springböcke" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0023.jpg", "Oryx und Adler" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0024.jpg", "die mag uns nicht" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0025.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0026.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0027.jpg", "so ists besser" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0030.jpg", "Gnus" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0031.jpg", "Wasserloch" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0033.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0041.jpg", "kleine Diskussion" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0042.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0034.jpg", "Hyäne<p>mein Wasserloch" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0035.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0038.jpg", "argh<p>Hyäne" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0032.jpg", "Etosha" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0050.jpg", "Springböcke<p>nicht springend" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0051.jpg", "Adler" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0052.jpg", "Elephanten<p>ja, ja, ja<p>4 Uhr und es hat doch noch geklappt" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0057.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0064.jpg", "Oryx" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0065.jpg", "und Springböcke" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0070.jpg", "Etosha" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0072.jpg", "und Springböcke" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0075.jpg", "acrobatisch<p>Das dunklere Männchen passt auf während das hellere Weibchen trinkt" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0080.jpg", "zwei Methoden<p>durchgestreckt und abgeknickt" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0076.jpg", "danke" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0083.jpg", "Jetzt trinkt der Chef" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0085.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0094.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0095.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0089.jpg", "vorher nachher" ));
        ((Page)pages.get( pages.size() - 1 )).addImage( "../sd0011/dcim/100mlt18/pict0092.jpg" );
        ((Page)pages.get( pages.size() - 1 )).addImage( "../sd0011/dcim/100mlt18/pict0093.jpg" );
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0096.jpg", "Suchspiel<p>Tip: die gehört nicht in die Wüste" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0097.jpg", "nochmal Elephanten" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0102.jpg", "baden macht sauber" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0105.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0107.jpg", "baden macht auf jeden Fall Spass" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0108.jpg", "Rinos jagen macht auch Spass<p>wenn Mama beschützt" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0109.jpg", "plus Giraffe" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0111.jpg", "Sandsturm kommt" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0113.jpg", "" ));
        pages.add( new Page( "../sd0011/dcim/100mlt18/pict0115.jpg", "" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0019.jpg", "Zebras" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0022.jpg", "Hallo" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0023.jpg", "" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0024.jpg", "Etosha ist eine Salzpfanne" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0025.jpg", "" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0026.jpg", "Ein Rundblick" ));
        ((Page)pages.get( pages.size() - 1 )).addImage( "../sd0012/dcim/100mlt18/pict0027.jpg" );
        ((Page)pages.get( pages.size() - 1 )).addImage( "../sd0012/dcim/100mlt18/pict0028.jpg" );
        ((Page)pages.get( pages.size() - 1 )).addImage( "../sd0012/dcim/100mlt18/pict0029.jpg" );

        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0015.jpg", "Schlaflose Elephanten", "Halali: Nachtwasserloch" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0016.jpg", "Ein Spitzmaulnashorn<p>Einzelgänger die Bäume äsen und bevorzugt Nachts unterwegs sind<plus ein Elephant der gleich angreift>" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0007.jpg", "Elehanten und Nashörner blagen sich<p>die Elephanten kommen zurück<p>Ein Baby wird von der Mutter mit dem Fuss daran gehindert zu schnell zu trinken" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0007.jpg", "kein Leopard" ));
        // 7/80 landschaft

        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0030.jpg", "Einer Schule<>samt Uniformen", "Wir verlassen die Etosha" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0031.jpg", "Versteinerter Baum" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0032.jpg", "Der ist irgendwo in Amerika gewachsen" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0035.jpg", "und dann per Flut" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0037.jpg", "und Vulkan hier glandet" ));
        pages.add( new Page( "../sd0012/dcim/100mlt18/pict0046.jpg", "Zikade<p>klingt wie eine nicht abgeschirmte Leitung" ));
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        new JTravelAlbum( "namibia", "D:\\art\\pics\\foto\\namibia\\jta\\", 1000 ).process();
        
        
    }
    
    public void process() {

        for ( int i = 0; i < pages.size(); ++i ) {

            ((Page)pages.get(i)).print( i, dir, pictureSize, title );
        }
        
        printTitle();
        
        
        
    }
    
    private void printTitle() {
        try{
           
            FileOutputStream out = new FileOutputStream( dir + title + ".html" );

            byte bufferA[] = ("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">" +
                "<html>" +
                  "<head>" +
                    "<title>" + title + "</title>" +
                  "</head>" +
                  "<body>" +
                  "<center><h1>" + title + "</h1></center>"
                ).getBytes();
            out.write( bufferA, 0, bufferA.length );

            for ( int i = 0; i < pages.size(); ++i ) {

                Page page = (Page)pages.get(i);
                String chapter = page.getChapterTitle();
                
                if ( chapter != "" ) {
                    byte bufferC[] = ("<center><a href=jta" + i +".html>" + chapter + "</a></center>").getBytes();
                    out.write( bufferC, 0, bufferC.length );

                }
                
                
            }
            
            byte bufferE[] = (
                  "</body>" +
                  "</html>"
                ).getBytes();
            out.write( bufferE, 0, bufferE.length );

            out.close();
        } catch ( Exception e ) {
            System.out.println( "store problem: title" );
            e.printStackTrace();
        }
        
    }
    
    
    Vector pages;
    String title;
    String dir;
    int    pictureSize;
    
}
