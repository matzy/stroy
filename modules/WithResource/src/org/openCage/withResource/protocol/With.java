package org.openCage.withResource.protocol;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import org.openCage.lang.protocol.FE1;

public interface With {
	
	public <T> T withInputStream( File file, FE1<T, InputStream> reader );
    public <T> T withReader( File file, FE1<T, Reader> reader );
    public <T> T withWriter( File file, FE1<T, Writer> writer );
}
