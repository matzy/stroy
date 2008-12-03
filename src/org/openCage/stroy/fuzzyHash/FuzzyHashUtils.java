package org.openCage.stroy.fuzzyHash;

import org.openCage.stroy.FingerPrint;
import org.openCage.stroy.text.LineNoise;
import org.openCage.util.io.FileUtils;
import org.openCage.util.lang.V1;

import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.InputStream;

public class FuzzyHashUtils {

    public Set<Integer> toSet( final File file,  final FingerPrint<String> fingerPrint, final LineNoise noise ) {

        final Set<Integer> set = new HashSet<Integer>();
        FileUtils.withIterator( file, setBuilder( noise, set, fingerPrint ) );
        return set;
    }

    public Set<Integer> toSet( final InputStream is,  final FingerPrint<String> fingerPrint, final LineNoise noise ) {

        final Set<Integer> set = new HashSet<Integer>();
        FileUtils.withIterator( is, setBuilder( noise, set, fingerPrint ));
        return set;
    }

    private V1<Iterable<String>> setBuilder( final LineNoise noise, final Set<Integer> set, final FingerPrint<String> fingerPrint ) {
        return new V1<Iterable<String>>() {
            public void call( Iterable<String> iterable ) {
                for ( final String line :iterable ) {
                    if ( !noise.isGrayNoise( line )) {
                        set.add( fingerPrint.getHash( line ) );
                    }
                }
            }
        };
    }

}
