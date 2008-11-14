package org.openCage.stroy.matching;

import org.openCage.stroy.diff.ContentDiff;

public interface EdgeAttributes {

    public double getQuality();
    public void   setQuality( double quality );

    public ContentDiff getDifference();
    public void        setDifference( ContentDiff payload );

}
