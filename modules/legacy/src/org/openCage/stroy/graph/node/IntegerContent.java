/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.stroy.graph.node;

import java.io.File;
import org.openCage.stroy.algo.fuzzyHash.HasDistance;
import org.openCage.stroy.content.Content;

/**
 *
 * @author stephan
 */
public class IntegerContent implements Content{

    private int inti;

    public IntegerContent( int i) {
        inti = i;
    }

    public String getName() {
        return "" + inti;
    }

    public String getChecksum() {
        return "" + inti;
    }

    public HasDistance getFuzzyHash() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public File getFile() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
