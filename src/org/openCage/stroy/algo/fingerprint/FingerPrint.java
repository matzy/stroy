package org.openCage.stroy.algo.fingerprint;

import org.openCage.stroy.algo.tree.IOState;

public interface FingerPrint<T> {

    public String getFingerprint( T obj, IOState state );
}
