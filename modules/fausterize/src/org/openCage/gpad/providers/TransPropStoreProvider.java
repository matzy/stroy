package org.openCage.gpad.providers;

import org.openCage.fspath.clazz.FSPathBuilder;
import org.openCage.gpad.Constants;
import org.openCage.property.clazz.AbstractPropStoreProvider;

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
}
