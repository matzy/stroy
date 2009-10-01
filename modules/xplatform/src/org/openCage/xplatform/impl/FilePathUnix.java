/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.xplatform.impl;

import org.openCage.xplatform.protocol.FilePath;

/**
 *
 * @author stephan
 */
public class FilePathUnix implements FilePath {

    String path;

    public String getAbsolutePath() {
        return path;
    }

}
