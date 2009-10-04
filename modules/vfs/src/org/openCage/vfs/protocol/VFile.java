/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.vfs.protocol;

import java.util.Collection;

/**
 *
 * @author stephan
 */
public interface VFile {
    public String gettName();  // TODO return more specific type ?

    /**
     * return the parent VFile or null if its root 
     * @return
     */
    public VFile  getParent();

    public Collection<? extends VFile> gettChildren();
}
