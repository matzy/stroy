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
    private Key elementKey;

    public RList( Key elementKey ) {
        this.elementKey = elementKey;
    }

    public Key getElementKey() {
        return elementKey;
    }

    @Override
    public String toString() {
        return "rlist " + elementKey;
    }
}
