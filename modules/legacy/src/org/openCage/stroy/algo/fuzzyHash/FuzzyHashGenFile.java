package org.openCage.stroy.algo.fuzzyHash;

import org.openCage.lang.protocol.HasDistance;
import org.openCage.stroy.text.LineNoise;
import org.openCage.util.io.FileUtils;

import java.util.Set;
import java.util.HashSet;
import java.io.File;
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

public class FuzzyHashGenFile implements FuzzyHashGen<File> {
    private LineNoise noise;
    private Object hash;

    public HasDistance create( File file, String typ ) {

        throw LogError.log( new Error( "impl me" ));
//        final Set<String> lines = new HashSet<String>();
//
////        FileUtils.withIterator( file, new V1<Iterable<String>>() {
////            public void call( Iterable<String> iterable ) {
////                for ( String str : iterable ) {
////                    if ( !noise.isGrayNoise( str )) {
//////                        lines.add( hash. str );
////                    }
////                }
////            }
////        } );
//
//        return null;
    }
}
