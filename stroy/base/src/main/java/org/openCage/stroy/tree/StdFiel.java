package org.openCage.stroy.tree;

import org.openCage.stroy.mimetype.MimeList;
import org.openCage.stroy.hash.FingerPrint;
import org.openCage.stroy.hash.FuzzyHash;
import org.openCage.lang.F0;

import java.io.InputStream;

/**
 * Wherever a fiel comes from, most can present there content as InputStream
 */
public class StdFiel implements Fiel {

    private F0<InputStream> is;
    private MimeList        mimes;

    public StdFiel( F0<InputStream> isGetter, MimeList mimes ) {
        this.is    = isGetter;
        this.mimes = mimes;
    }

    public MimeList getType() {
        return mimes;
    }

    public FingerPrint getFingerprint() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public FuzzyHash getFuzzyHash() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long getSize() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
