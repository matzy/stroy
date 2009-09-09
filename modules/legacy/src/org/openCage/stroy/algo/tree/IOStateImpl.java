package org.openCage.stroy.algo.tree;

public class IOStateImpl implements IOState {

    private Exception exp = null;

    public boolean isError() {
        return exp != null;
    }

    public void setError( Exception exp ) {
        this.exp = exp;
    }
}
