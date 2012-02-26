/*
 * Comic.java
 *
 * Created on September 15, 2004, 4:26 PM
 */

import java.util.*;

import openCage.persistence.*;

/**
 *
 * @author  SPfab
 */
public class Comic {
    
    /** Creates a new instance of Comic */
    public Comic( String name, String url ) {
        this.name = name;
        urls = new Vector();
        urls.add( url );
        artists = new Vector();
        rating    = 2;
        frequency = 0;
        primaryUrl = 0;
        timeAdded = new Date();
        languages = new Vector();
    }
    
    public static List getPropEditList() {

        List ret = null;
        
        String types = "(";
        types += "(\"Name\" 1 Text \"ggg\" )";
//        types += "(\"Url\" 2 Url \"\"  )";
        types += "(\"Artist\" 2 Text \"\" )";
        types += "(\"Rating\" 1 StringCombo ( \"--\" \"-\" \"unrated\" \"*\" \"**\" \"***\" \"****\"  \"*****\") )";
//        types += "(\"Life\" 1 StringCombo ( \"alive\" \"hiatus\" \"archived\" \"gone\") )";
//        types += "(\"Frequency\" 1 ComicFrequency ( \"daily\" \"weekly\") \"rare\" )";
//        types += "(\"Language\" 2 StringCombo ( \"English\" \"German\" \"Japanese\" \"Italian\" \"French\") )";
//        types += "(\"Politic\" 1 StringCombo ( \"unrated\" \"*\" \"**\" \"***\") )";
//        types += "(\"Adult\" 1 StringCombo ( \"unrated\" \"*\" \"**\" \"***\") )";
//        types += "(\"Funny\" 1 StringCombo ( \"unrated\" \"*\" \"**\" \"***\") )";
//        types += "(\"foo\" 1 Text \"  \" )";
        types += ")";
        
        try  {      
            LispFormat frmt = new LispFormat();
            ret = (java.util.List)frmt.parseObject( types );
            System.out.println( ret );
        } catch ( java.text.ParseException e ) {
            System.out.println( "exception");
        }               
        
        return ret;

    }
    
    public String toString() {

        Intermediate itm = new Intermediate();
        String ret = "";
        
        try {
            ret = "(Comic " +        
                     "(Name \"" + name +  "\")" +
                     "(ID \"" + id + "\")" +
                     "(Rating " + rating + ")" +
                     "(Frequency " + frequency + ")" +
                     "(FrequencyDetails \"" + frequencyDetails + "\")" +
                     "(Alive " + alive + ")" +
                     "(Politic " + politic + ")" +
                     "(Language \"" + language + "\")" +
                     "(BigLogo \"" + bigLogo + "\")" +
                     "(Comment \"" + comment + "\")" +
                     "(Added " + timeAdded.getTime() + ")"
                     ;
            
            
            itm.add( "Name", name );
            itm.add( "Rating", new Integer(rating ));
            itm.add( "Frequency", new Integer(frequency ));
            itm.add( "FrequencyDetails", frequencyDetails );
            itm.add( "Alive", new Integer(alive ));
            itm.add( "Politic", new Integer(politic ));
            itm.add( "Language", language );
            itm.add( "BigLogo", bigLogo );
            itm.add( "Added", new Long(timeAdded.getTime() ));
        } catch ( NullPointerException exp ) {
            int i = 5;
        }
        
        for ( int i = 0; i < artists.size(); ++i ) {
            ret = ret + "(Artist \"" + (String)artists.get(i) + "\")";
            itm.add( "Artist", (String)artists.get(i) );
        }
            
        for ( int i = 0; i < urls.size(); ++i ) {
            ret = ret + "(Url \"" + (String)urls.get(i) + "\")";
            itm.add( "Url", (String)urls.get(i) );
        }
        
        ret += ")";
                
        openCage.itoons.Comic cc = new  openCage.itoons.Comic( itm );
        
        return ret;
    }
    
    public Comic( List lst ) {
        Symbol sName    = Symbol.get( "Name");
        Symbol sUrl     = Symbol.get( "Url");
        Symbol sRating  = Symbol.get( "Rating");
        Symbol sArtist  = Symbol.get( "Artist");
        Symbol sFrequency  = Symbol.get( "Frequency");
        Symbol sLanguage  = Symbol.get( "Language");
        Symbol sPolitic  = Symbol.get( "Politic");
        Symbol sBigLogo  = Symbol.get( "BigLogo");
        Symbol sAlive  = Symbol.get( "Alive");
        Symbol sComment  = Symbol.get( "Comment");
        Symbol sID       = Symbol.get( "ID");
        Symbol sAdded    = Symbol.get( "Added");
        
        artists   = new Vector();
        urls      = new Vector();
        rating    = 2;
        primaryUrl = 0;
        frequency = 0;

        for ( int i = 1; i < lst.size(); i++) {
            List tag = (List)lst.get(i);
            
            if ( sName == tag.get(0)) {
                name = (String)tag.get(1);
            } else if ( sUrl == tag.get(0)){
                urls.add( (String)tag.get(1));
            } else if ( sRating == tag.get(0)){
                rating = ((Integer)tag.get(1)).intValue() ;
            } else if ( sPolitic == tag.get(0)){
                politic = ((Integer)tag.get(1)).intValue() ;
            } else if ( sFrequency == tag.get(0)){
                frequency = ((Integer)tag.get(1)).intValue() ;
            } else if ( sAlive == tag.get(0)){
                alive = ((Integer)tag.get(1)).intValue() ;
            } else if ( sLanguage == tag.get(0)){
                language = (String)tag.get(1);
            } else if ( sBigLogo == tag.get(0)){
                bigLogo = (String)tag.get(1);
            } else if ( sArtist == tag.get(0)){
                artists.add( tag.get(1));
            } else if ( sID == tag.get(0)){
                id = new GUID( (String)tag.get(1) );
            } else if ( sID == tag.get(0)){
                timeAdded = new Date( ((Integer)tag.get(1)).longValue() );
            } else if ( sComment == tag.get(0)){
                comment = (String)tag.get(1);
            }
        }        
        
        if ( id == null ) {
            id = new GUID();
        }
        if ( timeAdded == null ) {
            timeAdded = new Date();
        }
        
        languages = new Vector();
        languages.add( language );
        
    }
    
    public Comic( int la, List lst ) {
        Symbol sName    = Symbol.get( "Name");
        Symbol sUrl     = Symbol.get( "Url");
        Symbol sRating  = Symbol.get( "Quality");
        Symbol sArtist  = Symbol.get( "Artist");
        Symbol sFrequency  = Symbol.get( "Frequency");
        Symbol sLanguage  = Symbol.get( "Lang");
        Symbol sBigLogo  = Symbol.get( "Img");
        Symbol sComment  = Symbol.get( "Comment");
        
        artists   = new Vector();
        urls      = new Vector();
        rating    = 2;
        primaryUrl = 0;
        frequency = 0;
        timeAdded = new Date();

        for ( int i = 1; i < lst.size(); i++) {
            List tag = (List)lst.get(i);
            
            if ( sName == tag.get(0)) {
                name = (String)tag.get(1);
            } else if ( sUrl == tag.get(0)){
                urls.add( (String)tag.get(1));
            } else if ( sRating == tag.get(0)){
                rating = ((Integer)tag.get(1)).intValue() + 2;
                assert  0 <= rating && rating < 8;
            } else if ( sFrequency == tag.get(0)){
                int oldFrequency = ((Integer)tag.get(1)).intValue() ;                
                if ( oldFrequency < 3 ) {
                    frequency = 0;
                    alive     = oldFrequency; 
                } else {
                    frequency = oldFrequency - 3;
                    alive     = 3;
                }
            } else if ( sLanguage == tag.get(0)){
                language = (String)tag.get(1);
            } else if ( sBigLogo == tag.get(0)){
                bigLogo = (String)tag.get(1);
            } else if ( sComment == tag.get(0)){
                comment = (String)tag.get(1);
            } else if ( sArtist == tag.get(0)){
                artists.add( tag.get(1));
            }
        }        
        
        assert  0 <= rating && rating < 8;
        
        languages = new Vector();
        languages.add( language );

    }
    
    public String getName() {
        return name;
    }

    public void setName( String n ) {
        name = n;
    }

    public String getBigLogo() {
        return bigLogo;
    }
    
    public Vector getUrls() {
        return urls;
    }
    
    public String getUrl() {
        return getUrl( primaryUrl );
    }
    
    public String getUrl( int idx ) {
        if ( urls.size() <= idx ) {
            return "404";
        }
        
        return (String)urls.get(idx);
    }
    
    public void setUrl( int idx, String u ) {
        
        while ( urls.size() <= idx ) {
            urls.add( "" );
        }
        
        urls.set(idx, u);
    }
    
    
    public String getArtistsText() {
        String ret = "";
        
        for ( int i = 0; i < artists.size(); ++i ) {
            if ( i > 0 ) {
                ret += "; ";
            }
            ret += (String)artists.get(i);
        }
        
        return ret;    
    }
    
    public Vector getArtists() {
        return artists;
    }
    
    public String getArtist( int idx ) {
        if ( artists.size() <= idx ) {
            return "";
        }
        
        return (String)artists.get(idx);
    }

    public void setArtist( int idx, String a ) {
        
        while ( artists.size() <= idx ) {
            artists.add( "" );
        }
        
        artists.set(idx, a);
    }
    
    public void setArtists( Vector a ) {
        artists = a;
    }
    
    public int getRating() {
        return rating;
    }
    
    public String getRatingText() {

        if ( rating < 0 ) {
            int foo = 5;
        }
        return ratingTexts[rating];
    }
    
    public void setRating( int r ) {
        rating = r;
    }
    

    public String getComment() {
        return comment;
    }
    
    public void setComment( String c ) {
        comment = c;
    }
    
    public int getPrimaryUrlIdx() {
        return primaryUrl;
    }
    
    public void setPrimaryUrl( int idx ) {
        primaryUrl = idx;
    }
    
    public int getFrequency() {
        return frequency;
    }
    
    public void setFrequency( int f ) {
        frequency = f;
    }
    
    public int getAlive() {
        return alive;
    }
    
    public void setAlive( int a ) {
        alive = a;
    }
    
    public GUID getID() {
        return id;
    }
    
    public String getFrequencyDetails() {
        return frequencyDetails;
    }
    
    public void setFrequencyDetails( String fd ) {
        frequencyDetails = fd;
    }

    public Vector getLanguages() {
        return languages;
    }
    
    public static String[] getFrequencyTexts() {
        return frequencyTexts;
    }
    
    public static String[] getAliveTexts() {
        return aliveTexts;
    }
    
    public static String[] getRatingTexts(){
        return ratingTexts;
    }
    
    public static String[] getLanguageTexts() {
        return languageTexts;
    }
    
    public static String[] getLevelTexts() {
        return levelTexts;
    }


    private String    name;
    private Vector    urls;    
    private int       primaryUrl;
    private int       rating; // 0 - 7
    private Date      timeAdded;
    private Vector    artists;
    private String    language;
    private Vector    languages;
    private int       alive;
    private int       frequency;
    private String    frequencyDetails;
    private int       politic;
    private String    bigLogo;
    private String    comment;
    private GUID      id;
    
    
    private static String    ratingTexts[] = { "- -",
                                               "-",
                                               "unrated",
                                               "*",
                                               "* *",
                                               "* * *",
                                               "* * * *",
                                               "* * * * *" };

    private static String    levelTexts[] = {  "unrated",
                                               "*",
                                               "* *",
                                               "* * *",
                                               "* * * *",
                                               "* * * * *" };

    private static String    frequencyTexts[] = { "rare",
                                                  "weekly",
                                                  "daily" };

    private static String    aliveTexts[] = { "gone",                                          
                                              "archived",
                                              "hiatus", 
                                              "alive" };
                                              
    private static String    languageTexts[] = { "English",
                                                 "German",
                                                 "Japanese",
                                                 "Italian",
                                                };
                                                
                                              
}
