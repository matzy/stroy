package org.openCage.webserv;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 4/1/12
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class XmlBuilder {
    
    private String name;
    private List<XmlBuilder> kids = new ArrayList<XmlBuilder>();
    private String txt = "";
    private List<Pair<String, String>> attis = new ArrayList<Pair<String, String>>();

    public static XmlBuilder tag( String name, XmlBuilder ... kids ) {
        XmlBuilder xmlb = new XmlBuilder();
        xmlb.name = name;
        xmlb.kids.addAll( Arrays.asList( kids ));

        return xmlb;
    }

    public static XmlBuilder tag( String name, String txt ) {
        XmlBuilder xmlb = new XmlBuilder();
        xmlb.name = name;
        xmlb.kids = new ArrayList<XmlBuilder>();
        xmlb.txt = txt;

        return xmlb;
    }

    public static XmlBuilder tagAttribute( String name, String txt ) {
        XmlBuilder xmlb = new XmlBuilder();
        xmlb.name = name;
        xmlb.kids = new ArrayList<XmlBuilder>();
        xmlb.txt = txt;

        return xmlb;
    }

    public static XmlBuilder html( XmlBuilder ... kids ) {
        return tag( "html", kids );
    }

    public static XmlBuilder body( XmlBuilder ... kids ) {
        return tag( "body", kids );
    }


    public static XmlBuilder head( XmlBuilder ... kids ) {
        return tag( "head", kids );
    }

    public static XmlBuilder title( String txt ) {
        return tag( "title", txt );
    }

    public static XmlBuilder h1( String txt ) {
        return tag( "h1", txt );
    }

    public static XmlBuilder href( String url, String txt ) {
        XmlBuilder xmlb = tag( "a", txt );
        xmlb.attis.add( new Pair<String, String>("href", url));
        return xmlb;
    }

    public static XmlBuilder ulist( String ...  items) {
        return ulist( Arrays.asList( items ));
    }

    public static XmlBuilder ulist( F1<XmlBuilder, String> trans  , String ...  items)  {
        return ulist( Arrays.asList( items ), trans );
    }

    public static XmlBuilder ulist( List<String> items) {
        XmlBuilder lst = tag( "ul" );

        for ( String item : items ) {
            lst.child( tag( "li", item));
        }

        return lst;
    }

    public static XmlBuilder ulist( List<String> items, F1<XmlBuilder,String> trans ) {
        XmlBuilder lst = tag( "ul" );

        for ( String item : items ) {
            lst.child( tag( "li", trans.call(item)));
        }

        return lst;
    }

    private void child(XmlBuilder li) {
        kids.add( li );
    }


    public String toString() {
        String ret = "<" + name ;
        
        if ( attis.isEmpty() ) {
            ret += ">" + txt;
        } else {
            for ( Pair<String,String> atti : attis ) {
                ret += " " + atti.fst + "=\"" + atti.snd + "\"";
            }
            ret += ">" + txt;
        }
        
        
        
        for ( XmlBuilder kid : kids ) {
            ret += kid;
        }
        
        ret += "</" + name + ">";

        return ret;
    }

    public void tt() {
        tag("html",
                tag("head"));
    }
}
