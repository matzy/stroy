package org.openCage.util.io;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 19.11.2008
 * Time: 22:48:01
 * To change this template use File | Settings | File Templates.
 */
public class LoopStateImpl implements LoopState {
    private int idx = 0;
    private boolean broken = false;


    public void doBreak() {
        broken = true;
    }

    public int idx() {
        return idx;
    }

    public boolean isLast() {
        return false;
    }


    public boolean isBroken() {
        return broken;
    }

    public void incr() {
        ++idx;
    }
}
