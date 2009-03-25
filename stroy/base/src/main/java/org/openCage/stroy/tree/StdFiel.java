package org.openCage.stroy.tree;

import org.openCage.stroy.mimetype.MimeList;
import org.openCage.stroy.hash.FuzzyHash;
import org.openCage.lang.*;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

/**
 * Wherever a fiel comes from, most can present there content as InputStream
 */
public class StdFiel implements Fiel {

    private E0<InputStream> is;
    private MimeList        mimes;
    private long            size;
    private L1<String, InputStream> hash;
    private L1<FuzzyHash, InputStream> fuzzyHash;

    public StdFiel( @NotNull final E0<InputStream> isGetter,
                    MimeList       mimes,
                    long           size,
                    @NotNull final E1<String, InputStream> hashGen,
                    @NotNull final E1<FuzzyHash, InputStream> fuzzyHashGen ) {
        this.is    = isGetter;
        this.mimes = mimes;
        this.size  = size;
        this.hash      = new L1<String, InputStream>( hashGen );
        this.fuzzyHash = new L1<FuzzyHash, InputStream>( fuzzyHashGen );
    }

    public MimeList getType() {
        return mimes;
    }

    public String getFingerprint() {
        return hash.get( is );
    }

    public FuzzyHash getFuzzyHash() {
        return fuzzyHash.get( is );
    }

    public long getSize() {
        return size;
    }
}
