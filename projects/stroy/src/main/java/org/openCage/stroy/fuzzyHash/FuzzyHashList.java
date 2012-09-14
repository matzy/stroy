package org.openCage.stroy.fuzzyHash;

import com.google.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openCage.stroy.fuzzyHash.metric.CountChangeMetric;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;

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

public class FuzzyHashList implements FuzzyHash<FuzzyHashList> {

    private final List<Integer>  list;
    // transient
    private Map<Integer,Integer> mapped;

    @Inject
    private CountChangeMetric metric;

    public FuzzyHashList( List<Integer>  list ) {
        this.list = list;
    }


    @Override
    public double fuzzyEqual( FuzzyHashList other) {


        final Map<Integer,Integer> otherMap = other.getMapped();

        int oldIdx = -1;
        double sum = 0.0;


        for ( Integer line : list ) {

            Integer idx = otherMap.get( line );

            if ( idx != null ) {
                if ( oldIdx + 1 == idx ) {
                    sum += 1.0;
                } else {
                    sum += 0.7;       
                }

                oldIdx = idx;
            }
        }



        return metric.distance( list.size(), (int)sum, otherMap.size() );
    }

    private Map<Integer, Integer> getMapped() {

        if ( mapped == null ) {

        }

        mapped = new HashMap<Integer, Integer>();

        int idx = 0;
        for ( Integer line : list ) {
            mapped.put( line, idx );
            ++idx;
        }

        return mapped;

    }


}
