package org.openCage.jmidgard.core;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 6/24/11
 * Time: 6:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Version extends Comparable<Version>{

    public boolean isSnapshot();
}
