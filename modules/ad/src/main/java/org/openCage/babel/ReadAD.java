package org.openCage.babel;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 15, 2010
 * Time: 9:42:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReadAD {



    public static void main(String[] args) {

        Map<String,String> props = new HashMap<String, String>();
        Map<String,String> propsen = new HashMap<String, String>();

        for ( File adFile : new File("/Volumes/TA-TraditionalChinese-zh_TW/").listFiles() ) {

            String xmlFile = adFile.getAbsolutePath();

            if ( !xmlFile.endsWith( ".ad" )) {
                continue;
            }

            System.out.println("file: " + xmlFile );

            QName returnType = XPathConstants.STRING;
            String expression = "Proj/File/TextItem[200000]/TranslationSet/base";
            String expression2 = "Proj/File/TextItem[2]/TranslationSet/tran";

            Document xmlDocument;
            XPath xPath;


            try {
                xmlDocument = DocumentBuilderFactory.
                        newInstance().newDocumentBuilder().
                        parse(xmlFile);
                xPath =  XPathFactory.newInstance().
                        newXPath();

                String key = "foo";
                String valEn = "";
                int idx = 1;
                int fdx = 1;

                while( true ) {
                    XPathExpression xPathExpression = xPath.compile( "Proj[" + fdx + "]/File/TextItem[" + idx + "]/TranslationSet/base");

                    key = clearKey( (String) xPathExpression.evaluate(xmlDocument, returnType));
                    valEn = nativeToAscii( (String) xPathExpression.evaluate(xmlDocument, returnType));

                    XPathExpression xPathExpression2 = xPath.compile( "Proj[" + fdx + "]/File/TextItem[" + idx + "]/TranslationSet/tran");

                    String val = nativeToAscii((String) xPathExpression2.evaluate(xmlDocument, returnType));

                    if ( !key.isEmpty() ) {
                    //System.out.println( key + " = " + val );

                        while( props.containsKey( key )) {
                            if ( props.get( key ).equals(val)) {
                                break;
                            }

                            key += "-a";
                        }
                        if ( !props.containsKey( key ) ) {
                            props.put( key, val );
                            propsen.put( key, valEn );
                        }


//                        if ( props.containsKey( key )) {
//                            if ( !props.get( key ).equals(val)) {
//                                while ( props.containsKey( key ) ) {
//                                    key = key + "-a";
//                                }
//                                props.put( key, val );
//
////                                System.out.println( "old val: " + props.get(key) );
////                                System.exit(1);
//                            }
//                        } else {
//                            props.put( key, val );
//                        }


                        idx++;
                    } else {
                        if ( idx > 1 ) {
                            fdx++;
                            idx = 1;
                        } else {
                            break;
                        }
                    }
                }

                //System.out.println("\n\n\n\n\n\n");


//            {
//                XPathExpression xPathExpression =
//                        xPath.compile(expression);
//
//                Object key = xPathExpression.evaluate
//                        (xmlDocument, returnType);
//
//                System.out.println(key);
//            }
//
//            {
//                XPathExpression xPathExpression =
//                        xPath.compile(expression2);
//                Object oo2 = xPathExpression.evaluate
//                        (xmlDocument, returnType);
//
//                System.out.println(oo2);
//            }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (SAXException ex) {
                ex.printStackTrace();
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            } catch (XPathExpressionException ex) {
                ex.printStackTrace();
            }

        }

        System.out.println("\n\n\n");

        List<String> keys = new ArrayList<String>( props.keySet());

        Collections.sort( keys );

//        for ( String kk : keys ) {
//            System.out.println( kk + "=" + props.get(kk));
//        }

        BufferedWriter out = null;
        BufferedWriter outen = null;
        try {
            out = new BufferedWriter(new FileWriter("/Users/stephan/Documents/prs/stroy-10/modules/ad/src/main/resources/org/openCage/babel/ap_ch_TW.properties"));
            outen = new BufferedWriter(new FileWriter("/Users/stephan/Documents/prs/stroy-10/modules/ad/src/main/resources/org/openCage/babel/ap_en.properties"));
            for ( String kk : keys ) {
                out.write(  kk + "=" + props.get(kk));
                out.newLine();

                outen.write(  kk + "=" + propsen.get(kk));
                outen.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                out.close();
                outen.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }


//        out.write("aString");
//			out.newLine();
//			out.write("this is a");
//			out.newLine();
//			out.write("ttest");
//			out.close();
		}

    }

    public static String clearKey( String key ) {
        String ret = nativeToAscii( key );
        boolean toUpper = false;

        StringBuffer buffer = new StringBuffer( ret.length() );
        for (int i = 0; i < ret.length(); i++) {
            char c = ret.charAt(i);
            if ( 'a' <= c && c <= 'z' ) {
                if ( toUpper ) {
                    toUpper = false;
                    buffer.append( ("" + c).toUpperCase());
                } else {
                    buffer.append(c);
                }
            } else if ( 'A' <= c && c <= 'Z' ) {
                toUpper = false;
                buffer.append(c);                
            } else if ( c == ' ' ) {
                // make camelcase
                toUpper = true;
            }
            else {
//                buffer.append("\\u");
//                String hex = Integer.toHexString(c);
//                for (int j = hex.length(); j < 4; j++ ) {
//                    buffer.append( '0' );
//                }
//                buffer.append( hex );
            }
        }
        return buffer.toString();

    }

    public static String removeSpaces(String s) {
        StringTokenizer st = new StringTokenizer(s," ",false);
        String t="";
        while (st.hasMoreElements()) t += st.nextElement();
        return t;
    }

    /**
	 * Translates the given String into ASCII code.
	 *
	 * @param input the input which contains native characters like umlauts etc
	 * @return the input in which native characters are replaced through ASCII code
	 */
	public static String nativeToAscii( String input ) {
		if (input == null) {
			return null;
		}
		StringBuffer buffer = new StringBuffer( input.length() + 60 );
		for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c <= 0x7E) {
                buffer.append(c);
            }
            else {
            	buffer.append("\\u");
            	String hex = Integer.toHexString(c);
            	for (int j = hex.length(); j < 4; j++ ) {
            		buffer.append( '0' );
            	}
            	buffer.append( hex );
            }
        }
		return buffer.toString();
	}
}


