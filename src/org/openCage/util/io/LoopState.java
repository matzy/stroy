package org.openCage.util.io;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 19.11.2008
 * Time: 22:46:22
 * To change this template use File | Settings | File Templates.
 */
public interface LoopState {

    public void    doBreak();
    public int     idx();
    public boolean isLast();

}
