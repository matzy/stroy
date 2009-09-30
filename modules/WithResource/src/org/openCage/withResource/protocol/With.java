package org.openCage.withResource.protocol;

import java.io.File;
import java.io.InputStream;
import org.openCage.lang.protocol.FE1;

public interface With {
	
	public <T> T withInputStream( File file, FE1<T, InputStream> reader );

}
