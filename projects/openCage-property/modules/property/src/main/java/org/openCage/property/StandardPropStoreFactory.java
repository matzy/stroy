//package org.openCage.property;
//
//import com.google.inject.Inject;
//import org.openCage.artig.stjx.Artifact;
//import org.openCage.artig.stjx.Deployed;
//import org.openCage.io.SingletonApp;
//import org.openCage.io.fspath.FSPathBuilder;
//import org.openCage.lang.BackgroundExecutor;
//
//
//public class StandardPropStoreFactory  {
//    private BackgroundExecutor executor;
//    private Deployed arti;
//    private SingletonApp single;
//
//
//    @Inject
//    public StandardPropStoreFactory( SingletonApp singleApp, BackgroundExecutor executor, Deployed arti ) {
//        this.executor = executor;
//        this.arti = arti;
//        this.single = singleApp;
//    }
//
//    public PropStore get( String name ) {
//        return new PersistingPropStore( executor,
//                FSPathBuilder.getPreferences().add( arti.getArtifact().getName(), name).toFile(),
//                null,
//                single );
//    }
//
//
//}
