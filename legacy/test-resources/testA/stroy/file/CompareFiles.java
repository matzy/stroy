//package org.openCage.stroy.file;
//
//import org.openCage.stroy.fileMeta.LeafFileMeta;
//import org.openCage.stroy.dir.LeafTreeNodeImpl;
//
//import java.io.File;
//
///**
// * Created by IntelliJ IDEA.
// * User: spfab
// * Date: Jun 1, 2007
// * Time: 10:51:20 AM
// * To change this template use File | Settings | File Templates.
// */
//public class CompareFiles {
//
//    public final LeafFileMeta from;
//    public final LeafFileMeta to;
//
//    public CompareFiles( String from, String to ) {
//        this.from = new LeafTreeNodeImpl( new File( from ), true);
//        this.to   = new LeafTreeNodeImpl( new File( to ), false );
//    }
//
//
//    public void go() {
//
//        if ( from.getCheckSum().equals( to.getCheckSum())) {
//            System.out.println("file content: identical");
//        } else {
//            System.out.println("file content: different");
//
//            double dist = new LeafFileMetaDistance().distance( from, to );
//
//            if ( dist < 0.31 ) {
//                System.out.println("     related: yes");
//            } else {
//                System.out.println("     related: no");
//            }
//
//        }
//
//
//    }
//}
