package org.openCage.stroy.fuzzyHash;

import org.openCage.util.collection.Sets;
import org.openCage.stroy.fuzzyHash.metric.CountChangeMetric;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;

import java.util.Set;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
