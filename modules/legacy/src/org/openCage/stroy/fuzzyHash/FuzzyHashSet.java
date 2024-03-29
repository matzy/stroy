package org.openCage.stroy.fuzzyHash;

import org.openCage.util.collection.Sets;
import org.openCage.stroy.fuzzyHash.metric.CountChangeMetric;
import org.openCage.lang.protocol.HasDistance;

import java.util.Set;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class FuzzyHashSet implements HasDistance {

    private final Set<Integer>       set;
    private final CountChangeMetric  metric;

    public FuzzyHashSet( final CountChangeMetric metric, Set<Integer> set ) {
        this.set    = set;
        this.metric = metric;
    }

    public double distance( HasDistance other ) {
        if ( ! ( other instanceof FuzzyHashSet  )) {
            return 1.0;
        }

        FuzzyHashSet otherSet = (FuzzyHashSet)other;

        return metric.distance( set.size(), Sets.intersectionSize( set, otherSet.set ), otherSet.set.size() );
    }


    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FuzzyHashSet that = (FuzzyHashSet) o;

        return Sets.equal( set, that.set);
    }

    @Override
    public int hashCode() {
        return (set != null ? set.hashCode() : 0);
    }
}
