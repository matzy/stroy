/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.withResource.error;

import java.util.logging.Logger;

/**
 *
 * @author stephan
 */
public class LogError {
    public static <T extends Throwable> T log( T exp ) {
        exp.printStackTrace();
        Logger.getAnonymousLogger().warning( exp.toString() );
        return exp;
    }
}
