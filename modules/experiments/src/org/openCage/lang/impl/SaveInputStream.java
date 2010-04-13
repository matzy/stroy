package org.openCage.lang.impl;

import java.io.IOException;
import java.io.InputStream;
import org.openCage.lang.errors.Unchecked;

/**
 *
 * @author stephan
 */
public class SaveInputStream extends InputStream {

    private InputStream is;

    public SaveInputStream( InputStream is ) {
        this.is = is;
    }

    @Override
    public int read() throws IOException {
        if ( is == null ) {
            throw new Unchecked( new IllegalArgumentException());
        }
        return is.read();
    }



}
