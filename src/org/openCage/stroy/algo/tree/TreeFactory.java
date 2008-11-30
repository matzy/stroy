package org.openCage.stroy.algo.tree;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 30.11.2008
 * Time: 18:44:13
 * To change this template use File | Settings | File Templates.
 */
public interface TreeFactory {

    public NoedGenerator create( String path, boolean single );
}
