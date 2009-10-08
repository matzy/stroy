package org.openCage.stroy.array;

import com.google.inject.Inject;
import org.openCage.stroy.protocol.Distance;

import java.util.List;
import java.util.ArrayList;

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

public class  ReorderIgnorantArrayDistance<T> implements Distance< List<T>> {

    final ListChangeMetric metric;

    @Inject
    public ReorderIgnorantArrayDistance( ListChangeMetric metric ) {
        this.metric = metric;
    }

    public double distance(List<T> from, List<T> to) {

        ArrayList<T> toCopy = new ArrayList<T>( to );

        int deleted = 0;
        int same    = 0;

        for ( T lineFrom : from ) {
            
            int pos = toCopy.indexOf( lineFrom );

			if ( pos >= 0 ) {
				toCopy.set( pos, null);
				same++;
			} else {
				deleted++;
			}
		}

        return metric.measure( from.size(),
                               to.size(),
                               same,
                               deleted,
                               to.size() - same );
    }
}
