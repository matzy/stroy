package org.openCage.gpad.providers;

import com.google.inject.Inject;
import org.openCage.fspath.clazz.FSPathBuilder;
import org.openCage.gpad.Constants;
import org.openCage.lang.MRU;
import org.openCage.lang.protocol.SingletonApp;
import org.openCage.property.protocol.AbstractPropStoreProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Feb 1, 2010
 * Time: 7:04:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransPropStoreProvider  extends AbstractPropStoreProvider {

    @Inject
    public TransPropStoreProvider( SingletonApp singletonApp ) {
        super( FSPathBuilder.getPreferences().add( Constants.FAUSTERIZE, "prefs-trans.xml").toFile(), singletonApp, getAliases());
    }

    private static Map<String,Class> getAliases() {
        Map<String,Class> aliases = new HashMap<String, Class>();
        aliases.put( "MRU", MRU.class );
        return aliases;
    }
}
