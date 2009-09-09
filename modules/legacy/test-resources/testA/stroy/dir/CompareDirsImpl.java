//package org.openCage.stroy.dir;
//
//import com.google.inject.Inject;
//import org.openCage.stroy.filter.IgnoreHelper;
//import org.openCage.util.logging.LogOne;
//
//import java.io.File;
//
///**
// * Created by IntelliJ IDEA.
// * User: spfab
// * Date: May 31, 2007
// * Time: 5:11:16 PM
// * To change this template use File | Settings | File Templates.
// */
//public class CompareDirsImpl implements CompareDirs {
//
//    private final FileMetaPool pool;
//    private File from;
//    private File to;
//
//    @Inject
//    public CompareDirsImpl( FileMetaPool pool ) {
//        this.pool = pool;
//
//        IgnoreHelper.addStd( pool.getIgnore());
//    }
//
//    public void go( String from, String to ) {
//
//        this.from = new File( from );
//        this.to = new File( to );
//
//        LogOne.fine( "compare dir starts " );
//        pool.addSrc( this.from );
//        LogOne.fine( " 1 done" );
////        pool.printAbrevStatus();
//
//        pool.addTgt( this.to );
//        LogOne.fine(  " 2 done with perfect" );
//
//        pool.match();
//
////        pool.printAbrevStatus();
//
////        pool.findHistoryMatches();
//        LogOne.fine(  " history done" );
////        pool.printAbrevStatus();
////        pool.printStatus();
//
//    }
//
//    public void addIgnore(String pattern) {
//
//        pool.getIgnore().add( pattern );
//
//    }
//
//}
