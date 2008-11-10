package org.openCage.stroy.tree;

import com.twmacinta.util.MD5;

import java.io.InputStream;
import java.io.IOException;

import org.openCage.util.string.Strings;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 09.11.2008
 * Time: 16:48:02
 * To change this template use File | Settings | File Templates.
 */
public class FielStream implements Fiel {
    private InputStream is;
    private String checkSum = null;


    public FielStream( InputStream is ) {
        this.is = is;
    }

    public String getChecksum() {
        if ( checkSum == null ) {
            try {
                checkSum = Strings.asHex( MD5.getHash( is ));
            } catch( IOException e ) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return checkSum;
    }
}
