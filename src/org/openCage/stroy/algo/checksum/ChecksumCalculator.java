package org.openCage.stroy.algo.checksum;

import org.openCage.stroy.algo.tree.IOState;

public interface ChecksumCalculator {

    public String getChecksum( Object obj, IOState state );
}
