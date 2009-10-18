package org.openCage.gpad;

import org.openCage.lang.protocol.FE1;
import org.openCage.withResource.impl.WithImpl;

import java.io.File;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 17, 2009
 * Time: 9:49:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringToStory implements TextEncoder<String>{

    private FaustNum faust = new FaustNum();

    public StringToStory() {
    }

    @Override
    public String encode( final String str ) {
        return new WithImpl().withInputStream( new File( getClass().getResource("FaustNum.class").getPath()),
                new FE1<String, InputStream>() {
                    @Override
                    public String call(InputStream inputStream) throws Exception {
                        String ret = "";
                        for ( int i = 0; i < str.length(); ++i ) {
                            byte pad = (byte)(inputStream.read() % 256);
                            byte code = xor( (byte)str.charAt(i), pad );
                            char ch = (char)(code + 128);
                            System.out.println("" + ch );
                            ret += faust.encode((char)ch) + "\n";
                        }
                        return ret;
                    }
                });
    }

    @Override
    public String decode(String line) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static byte xor( byte a, byte b ) {
        return (byte)((int)a ^ (int)b);
    }

}
