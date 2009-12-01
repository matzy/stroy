package org.openCage.lang.protocol;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.openCage.lang.impl.BackgroundExecutorImpl;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 1, 2009
 * Time: 11:30:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class LangWiring implements Module {

    public void configure(Binder binder) {
        binder.bind( BackgroundExecutor.class ).to( BackgroundExecutorImpl.class );
        
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof LangWiring );
    }
}
