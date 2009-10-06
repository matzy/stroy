/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.withResource.protocol;

/**
 *
 * @author stephan
 */
public interface FileLineIterable extends Iterable<String> {
    public void    close();
    public boolean isClosed();
}
