/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.lang.impl;

import java.io.File;
import java.util.Collection;

/**
 *
 * @author stephan
 */
public interface FilePath {
    public String                         getAbsolutePath();
    public File                           getFile();

    public Collection<? extends FilePath> getChildren();
    public FilePath                       getParent();
    public FilePath                       buildChild( String name );
}
