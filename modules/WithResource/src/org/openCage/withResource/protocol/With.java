package org.openCage.withResource.protocol;

import java.io.File;
import java.io.InputStream;

public interface With {
	
	public <T> T withOpenStream( File file, Reader<T, InputStream> reader ) throws ReaderException;

	public <T> T withOpenStreamQuiet( File file, Reader<T, InputStream> reader );

}
