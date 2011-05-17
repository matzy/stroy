//package org.openCage.gpad.providers;
//
//import com.google.inject.Inject;
//import com.google.inject.Provider;
//import org.openCage.gpad.Constants;
//import org.openCage.io.fspath.FSPathBuilder;
//import org.openCage.lang.BackgroundExecutor;
//import org.openCage.io.SingletonApp;
//import org.openCage.lang.structure.MRU;
//import org.openCage.property.PropStore;
//import org.openCage.property.PersistingPropStore;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class TransPropStoreProvider implements Provider<PropStore> {
//    private SingletonApp singleApp;
//    private BackgroundExecutor executor;
//
//    @Inject
//    public TransPropStoreProvider( SingletonApp singleApp, BackgroundExecutor executor ) {
//        this.singleApp = singleApp;
//        this.executor = executor;
//    }
//
//
//    private static Map<String,Class> getAliases() {
//        Map<String,Class> aliases = new HashMap<String, Class>();
//        aliases.put( "MRU", MRU.class );
//        return aliases;
//    }
//
//    @Override
//    public PropStore get() {
//        return new PersistingPropStore(
//                executor,
//                FSPathBuilder.getPreferences().add( Constants.FAUSTERIZE, "prefs-trans.xml").toFile(),
//                null,
//                singleApp );
//    }
//}
