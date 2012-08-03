//package org.openCage.stroy.fuzzyHash.file;
//
//import com.google.inject.Inject;
//import com.google.inject.name.Named;
//import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
//import org.openCage.stroy.file.FileTypes;
//import org.openCage.stroy.fuzzyHash.FuzzyHashGenerator;
//import org.openCage.stroy.fuzzyHash.FuzzyHashNever;
//import org.openCage.util.logging.Log;
//
//import java.io.File;
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
//public class FuzzyHashGeneratorForFiles implements FuzzyHashGenerator<File> {
//
//    private final FuzzyHashGenerator<File> javaHashGen;
//    private final FuzzyHashGenerator<File> textHashGen;
//    private final FuzzyHashGenerator<File> cHashGen;
//    private final FuzzyHashGenerator<File> xmlHashGen;
//    private final FileTypes fileTypes;
//
////    private final FileDistanceBroker broker = new FileDistanceBroker();
//
//    @Inject
//    public FuzzyHashGeneratorForFiles(
//            @Named("ForJava") final FuzzyHashGenerator<File> javaHashGen,
//            @Named("ForText") final FuzzyHashGenerator<File> textHashGen,
//            @Named("ForC") final FuzzyHashGenerator<File> cHashGen,
//            @Named("ForXML") final FuzzyHashGenerator<File> xmlHashGen, FileTypes fileTypes) {
//        this.javaHashGen = javaHashGen;
//        this.textHashGen = textHashGen;
//        this.cHashGen    = cHashGen;
//        this.xmlHashGen  = xmlHashGen;
//
//        this.fileTypes = fileTypes;
//
//    }
//
//
//    public FuzzyHash generate( final File file ) {
//        Log.finer( "fuzzy hash gen for: " + file.getAbsolutePath() );
//
////        final String ext = FileUtils.getExtension( file );
////
////        if ( !broker.contains( ext )) {
////            Log.warning( "unknown extension for FuzzyHashGeneration " + ext);
////
////            return new FuzzyHashNever();
////        }
//
//        switch( fileTypes.getAlgo( file.getName())) {
//            case java:
//                return javaHashGen.generate( file );
//
//            case text:
//                return textHashGen.generate( file );
//
//            case c:
//                return cHashGen.generate( file );
//
//            case xml:
//                return xmlHashGen.generate( file );
//
//            default:
//                return new FuzzyHashNever();
//        }
//    }
//}
