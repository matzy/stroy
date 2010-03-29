package org.openCage.property.demo;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.property.clazz.AbstractPropertyProvider;
import org.openCage.property.protocol.PropStore;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 28-mar-2010
 * Time: 13:23:22
 * To change this template use File | Settings | File Templates.
 */
public class WorkProp extends AbstractPropertyProvider<String> {
    public static final String Key = "Work";

    @Inject
    public WorkProp( @Named( "std" ) PropStore store) {
        super( store, Key, "wikidot.com" );
    }
}
