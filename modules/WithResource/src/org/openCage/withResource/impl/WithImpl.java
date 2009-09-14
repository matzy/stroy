package org.openCage.withResource.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.openCage.withResource.protocol.Reader;
import org.openCage.withResource.protocol.ReaderException;
import org.openCage.withResource.protocol.Unchecked;
import org.openCage.withResource.protocol.With;

public class WithImpl implements With {

	public <T> T withOpenStream( File file, Reader<T, InputStream> reader) throws ReaderException{
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			return reader.read(is);
		} catch (FileNotFoundException e) {
			throw new ReaderException(e);			
		} finally {
			if ( is != null ) {
				try {
					is.close();
				} catch ( IOException e ) {
					// was closed
				}
			}
		}
	}

	public <T> T withOpenStreamQuiet(File file, Reader<T, InputStream> reader) {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			return reader.read(is);
		} catch (FileNotFoundException e) {
			throw new Unchecked(e);			
		} catch (ReaderException e) {
			throw new Unchecked(e); 
		} finally {
			if ( is != null ) {
				try {
					is.close();
				} catch ( IOException e ) {
					// was closed
				}
			}
		}
	}

}
