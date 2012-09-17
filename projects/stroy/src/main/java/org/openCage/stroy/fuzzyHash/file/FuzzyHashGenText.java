//package org.openCage.stroy.fuzzyHash.file;
//
//import com.google.inject.Inject;
//import com.google.inject.name.Named;
//import org.openCage.lang.functions.VF1;
//import org.openCage.stroy.algo.hash.Hash;
//import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
//import org.openCage.stroy.fuzzyHash.FuzzyHashGenerator;
//import org.openCage.stroy.fuzzyHash.FuzzyHashSetFactory;
//import org.openCage.stroy.text.LineNoise;
//import org.openCage.util.logging.Log;
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
//public class FuzzyHashGenText implements FuzzyHashGenerator<File> {
//
//    private final LineNoise    noise;
//    private final Hash<String> hash;
//    private final FuzzyHashSetFactory fuzzyHashSetFactory;
//
//    @Inject
//    public FuzzyHashGenText( @Named("ForText") final LineNoise    javaNoise,
//                             @Named("ForText") final Hash<String> javaHash,
//                             final FuzzyHashSetFactory   fuzzyHashSetFactory ) {
//        this.noise               = javaNoise;
//        this.hash = javaHash;
//        this.fuzzyHashSetFactory = fuzzyHashSetFactory;
//    }
//
//
////    public FuzzyHash generate( final File file ) {
////
////        System.out.println( "fuzzyhash gen " + file.getAbsolutePath()  );
////
////        final Set<Integer> set = new HashSet<Integer>();
////
////        int ii = 0;
////        try {
////            for ( final String line : Iterators.lines( file ) ) {
////                ii++;
////
////                if ( !noise.isGrayNoise( line )) {
////                    set.add( hash.getHash( line ) );
////                }
////            }
////        } catch ( Exception exp ) {
////            Log.warning( "can't read file: " + file.getAbsolutePath() );
////        } catch ( Error err ) {
////            Log.severe( "can't read file: " + file.getAbsolutePath() + "lines " + ii );
////            Log.severe( "error " + err.getMessage() );
////
////        }
////
////        return fuzzyHashSetFactory.getOrCreate( set );
////    }
//
//    public FuzzyHash generate( final File file ) {
//
//        Log.fine( "fuzzyhash gen " + file.getAbsolutePath()  );
//
//        final Set<Integer> set = new HashSet<Integer>();
//
//        String collect = "";
//        try {
//            FileUtils.withIterator( file, new VF1<Iterable<String>>() {
//                public void call( Iterable<String> iterable ) {
//                    int ii = 0;
//                    for ( final String line : iterable ) {
//                        ii++;
//
//                        // TODO real solution
//                        if ( ii > 2000 ) {
//                            break;
//                        }
//                        if ( !noise.isGrayNoise( line )) {
//                            set.add( hash.getHash( line ) );
//                        }
//                    }
//                }
//            } );
//        } catch ( Exception exp ) {
//            Log.warning( "can't read file: " + file.getAbsolutePath() );
//        }
////        catch ( Error err ) {
////            Log.severe( "can't read file: " + file.getAbsolutePath() + "lines " + ii );
////            Log.severe( "error " + err.getMessage() );
////
////        }
//
//        return fuzzyHashSetFactory.create( set );
//    }
//}
