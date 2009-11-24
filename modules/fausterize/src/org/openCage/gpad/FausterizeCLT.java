package org.openCage.gpad;

import org.openCage.withResource.impl.WithImpl;
import org.openCage.withResource.protocol.FileLineIterable;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 18, 2009
 * Time: 11:19:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class FausterizeCLT {

    public static void main(String[] args) {

        try {
            TextEncoder<String> tts = new TextToStory( new URI(args[1]) );

            String text = "";
            FileLineIterable it =  new WithImpl().getLineIteratorCloseInFinal( new File(args[2]));
            try {
                for ( String str : it ) {
                    text += str + "\n";
                }
            } finally {
                it.close();
            }


            if ( args[0].startsWith("e")) {
                System.out.println( tts.encode(text));
            } else {
                System.out.println( tts.decode(text));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
