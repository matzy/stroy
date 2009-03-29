package org.openCage.utils.iterator;

import java.util.Collection;

public interface TreeNodeAdapter<T> {

    public boolean isLeaf( T node );
    public Collection<T> getChildren( T node );
    public T             getParent( T node );
}
