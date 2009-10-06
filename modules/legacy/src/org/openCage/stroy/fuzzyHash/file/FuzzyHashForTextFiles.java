/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openCage.stroy.fuzzyHash.file;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
import org.openCage.stroy.algo.hash.Hash;
import org.openCage.stroy.fuzzyHash.FuzzyHashSetFactory;
import org.openCage.stroy.text.LineNoise;
import org.openCage.withResource.impl.WithImpl;
import org.openCage.withResource.protocol.FileLineIterable;
import org.openCage.withResource.protocol.With;

/**
 *
 * @author stephan
 */
public class FuzzyHashForTextFiles {

    private static With with = new WithImpl();

    public static FuzzyHash gen( final LineNoise noise, final Hash<String> hash, FuzzyHashSetFactory fuzzyHashSetFactory, File file ) {
        final Set<Integer> set = new HashSet<Integer>();
        int blockSize = 1;
        int idx = 0;
        int blockcount = 0;
        String combined = "";

        FileLineIterable dli = with.getLineIteratorCloseInFinal(file);
        try {
            for (String line : dli) {
                if (!noise.isGrayNoise(line)) {
                    idx++;

                    combined += line;

                    if ( idx == blockSize ) {
                        set.add(hash.getHash(combined));
                        combined = "";
                        idx = 0;

                        blockcount++;
                        if ( blockcount == 1000 ) {
                            blockSize *= 5;
                            blockcount = 0;
                        }
                    }
                }

            }
        } finally { dli.close(); }

        return fuzzyHashSetFactory.create(set);
    }
}

