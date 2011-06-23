package org.openCage.jmidgard;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 16.06.11
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */
public abstract class Main {

    public void start(String[] args) {
        System.out.println( "hallo " + getFoo() );
    }

    public abstract String getFoo();
}
