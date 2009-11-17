package org.openCage.gpad;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 23, 2009
 * Time: 5:32:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class FaustString implements TextEncoderIdx<String>{

    private static Charset UTF8 = Charset.forName("utf8");
    private TextEncoderIdx<Byte> encoder = new FaustByteNum();

    public void setPad( URI uri ) {
        encoder.setPad( uri );
    }

    @Override
    public boolean isSet() {
        return encoder.isSet();
    }

    public String encode(String ch, int ix ) {
        byte[] bytes = new byte[0];
        try {
            bytes = ch.getBytes( "utf8" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        String ret = "";
        int idx = 0;
        for ( byte by : bytes ) {
            ret += encoder.encode(by,idx) + "\n";
            ++idx;
        }
        return ret;
    }

    public String decode(String lines, int ix ) {
        List<Byte> bytes = new ArrayList<Byte>();

        int idx = 0;
        for ( String line : lines.split("\n")) {
            bytes.add( encoder.decode(line,idx));
            ++idx;
        }
        byte[] byteArr = new byte[ bytes.size()];
        int i = 0;
        for ( Byte by : bytes ) {
            byteArr[i] = by;
            ++i;
        }
        try {
            return new String( byteArr, "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }
}
