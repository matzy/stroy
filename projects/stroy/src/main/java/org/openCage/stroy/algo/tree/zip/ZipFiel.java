//package org.openCage.stroy.algo.tree.zip;
//
//import org.openCage.lang.Strings;
//import org.openCage.lang.structure.Lazy;
//import org.openCage.stroy.algo.tree.Fiel;
//import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
//
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipFile;
//import java.io.IOException;
//import java.io.InputStream;
//
//import com.twmacinta.util.MD5;
//import com.JavaExchange.www.RandomGUID;
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
//public class ZipFiel implements Fiel {
//    private String   rootPath;
//    private ZipEntry zipEntry;
//    private String   checkSum;
//    private boolean  readError = false;
//    private String   type;
//    private Lazy<FuzzyHash> lazyFuzzy;
//
//    public ZipFiel( String rootPath, ZipEntry zipEntry, String type ) { //, Lazy<FuzzyHash> lazyFuzzy ) {
//        this.rootPath = rootPath;
//        this.zipEntry = zipEntry;
//        this.type     = type;
////        this.lazyFuzzy = lazyFuzzy;
//    }
//
//    public String getFingerprint() {
//        if ( checkSum == null ) {
//            try {
//                ZipFile zf = new ZipFile( rootPath );
//                InputStream is = zf.getInputStream( zipEntry );
//                checkSum = Strings.asHex(MD5.getHash(is, getSize()));
//                zf.close();
//            } catch( IOException e ) {
//                checkSum = new RandomGUID().toString();
//                readError = true;
//            }
//        }
//
//        return checkSum;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public FuzzyHash getFuzzyHash() {
//        return lazyFuzzy.get();
//    }
//
//    public long getSize() {
//        return zipEntry.getSize();
//    }
//
//    public boolean hasReadError() {
//        return readError;
//    }
//}
