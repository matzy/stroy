package org.openCage.fspath.wiring;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.muchsoft.util.Sys;
import org.openCage.fspath.clazz.FSPathBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 30, 2009
 * Time: 10:37:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class FSPathWiring implements Module {
    public void configure(Binder binder) {

//        if ( Sys.isMacOSX() || Sys.isLinux() ) {
//            binder.bind( FSPathBuilder.class).to( FSPathBuilderUnix.class );
//        }
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o != null && (o instanceof FSPathWiring);
    }
}
