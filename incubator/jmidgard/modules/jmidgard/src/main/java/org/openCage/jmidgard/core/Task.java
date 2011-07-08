package org.openCage.jmidgard.core;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 22.06.11
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */
public interface Task {

    public Collection<Task> getPrerequisites();
    public boolean          needsToRun();
    public boolean          run();
    public void             clean();
    public String           getName();
    public void             depends( Task task );

    public Task setConf(Base base, String module);

    public Base getBase();

}
