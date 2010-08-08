package org.openCage.stjx;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 8, 2010
 * Time: 9:24:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Rnc {
    private String name;
    private String tag;

    Rnc( String name, String tag ) {
        this.name = name;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return name + " = " + tag + "{}";
    }
}
