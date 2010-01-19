package org.openCage.webgen.protocol;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jan 19, 2010
 * Time: 9:34:31 AM
 * To change this template use File | Settings | File Templates.
 */
public interface WebGen {
    public String ancor( String name );

//    public static String ancor( int num ) {
//        return ancor( "" + num );
//    }

    public String link( String name, String text );

    public String link( String page, String name, String text );

//    public static String link( int num, String text ) {
//        return link( "" + num, text);
//    }


    public String normalize( String ref );

    public String externalLink(String ext, String text);

    public String title( int level, String text);
}
