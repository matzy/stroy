package org.openCage.gpad.providers;

import org.openCage.fspath.clazz.FSPathBuilder;
import org.openCage.gpad.Constants;
import org.openCage.lang.clazz.MRU;
import org.openCage.property.clazz.AbstractPropStoreProvider;

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

    public TransPropStoreProvider() {
        super( FSPathBuilder.getHome().add( Constants.PROJECT_DIR, ".prefs-trans.xml").toFile());
    }

    private static Map<String,Class> getAliases() {
        Map<String,Class> aliases = new HashMap<String, Class>();
        aliases.put( "MRU", MRU.class );
        return aliases;
    }
}
