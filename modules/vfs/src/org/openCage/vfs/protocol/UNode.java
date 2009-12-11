package org.openCage.vfs.protocol;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 11, 2009
 * Time: 4:24:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UNode {

    public List<? extends UNode> getChildren();
    public String                getName();
}
