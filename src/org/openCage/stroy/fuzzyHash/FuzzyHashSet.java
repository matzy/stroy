package org.openCage.stroy.fuzzyHash;

import org.openCage.util.collection.Sets;
import org.openCage.stroy.fuzzyHash.metric.CountChangeMetric;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class FuzzyHashSet implements FuzzyHash {

    private final Set<Integer>       set;
    private final CountChangeMetric  metric;

    public FuzzyHashSet( final CountChangeMetric metric, Set<Integer> set ) {
        this.set    = set;
        this.metric = metric;
    }

    public double fuzzyEqual( FuzzyHash other ) {
        if ( ! ( other instanceof FuzzyHashSet  )) {
            return 0.0;
        }

        FuzzyHashSet otherSet = (FuzzyHashSet)other;

        return 1.0 - metric.distance( set.size(), Sets.intersectionSize( set, otherSet.set ), otherSet.set.size() );
    }


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

    public int hashCode() {
        return (set != null ? set.hashCode() : 0);
    }
}
