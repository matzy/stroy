//package org.openCage.stroy.fuzzyHash.file;
//
//import com.google.inject.Inject;
//import com.google.inject.name.Named;
//import org.openCage.stroy.algo.hash.Hash;
//import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
//import org.openCage.stroy.fuzzyHash.FuzzyHashGenerator;
//import org.openCage.stroy.fuzzyHash.FuzzyHashSetFactory;
//import org.openCage.stroy.text.LineNoise;
//import org.openCage.util.logging.Log;
//import org.openCage.util.io.LineReaderIterator;
//import org.openCage.lindwurm.FileUtils;
//
//import java.io.File;
//import java.util.HashSet;
//import java.util.Set;
//
///***** BEGIN LICENSE BLOCK *****
// * BSD License (2 clause)
// * Copyright (c) 2006 - 2012, Stephan Pfab
// * All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions are met:
// *     * Redistributions of source code must retain the above copyright
// *       notice, this list of conditions and the following disclaimer.
// *     * Redistributions in binary form must reproduce the above copyright
// *       notice, this list of conditions and the following disclaimer in the
// *       documentation and/or other materials provided with the distribution.
// *
// * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
// * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
// * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
// * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
// * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
// * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
// * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//***** END LICENSE BLOCK *****/
//
//public class FuzzyHashGenC implements FuzzyHashGenerator<File> {
//
//    private final LineNoise    noise;
//    private final Hash<String> hash;
//    private final FuzzyHashSetFactory fuzzyHashSetFactory;
//
//    @Inject
//    public FuzzyHashGenC( @Named("ForC") final LineNoise noise,
//                          @Named("ForC") final Hash<String> hash,
//                          FuzzyHashSetFactory fuzzyHashSetFactory ) {
//        this.noise = noise;
//        this.hash = hash;
//        this.fuzzyHashSetFactory = fuzzyHashSetFactory;
//    }
//
//
//    public FuzzyHash generate( final File file ) {
//
//
//            final Set<Integer> set = new HashSet<Integer>();
//            LineReaderIterator it = FileUtils.iterator( file );
//
//            try {
//                for ( final String line : Iterators.loop( it )) {
//                    if ( !noise.isGrayNoise( line )) {
//                        set.add( hash.getHash( line ) );
//                    }
//                }
//            } catch ( Exception exp ) {
//                Log.warning( "can't read file: " + file.getAbsolutePath() );
//            } finally {
//                LineReaderIterator.close( it );
//            }
//
//        return fuzzyHashSetFactory.create( set );
//    }
//
//}
