//package org.openCage.stroy.file;
//
//import java.util.HashMap;
//import java.util.Map;
//
///***** BEGIN LICENSE BLOCK *****
//* Version: MPL 1.1/GPL 2.0
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
//*
//* Alternatively, the contents of this file may be used under the terms of
//* either the GNU General Public License Version 2 or later (the "GPL"),
//* in which case the provisions of the GPL are applicable instead
//* of those above. If you wish to allow use of your version of this file only
//* under the terms of either the GPL, and not to allow others to
//* use your version of this file under the terms of the MPL, indicate your
//* decision by deleting the provisions above and replace them with the notice
//* and other provisions required by the GPL. If you do not delete
//* the provisions above, a recipient may use your version of this file under
//* the terms of any one of the MPL, the GPL.
//*
//***** END LICENSE BLOCK *****/
//
//public class FileDistanceBroker {
//
//    public static final int JAVA   = 0;
//    public static final int TEXT   = 1;
//    public static final int C      = 2;
//    public static final int BINARY = 3;
//    public static final int NOEXT  = 4;
//    public static final int XML    = 5;
//
//    private Map< String, Integer > extNormalizer = new HashMap<String, Integer>();
//
//    @SuppressWarnings({"OverlyLongMethod"})
//    public FileDistanceBroker() {
//
//        extNormalizer.put( "java", JAVA );
//
//        extNormalizer.put( "c", C );
//        extNormalizer.put( "cpp", C );
//        extNormalizer.put( "cc", C );
//        extNormalizer.put( "h", C );
//
//        extNormalizer.put( "text", TEXT );
//        extNormalizer.put( "txt", TEXT );
//        extNormalizer.put( "log", TEXT );
//        extNormalizer.put( "from", TEXT );  // TODO: form ?
//        extNormalizer.put( "jdl", TEXT );
//        extNormalizer.put( "jsp", TEXT );
//        extNormalizer.put( "sh", TEXT );
//        extNormalizer.put( "bash", TEXT );
//        extNormalizer.put( "bashrc", TEXT );
//        extNormalizer.put( "php", TEXT );
//        extNormalizer.put( "ruby", TEXT );
//        extNormalizer.put( "jsp", TEXT );
//        extNormalizer.put( "doc", TEXT );
//        extNormalizer.put( "properties", TEXT );
//        extNormalizer.put( "vcproj", TEXT );
//        extNormalizer.put( "mf", TEXT );
//        extNormalizer.put( "bat", TEXT );
//        extNormalizer.put( "py", TEXT );
//        extNormalizer.put( "ini", TEXT );   // key=val list
//        extNormalizer.put( "sln", TEXT );   // for ms visual studio
//        extNormalizer.put( "policy", TEXT );   // java plugin ??
//        extNormalizer.put( "css", TEXT );   // web style description
//
//        extNormalizer.put( "m", TEXT );   // objective c
//        extNormalizer.put( "pch", TEXT );   // objective c ?? c
//
//        extNormalizer.put( "xml", XML );
//        extNormalizer.put( "html", XML );
//        extNormalizer.put( "htm", XML );
//        extNormalizer.put( "ism", XML );
//        extNormalizer.put( "dtd", XML );
//        extNormalizer.put( "jspx", XML );
//        extNormalizer.put( "plist", XML );
//        extNormalizer.put( "xls", XML );
//        extNormalizer.put( "iml", XML );
//        extNormalizer.put( "ipr", XML );
//        extNormalizer.put( "iws", XML );
//
//
//        extNormalizer.put( "jnilib", BINARY );
//        extNormalizer.put( "dylib", BINARY );
//        extNormalizer.put( "so", BINARY );
//        extNormalizer.put( "exe", BINARY );
//        extNormalizer.put( "dll", BINARY );
//        extNormalizer.put( "lib", BINARY );
//        extNormalizer.put( "class", BINARY );
//        extNormalizer.put( "obj", BINARY );
//        extNormalizer.put( "o", BINARY );
//        extNormalizer.put( "vsd", BINARY );
//        extNormalizer.put( "jar", BINARY );
//        extNormalizer.put( "war", BINARY );
//        extNormalizer.put( "tgz", BINARY );
//        extNormalizer.put( "tar", BINARY );
//        extNormalizer.put( "gz", BINARY );
//        extNormalizer.put( "zip", BINARY );
//        extNormalizer.put( "jdb", BINARY );
//        extNormalizer.put( "pbproj", BINARY ); // ?? osx
//
//        extNormalizer.put( "jpg", BINARY );
//        extNormalizer.put( "jpeg", BINARY );
//        extNormalizer.put( "gif", BINARY );
//        extNormalizer.put( "png", BINARY );
//        extNormalizer.put( "bmp", BINARY );
//        extNormalizer.put( "raw", BINARY );
//        extNormalizer.put( "icns", BINARY );
//
//        extNormalizer.put( "mp3", BINARY );
//
//        extNormalizer.put( "jude", BINARY );
//        extNormalizer.put( "pdf", BINARY );
//        extNormalizer.put( "dat", BINARY ); // ???
//        extNormalizer.put( "ncb", BINARY );
//        extNormalizer.put( "suo", BINARY );   // for ms visual studio
//        extNormalizer.put( "pbxtree", BINARY );
//        extNormalizer.put( "header", BINARY );
//        extNormalizer.put( "pbxsymbols", BINARY );
//        extNormalizer.put( "xcodeproj", BINARY );
//        extNormalizer.put( "xcode", BINARY );
//
//        extNormalizer.put( "pbxproj", BINARY ); // todo what and print is it
//
//        extNormalizer.put( "DS_Store", BINARY );
//
//        extNormalizer.put( "", NOEXT );
//    }
//
//    public boolean contains( String ext ) {
//        return extNormalizer.containsKey( ext.toLowerCase() );
//    }
//
//
////    public Distance<TreeNode<FileContent>> getDistance( final TreeNode<FileContent> file ) {
////
////        final Injector injector = Guice.createInjector( new RuntimeModule() );
////
////
////        final String ext = FileUtils.getExtension( file.getContent().getFile() );
////
////        if ( !extNormalizer.containsKey( ext )) {
////            System.err.println("unknown extension " + ext);
////
////            return injector.getInstance( RefuseContentDistance.class );
////        }
////
////        switch( extNormalizer.get( ext ).intValue() ) {
////            case JAVA:
////                return injector.getInstance( JavaContentDistance.class );
////            case TEXT:
////            case C:
////                return injector.getInstance( TextContentDistance.class );
////            case BINARY:
////                return injector.getInstance( RefuseContentDistance.class );
////            case NOEXT:
////
////                if ( file.getName().equalsIgnoreCase( "Makefile" )) {
////                    return injector.getInstance( TextContentDistance.class );
////                }
////
////                System.err.println("unknown empty extension " + file.getName());
////
////                return injector.getInstance( RefuseContentDistance.class );
////            default:
////
////                System.err.println("unhandled extension " + ext);
////                return injector.getInstance( RefuseContentDistance.class );
////        }
////
////    }
//
//
////    public Distance<File> findContentDistance( final File file ) {
////
////        final Injector injector = Guice.createInjector( new RuntimeModule() );
////
////
////        final String ext = FileUtils.getExtension( file );
////
////        if ( !extNormalizer.containsKey( ext )) {
////            System.err.println("unknown extension " + ext);
////
////            return null;
////        }
////
////        switch( extNormalizer.get( ext ).intValue() ) {
////            case JAVA:
////                return injector.getInstance( JavaContentAsMapDistance.class );
////            case TEXT:
////            case C:
////                return injector.getInstance( TextContentAsMapDistance.class );
////            case BINARY:
////                return null;
////            case NOEXT:
////
////                if ( file.getContent().getName().equalsIgnoreCase( "Makefile" )) {
////                    return injector.getInstance( TextContentAsMapDistance.class );
////                }
////
////                System.err.println("unknown empty extension " + file.getContent().getName());
////
////                return null;
////            default:
////
////                System.err.println("unhandled extension " + ext);
////                return null;
////        }
////
////    }
//
//
////    public FuzzyHash calculateFuzzyHash( File file ) {
////
////        final Injector injector = Guice.createInjector( new RuntimeModule() );
////
////        final String ext = FileUtils.getExtension( file );
////
////        if ( !extNormalizer.containsKey( ext )) {
////            System.err.println("unknown extension " + ext);
////
////            return null;
////        }
////
////        LineNoise noise = null;
////
////        switch( extNormalizer.get( ext ).intValue() ) {
////            case JAVA:
////
////                noise = /*injector.getInstance( ); */new JavaLineNoiseForDistanceRegex();
////
////            case TEXT:
////
////                Set<Integer> set = new HashSet<Integer>();
////
////
////                for ( String line : new IterableFile( file ) ) {
////                    if ( noise == null || !noise.isGrayNoise( line )) {
////                        set.add( line.hashCode() );
////                    }
////                }
////
////                FuzzyHashSet ret = new FuzzyHashSet( set );
////                injector.injectMembers( ret );
////
////                return ret;
////
////            default:
////                return new FuzzyHashNever();
////        }
////
////    }
////
//    public boolean isGoodEnough( final double dist ) {
//        return dist < 0.31;
//    }
//
//    public int get(String ext) {
//        return extNormalizer.get( ext.toLowerCase() );
//    }
//}
