package org.openCage.withResource.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
}
