package org.openCage.babel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 16, 2010
 * Time: 1:15:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddProp {

    public static void main(String[] args) {

        List<String> locs = Arrays.asList( "ar", "cs", "de", "es", "fi", "fr", "hu", "gr", "in", "it", "ja", "ko", "nl", "no",
                "pt_BR", "pt_PT", "ru", "sv", "tr", "zh_CN", "zh_TW");


        Properties orig = getProps("/Users/stephan/Documents/prs/openCage-libs/modules/babel/src/main/resources/org/openCage/babel/menu_" + "en" + ".properties" );

        for ( String key : orig.stringPropertyNames() ) {

            for ( String loc : locs ) {

                System.out.println("looking for " + loc + "   " + key );

                String toFile = "/Users/stephan/Documents/prs/openCage-libs/modules/babel/src/main/resources/org/openCage/babel/menu_" + loc + ".properties";
                Properties base = getProps( toFile );
//                Properties other = getProps( "/Users/stephan/Documents/prs/stroy-10/modules/ad/src/main/resources/org/openCage/babel/ur_" + loc + ".properties" );
                Properties other = getProps( "/Users/stephan/Documents/prs/stroy-10/modules/ad/src/main/resources/org/openCage/babel/unspecifc_" + loc + ".properties" );

                if ( other == null ) {
                    continue;
                }

                add( base, other, key);

                FileOutputStream os = null;
                try {
                    os = new FileOutputStream( toFile );
                    base.store( os, "added");
                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } finally {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }

            }
        }


    }

    public static Properties getProps( String file ) {
        FileInputStream is = null;
        Properties properties = new Properties();
        try {
            is = new FileInputStream( file );
            properties.load( is );
        } catch (IOException e) {
            return null;
        } finally {
            try {
                if ( is != null ) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return properties;

    }

    public static void add( Properties to, Properties from, String key ) {

        if ( from.get( key ) == null ) {
            System.out.println("    not in from");
            return;
        }

        if ( to.get( key ) != null ) {
            System.out.println("    exists");
            return;
        }

        System.out.println("    setting");
        to.put( key, from.get( key ));

    }

}
