//package org.openCage.stroy.algo.tree.filesystem;
//
//import org.openCage.stroy.tree.Fiel;
//import org.openCage.stroy.hash.FuzzyHash;
//import org.openCage.stroy.hash.FingerPrint;
//import org.openCage.stroy.hash.FingerPrintGen;
//import org.openCage.lang.Lazy;
//import org.openCage.lang.F0;
//import org.jetbrains.annotations.NotNull;
//
//import java.io.File;
//
///***** BEGIN LICENSE BLOCK *****
//* Version: MPL 1.1
//*
//* The contents of this file are subject to the Mozilla Public License Version
//* 1.1 (the "License"); you may not use this file except in compliance with
//* the License. You may obtain a copy of the License at
//* http://www.mozilla.org/MPL/
//*
//* Software distributed under the License is distributed on an "AS IS" basis,
//* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
//* for the specific language governing rights and limitations under the
//* License.
//*
//* The Original Code is stroy code.
//*
//* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
//
///**
// * A standard file system file as seen through fiel eyes
// */
//public class FSFiel implements Fiel {
//    private final File file;
//
//    private Lazy<FuzzyHash> fuzzy;
//
//    private final Lazy<String> calcChecksum;
//
//    public FSFiel( @NotNull final File file,
//                   @NotNull final FingerPrintGen<File> calc,
//                   @NotNull final FuzzyHashGenGen gg) {
//        this.file = file;
//
//        calcChecksum = new Lazy<String>( new F0<String>() {
//            public String c() {
//                return calc.getFingerPrint( file );
//            }
//        } );
//
//        fuzzy = new Lazy<FuzzyHash>( new F1<FuzzyHash>() {
//            public FuzzyHash c() {
//                return null;  //To change body of implemented methods use File | Settings | File Templates.
//            }
//        } );
//
//
//    }
//
//    public String getFingerprint() {
//        return calcChecksum.get();
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public FuzzyHash getFuzzyHash() {
//        return fuzzy.get();
//    }
//
//    public long getSize() {
//        return file.length();
//    }
//
//    public boolean hasReadError() {
//        return ioState.isError();
//    }
//}
