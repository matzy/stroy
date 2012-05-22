package org.openCage.comphy;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/21/12
 * Time: 10:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class RList extends ArrayList<Readable> implements Readable {
    public String getElementKey() {
        return elementKey;
    }

    private String elementKey;

    public RList( String elementKey ) {
        this.elementKey = elementKey;
    }
}
