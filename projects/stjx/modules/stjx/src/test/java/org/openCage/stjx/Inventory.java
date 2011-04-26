package org.openCage.stjx;

import org.openCage.io.fspath.FSPathBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Sep 15, 2010
 * Time: 11:28:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class Inventory {

    public static void main(String[] args) {
        Stjx stjx = new Stjx( "Backup" );

        stjx.struct( "Backup" ).
                string( "name").
                enm("type").
                string( "timestamp" ).
                string("seqno").
                optional().string("to").
                zeroOrMore().complex( "DependsOn" ).
                zeroOrMore( "DatabaseFiles").string( "File" );

        stjx.enm( "type", "full", "increment", "difference" );

        stjx.struct("DependsOn").
                string( "name" ).
                enm( "type").
                string( "timestamp").
                string( "seqno").
                optional().string("to");
                
        stjx.generate( FSPathBuilder.getPath( "/Users/stephan/tmp/inv" ).toString(), "com.avid.qa.jxdktests.adminAPI.inventory" );

    }
}
