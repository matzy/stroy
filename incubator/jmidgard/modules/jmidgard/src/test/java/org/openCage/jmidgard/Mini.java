package org.openCage.jmidgard;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 16.06.11
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class Mini extends Main  {

    public static void main(String[] args) {
        new Mini().start( args );
    }

    @Override
    public String getFoo() {
        return " a foo";
    }
}
