package org.openCage.stroy.fuzzyHash.metric;

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


// LOOK

public class SizeWeightedMetric implements CountChangeMetric{
    public double distance(int src, int same, int tgt) {

        if ( tgt == 0 ) {

            if ( src == 0 ) {
                return 0.5;
            }

            return 1.0;
        }

        if ( src == 0 ) {
            return 1.0;
        }

        double min = Math.max( 1, Math.log10( 500 - Math.min( 500, Math.min( src, tgt ))));

        double symetric = 1- (((0.5 * same) / src) + ((0.5 * same) / tgt ));

        double ret = Math.pow( symetric, min );

        return ret;
    }
}
