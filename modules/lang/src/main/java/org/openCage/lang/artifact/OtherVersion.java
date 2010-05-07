package org.openCage.lang.artifact;

public class OtherVersion extends Version {


    private String tag;


    OtherVersion( String tag ) {
        this.tag = tag;
    }

    @Override public String getShort() {
        return tag;
    }

    @Override public String getFull() {
        return tag;
    }

    @Override public int getBuildnumber() {
        return 0;
    }

    @Override public int compareTo(Version o) {
        return tag.compareTo( o.getFull() );
    }

    @Override
    public String toString() {
        return tag;
    }
}
