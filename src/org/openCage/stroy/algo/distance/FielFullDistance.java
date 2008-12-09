package org.openCage.stroy.algo.distance;

import org.openCage.stroy.algo.tree.Fiel;

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

public class FielFullDistance implements Distance<Fiel> {
    private static final double HEURISTIC = 0.001;

    public double distance( Fiel a, Fiel b ) {

        if ( a.getType().equals( b.getType() )) {
            return 1;
        }

        if ( a.getSize() == b.getSize() ) {
            if ( a.getChecksum().equals( b.getChecksum() )) {
                return 0;
            }
        }

        // fuzzy equality is just a heuristic to differentiate from fingerprint equal
        // add a tiny number
        return Math.max( 1, HEURISTIC + a.getFuzzyHash().fuzzyEqual( b.getFuzzyHash()));
    }
}
