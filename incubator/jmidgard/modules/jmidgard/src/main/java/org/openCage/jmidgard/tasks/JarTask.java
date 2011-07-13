package org.openCage.jmidgard.tasks;

import org.openCage.jmidgard.core.BaseTask;
import org.openCage.jmidgard.core.Task;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 12.07.11
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class JarTask extends BaseTask  {
    public JarTask(String name, Set<Task> prereqs) {
        super(name, prereqs);
    }

    public JarTask(String name, Task... prereqs) {
        super(name, prereqs);
    }

    @Override
    public boolean needsToRun() {
        return true;
    }

    @Override
    public boolean run() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clean() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
