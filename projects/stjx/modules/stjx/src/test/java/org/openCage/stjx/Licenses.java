package org.openCage.stjx;

import org.openCage.io.fspath.FSPathBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 12/30/10
 * Time: 10:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class Licenses {

    public static void main(String[] args) {
        Stjx stjx = new Stjx( "Licenses" );

        stjx.struct( "Licenses" ).zeroOrMore().complex( "License");

        stjx.struct( "License" ).
                string( "name" ).
//                string( "version" ).
                zeroOrMore("aliases").string("alias").
                string( "uri" ).
                zeroOrMore( "positives" ).string( "license" ).
                zeroOrMore( "negatives" ).string( "license" );


//        stjx.struct( "Licence" ).
//                string( "name").
//                string( "uri" ).
//                enm("type").
//                string( "timestamp" ).
//                string("seqno").
//                optional().string("to").
//                zeroOrMore().complex( "DependsOn" ).
//                zeroOrMore( "DatabaseFiles").string( "File" );
//
//        stjx.enm( "type", "full", "increment", "difference" );
//
//        stjx.struct("DependsOn").
//                string( "name" ).
//                enm( "type").
//                string( "timestamp").
//                string( "seqno").
//                optional().string("to");

        stjx.generate( FSPathBuilder.getPath("/Users/stephan/tmp/licences").toString(), "org.openCage.pom4app" );

    }

}
