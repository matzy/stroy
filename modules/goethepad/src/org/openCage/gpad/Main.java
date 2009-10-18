package org.openCage.gpad;

import org.openCage.withResource.impl.WithImpl;
import org.openCage.withResource.protocol.FileLineIterable;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 16, 2009
 * Time: 9:59:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {




    public static void main(String[] args) {

        //System.out.println( new StringToStory().encode( "openCage@gmail.com ttMo\\/es!" ));

//        FaustNum faust = new FaustNum();
//
//        System.out.println( faust.encode('s'));
//        System.out.println( faust.encode('c'));
//        System.out.println( faust.encode('h'));
//        System.out.println( faust.encode('o'));
//        System.out.println( faust.encode('k'));
//        System.out.println( faust.encode('o'));
//        System.out.println( faust.encode('l'));
//        System.out.println( faust.encode('a'));
//        System.out.println( faust.encode('d'));
//        System.out.println( faust.encode('e'));

        URI pad = null;
        try {
            String path = "file://" + Main.class.getResource("Main.class").getPath();
            pad = new URI( path );
            TextEncoder<String> tts = new TextToStory( pad );
            String enc = tts.encode("schocolade\nist\ngut");
            System.out.println( enc );

            System.out.println( tts.decode(enc));

        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
