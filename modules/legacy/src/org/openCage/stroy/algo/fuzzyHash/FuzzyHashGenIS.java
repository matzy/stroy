package org.openCage.stroy.algo.fuzzyHash;

import org.openCage.stroy.fuzzyHash.metric.CountChangeMetric;
import org.openCage.util.io.FileUtils;

import java.io.InputStream;
import java.util.Set;
import java.util.HashSet;

import com.google.inject.Inject;
import java.util.logging.Logger;
import org.openCage.withResource.error.LogError;

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

public class FuzzyHashGenIS implements FuzzyHashGen<InputStream>{

    private final CountChangeMetric metric;
//    private final NoiceGen noiceGen;
//    private final HashGen;

    @Inject
    public FuzzyHashGenIS( CountChangeMetric metric ) {
        this.metric = metric;
    }


    public FuzzyHash create( InputStream inputStream, String type ) {

        final Set<String> lines = new HashSet<String>();
        throw LogError.log( new Error( "impl me" ));

////        FileUtils.withIterator( inputStream, new V1<Iterable<String>>() {
////            public void call( Iterable<String> iterable ) {
////                for ( String str : iterable ) {
////                    lines.add( str );
////                }
////            }
////        } );
//
//        return null;
//        return new SetFuzzyHash( metric, type, lines );
    }
}
