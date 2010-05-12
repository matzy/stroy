package org.openCage.withResource.protocol;

import org.openCage.lang.functions.F1;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;

public interface With {

    public <T> T withInputStream( File file, F1<T, InputStream> reader );
    public <T> T withReader( File file, F1<T, Reader> reader );
    public <T> T withWriter( File file, F1<T, Writer> writer );

    public FileLineIterable getLineIteratorCloseInFinal( File file );
}
