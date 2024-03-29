package org.openCage.stroy.file;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.stroy.protocol.Distance;
import org.openCage.stroy.text.LineNoise;

import java.io.File;
import java.util.List;
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

public class JavaContentDistance implements Distance<File> {

    final private Distance<List<String>> arrayDistance;
    final private LineNoise noise;

    @Inject
    public JavaContentDistance( Distance<List<String>> arrayDistance, @Named( "java" ) LineNoise noise ) {
        this.arrayDistance = arrayDistance;
        this.noise         = noise;
    }

    public double distance(File a, File b) {

        return arrayDistance.distance( getContent(a), getContent(b));
    }

    private List<String> getContent( File file ) {

                throw LogError.log( new Error( "impl me" ));

//        List<String> cont = new ArrayList<String>();
//        LineReaderIterator it = FileUtils.iterator( file );
//        try {
//            it = FileUtils.iterator( file );
//            for ( String line : Iterators.loop( it ) ) {
//                if ( !noise.isGrayNoise( line )) {
//                    cont.add( line );
//                }
//            }
//        } catch ( Exception exp ) {
//            Log.warning( "can't read file: " + file.getAbsolutePath() );  // NON-NLS
//        } finally {
//            LineReaderIterator.close( it );
//        }
//
//
//
//        return cont;
    }
}
