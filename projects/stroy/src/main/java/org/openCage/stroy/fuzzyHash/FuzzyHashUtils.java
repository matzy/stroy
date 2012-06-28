package org.openCage.stroy.fuzzyHash;

import org.openCage.lang.functions.VF1;
import org.openCage.stroy.algo.hash.Hash;
import org.openCage.stroy.text.LineNoise;
import org.openCage.util.io.FileUtils;

import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.InputStream;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ***** END LICENSE BLOCK *****/

public class FuzzyHashUtils {

    public Set<Integer> toSet( final File file,  final Hash<String> hash, final LineNoise noise ) {

        final Set<Integer> set = new HashSet<Integer>();
        FileUtils.withIterator( file, setBuilder( noise, set, hash ) );
        return set;
    }

    public Set<Integer> toSet( final InputStream is,  final Hash<String> hash, final LineNoise noise ) {

        final Set<Integer> set = new HashSet<Integer>();
        FileUtils.withIterator( is, setBuilder( noise, set, hash ));
        return set;
    }

    private VF1<Iterable<String>> setBuilder( final LineNoise noise, final Set<Integer> set, final Hash<String> hash ) {
        return new VF1<Iterable<String>>() {
            public void call( Iterable<String> iterable ) {
                for ( final String line :iterable ) {
                    if ( !noise.isGrayNoise( line )) {
                        set.add( hash.getHash( line ) );
                    }
                }
            }
        };
    }

}
