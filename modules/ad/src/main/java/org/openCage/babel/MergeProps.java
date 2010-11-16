package org.openCage.babel;

import java.io.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 16, 2010
 * Time: 10:25:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class MergeProps {

    public static void main(String[] args) {

//        ResourceBundle bundle = getSingleLocaleBundle( "org.openCage.babel.u2", new Locale("ar"));
//
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter( "/Users/stephan/Documents/prs/stroy-10/modules/ad/src/main/resources/org/openCage/babel/ut_ar.properties" );
//
//            for ( String key : bundle.keySet()) {
//
//                writer.write( key + "=" + bundle.getString( key) + "\n");
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } finally {
//            try {
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
//        }
//   new Locale("ar"),
//                new Locale("cs"),
//                new Locale("da"),
//                new Locale("de"),
//                new Locale("es"),
//                new Locale("fi"),
//                new Locale("fr"),
//                new Locale("gr"),
//                new Locale("it"),
//                new Locale("ja"),
//                new Locale("ko"),
//                new Locale("nl"),
//                new Locale("no"),
//                new Locale("pt_PT"),
//                new Locale("pt_BR"),
//                new Locale("ru"),
//                new Locale("zh_CN"),
//                new Locale("zh_TW");
//
        for ( String loc : Arrays.asList( "ar", "cs", "de", "es", "fi", "fr", "hu", "gr", "in", "it", "ja", "ko", "nl", "no",
                                          "pt_BR", "pt_PT", "ru", "tr", "zh_CN", "zh_TW")) {

            io( new File( "/Users/stephan/Downloads/rtext_1/Common/i18n/org/fife"),
                "/Users/stephan/Documents/prs/stroy-10/modules/ad/src/main/resources/org/openCage/babel/ur",
                loc );
        }


    }

    private static void append( Properties to, List<String> files, String loc ) {
        for ( String file : files ) {
            append( to, file + "_" + loc + ".properties");
        }
    }


    private static void append( Properties to, String from ) {
        FileInputStream is = null;
        Properties properties2 = new Properties();
        try {
            is = new FileInputStream(from);
            properties2.load( is );
        } catch (IOException e) {
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        for ( String key : properties2.stringPropertyNames() ) {
            to.put( key, properties2.get( key ));
        }

    }

    private static void append( Properties to, File base, String loc ) {
        for ( File child : base.listFiles() ) {

            System.out.println( child.getAbsolutePath() );

            if ( child.isDirectory() ) {
                append( to, child, loc );
            } else {
                String abs = child.getAbsolutePath();

                if ( abs.endsWith( "_" + loc + ".properties")) {
                    append( to, abs );
                }
            }
        }
    }

    private static void io( File base, String target, String loc ) {
        Properties properties = new Properties();

        append( properties, base, loc );

        FileOutputStream os = null;
        try {
            os = new FileOutputStream( target + "_" + loc + ".properties" );
            properties.store( os, "merged");
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
