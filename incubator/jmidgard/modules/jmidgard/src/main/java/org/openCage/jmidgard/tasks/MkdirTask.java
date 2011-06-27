package org.openCage.jmidgard.tasks;

import org.openCage.jmidgard.core.BaseTask;
import org.openCage.jmidgard.core.Task;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 6/23/11
 * Time: 11:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class MkdirTask extends BaseTask {

    public MkdirTask(String name, Set<Task> prereqs) {
        super(name, prereqs);
    }

    public MkdirTask(String name, Task... prereqs) {
        super(name, prereqs);
    }

    @Override
    public boolean needsToRun() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
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
