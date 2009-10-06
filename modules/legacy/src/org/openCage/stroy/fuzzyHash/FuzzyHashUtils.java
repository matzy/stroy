package org.openCage.stroy.fuzzyHash;

import org.openCage.stroy.algo.hash.Hash;
import org.openCage.stroy.text.LineNoise;
import org.openCage.util.io.FileUtils;

import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.InputStream;
import org.openCage.withResource.error.LogError;

public class FuzzyHashUtils {

    public Set<Integer> toSet( final File file,  final Hash<String> hash, final LineNoise noise ) {

        throw LogError.log( new Error( "impl me" ));
//        final Set<Integer> set = new HashSet<Integer>();
//        FileUtils.withIterator( file, setBuilder( noise, set, hash ) );
//        return set;
    }

    public Set<Integer> toSet( final InputStream is,  final Hash<String> hash, final LineNoise noise ) {

        throw LogError.log( new Error( "impl me" ));
//        final Set<Integer> set = new HashSet<Integer>();
//        FileUtils.withIterator( is, setBuilder( noise, set, hash ));
//        return set;
    }

//    private V1<Iterable<String>> setBuilder( final LineNoise noise, final Set<Integer> set, final Hash<String> hash ) {
//        return new V1<Iterable<String>>() {
//            public void call( Iterable<String> iterable ) {
//                for ( final String line :iterable ) {
//                    if ( !noise.isGrayNoise( line )) {
//                        set.add( hash.getHash( line ) );
//                    }
//                }
//            }
//        };
//    }

}
