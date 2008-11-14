package org.openCage.stroy.matching;

import org.openCage.stroy.task.NodeChangeListener;
import org.openCage.stroy.diff.ContentDiff;
import org.openCage.util.lang.F1;

import java.util.Collection;

public interface Task<T> {
    public void addLeft( T obj );
    public void addRight( T obj );
    public void remove( T obj );

    public Collection<T> getLeft( F1<Boolean, T> filter );
    public Collection<T> getRight( F1<Boolean, T> filter );

    public boolean isMatched( T obj );
    public T       getMatch( T obj );

    public void match( T left, T right  );
    public void breakMatch( T obj );

//    public void addNodeChangeListener( NodeChangeListener listener );
//    public void removeNodeChangeListener( NodeChangeListener listener );
//
//

    public EdgeAttributes getEdgeAttributes( T node );
    
}
