package org.openCage.lang.protocol;

public interface SingletonApp {

    /**
     * is this the first instance of the program
     * @return
     */
    public boolean isMaster();
}
