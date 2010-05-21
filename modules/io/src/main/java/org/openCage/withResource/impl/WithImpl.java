package org.openCage.withResource.impl;

import java.io.*;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openCage.lang.functions.F1;
import org.openCage.lang.errors.Unchecked;

public class WithImpl { //implements With {

    public <T> T withInputStream(File file, F1<T, InputStream> reader) {
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            return reader.call(is);
        } catch (Exception e) {
            throw new Unchecked(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // was closed
                }
            }
        }
    }

    public <T> T withInputStream( URI file, F1<T, InputStream> reader) {
        InputStream is = null;
        try {

            is = new BufferedInputStream( file.toURL().openStream());
            return reader.call(is);
        } catch (Exception e) {
            throw new Unchecked(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // was closed
                }
            }
        }
    }

    public <T> T withReader(File file, F1<T, Reader> reader) {
        Reader is = null;
        try {
            is = new FileReader( file );
            return reader.call(is);
        } catch (Exception e) {
            throw new Unchecked(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // was closed
                }
            }
        }
    }

    public <T> T withWriter(File file, F1<T, Writer> writer) {
        Writer is = null;
        try {
            is = new FileWriter( file );
            return writer.call(is);
        } catch (Exception e) {
            throw new Unchecked(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // was closed
                }
            }
        }
    }

//    public FileLineIterable getLineIteratorCloseInFinal(File file) {
//        return new IterableFile( file );
//    }

    public void close( InputStream is ) {
        if ( is != null ) {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(WithImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            is = null;
        }
    }



}
