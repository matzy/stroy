package org.openCage.stroy2;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jun 28, 2009
 * Time: 5:00:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class StroyCmd {
    private String left;
    private String right;

    public StroyCmd(String left, String right) {
        this.left = left;
        this.right = right;
    }


    public String getRight() {
        return right;
    }

    public String getLeft() {
        return left;
    }
}
