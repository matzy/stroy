/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.lang.impl;

import java.io.File;

/**
 *
 * @author stephan
 */
public interface FilePathBuilder {
    public FilePath fromString( String path );
    public FilePath fromFile( File file );
}
