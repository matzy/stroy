package org.openCage.jmidgard.core;

import javax.resource.cci.ResourceAdapterMetaData;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 22.06.11
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public class SimpleTask extends BaseTask {

    private boolean ran;

    public SimpleTask(String name, Task ...  prereqs ) {
        super(name, prereqs );
        ran = false;
    }


    @Override
    public boolean needsToRun() {
        return !ran;
    }

    @Override
    public boolean run() {
        ran = true;
        return true;
    }

    @Override
    public void clean() {
        ran = false;
    }

    @Override
    public String toString() {
        return getName() + ":" + ran + " ";
    }
}
