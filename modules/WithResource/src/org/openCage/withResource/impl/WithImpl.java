package org.openCage.withResource.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import java.io.Reader;
import java.io.Writer;
import org.openCage.lang.protocol.F1;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.protocol.FE1;
import org.openCage.withResource.protocol.With;

public class WithImpl implements With {

    public <T> T withInputStream(File file, FE1<T, InputStream> reader) {
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

    public <T> T withReader(File file, FE1<T, Reader> reader) {
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

    public <T> T withWriter(File file, FE1<T, Writer> writer) {
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

}
