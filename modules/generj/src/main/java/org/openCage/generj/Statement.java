package org.openCage.generj;


/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 20, 2010
 * Time: 10:31:42 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Statement {

    /**
     * toString method with a prefix
     * note: this method should not end with a newline
     * @param prefix
     * @return
     */
    public String toString( String prefix );
}
