package org.openCage.stroy.task;

import org.openCage.stroy.Difference;
import org.openCage.stroy.diff.ContentDiff;

import java.util.Collection;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public interface MatchingTask<T> {

    public void addLeft( T obj );
    public void addRight( T obj );

    public Collection<T> getUnmatchedLeft();
    public Collection<T> getUnmatchedRight();
    public Collection<T> getMatchedLeft();
    public Collection<T> getMatchedRight();

    public boolean isMatched( T obj );
    public T       getMatch( T obj );

    public void match( T src, T tgt, double quality  );
    public void breakMatch( T src );

    public T getLeftRoot();
    public T getRightRoot();

    public void setRoots( T src, T tgt );

    public void remove(T node);

    public double getMatchQuality(T obj);

    public void addNodeChangeListener( NodeChangeListener listener );
    public void removeNodeChangeListener( NodeChangeListener listener );

    public ContentDiff getDifference( T obj );
    public void        setDifference( T obj, ContentDiff payload );
}
