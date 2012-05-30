package org.openCage.comphy;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/20/12
 * Time: 12:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class RString implements Readable {

    public final String str;

    public RString(String str) {
        this.str = str;

    }

    public String get() {
        return str;
    }

    @Override
    public String toString() {
        return str;
    }

    public static RString valueOf( String str ) {
        return new RString(str);
    }
}
