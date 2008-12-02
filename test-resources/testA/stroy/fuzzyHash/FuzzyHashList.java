package org.openCage.stroy.fuzzyHash;

import com.google.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1/GPL 2.0
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
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU General Public License Version 2 or later (the "GPL"),
* in which case the provisions of the GPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL, and not to allow others to
* use your version of this file under the terms of the MPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the MPL, the GPL.
*
***** END LICENSE BLOCK *****/

public class FuzzyHashList implements FuzzyHash {

    private final List<Integer>  list;
    // transient
    private Map<Integer,Integer> mapped;

    @Inject
    private CountChangeMetric metric;

    public FuzzyHashList( List<Integer>  list ) {
        this.list = list;
    }


    public double fuzzyEqual(FuzzyHash other) {

        if ( ! ( other instanceof FuzzyHashList  )) {
            return 0.0;
        }

        final Map<Integer,Integer> otherMap = ((FuzzyHashList)other).getMapped();

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
