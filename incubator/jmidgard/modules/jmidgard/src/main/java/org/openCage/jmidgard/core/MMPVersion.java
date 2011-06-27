package org.openCage.jmidgard.core;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 6/24/11
 * Time: 7:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MMPVersion implements Version {

    private int patch;
    private int major;
    private int minor;

    public MMPVersion( int major, int minor, int patch ) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;

    }

    @Override
    public boolean isSnapshot() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int compareTo(Version version) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
