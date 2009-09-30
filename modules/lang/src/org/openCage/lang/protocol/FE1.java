/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.lang.protocol;

/**
 *
 * @author stephan
 */
public interface FE1<R,A> {
    public R call( A a) throws Exception;
}
