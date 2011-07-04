package org.openCage.jmidgard.core;

import sun.plugin.dom.exception.NoModificationAllowedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.EMPTY_LIST;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 22.06.11
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseTask implements Task {

    private final String name;
    private Set<Task> prereqs;
    private Base base;

    public BaseTask( String name, Set<Task> prereqs ) {
        this.name = name;
        this.prereqs = prereqs;
    }

    public BaseTask( String name, Task ... prereqs ) {
        this(name, new HashSet<Task>( Arrays.asList( prereqs )));
    }

    public void depends( Task dep ) {
        prereqs.add( dep );
    }


    @Override public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseTask)) return false;

        BaseTask baseTask = (BaseTask) o;

        if (name != null ? !name.equals(baseTask.name) : baseTask.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public Collection<Task> getPrerequisites() {
        return prereqs;
    }

    public Task setBase( Base base  ) {
        this.base = base;
        return this;
    }

    public Base getBase() {
        return base;
    }
}
