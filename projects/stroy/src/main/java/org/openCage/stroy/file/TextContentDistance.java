//package org.openCage.stroy.file;
//
//import com.google.inject.Inject;
//import org.openCage.lang.iterators.Iterators;
//import org.openCage.util.io.LineReaderIterator;
//import org.openCage.lindwurm.FileUtils;
//import org.openCage.stroy.algo.distance.Distance;
//import org.openCage.util.logging.Log;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
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
//public class TextContentDistance implements Distance<File> {
//    final private Distance<List<String>> arrayDistance;
//
//    @Inject
//    public TextContentDistance( Distance<List<String>> arrayDistance ) {
//        this.arrayDistance = arrayDistance;
//    }
//
//    public double distance(File a, File b) {
//
//        return arrayDistance.distance( getContent(a), getContent(b));
//    }
//
//    private List<String> getContent( File file ) {
//
//        List<String> cont = new ArrayList<String>();
//        LineReaderIterator it = FileUtils.iterator( file );
//        try {
//            it = FileUtils.iterator( file );
//            for ( String line : Iterators.loop(it) ) {
//                cont.add( line );
//            }
//        } catch ( Exception exp ) {
//            Log.warning( "can't read file: " + file.getAbsolutePath() );  // NON-NLS
//        } finally {
//            LineReaderIterator.close( it );
//        }
//
//
//        return cont;
//    }
//}
