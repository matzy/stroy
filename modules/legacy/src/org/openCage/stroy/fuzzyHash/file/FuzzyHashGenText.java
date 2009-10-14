package org.openCage.stroy.fuzzyHash.file;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.stroy.algo.hash.Hash;
import org.openCage.lang.protocol.HasDistance;
import org.openCage.stroy.fuzzyHash.FuzzyHashGenerator;
import org.openCage.stroy.fuzzyHash.FuzzyHashSetFactory;
import org.openCage.stroy.text.LineNoise;

import java.io.File;
import java.util.logging.Logger;

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

public class FuzzyHashGenText implements FuzzyHashGenerator<File> {

    private static final Logger LOG = Logger.getLogger(FuzzyHashGenText.class.getName());

    private final LineNoise    noise;
    private final Hash<String> hash;
    private final FuzzyHashSetFactory fuzzyHashSetFactory;

    @Inject
    public FuzzyHashGenText( @Named("text") final LineNoise    javaNoise,
                             @Named("text") final Hash<String> javaHash,
                             final FuzzyHashSetFactory   fuzzyHashSetFactory ) {
        this.noise               = javaNoise;
        this.hash = javaHash;
        this.fuzzyHashSetFactory = fuzzyHashSetFactory;
    }


//    public FuzzyHash generate( final File file ) {
//
//        System.out.println( "fuzzyhash gen " + file.getAbsolutePath()  );
//
//        final Set<Integer> set = new HashSet<Integer>();
//
//        int ii = 0;
//        try {
//            for ( final String line : Iterators.lines( file ) ) {
//                ii++;
//
//                if ( !noise.isGrayNoise( line )) {
//                    set.add( hash.getHash( line ) );
//                }
//            }
//        } catch ( Exception exp ) {
//            Log.warning( "can't read file: " + file.getAbsolutePath() );
//        } catch ( Error err ) {
//            Log.severe( "can't read file: " + file.getAbsolutePath() + "lines " + ii );
//            Log.severe( "error " + err.getMessage() );
//
//        }
//
//        return fuzzyHashSetFactory.getOrCreate( set );
//    }

    public HasDistance generate( final File file ) {

        return FuzzyHashForTextFiles.gen(noise, hash, fuzzyHashSetFactory, file);
    }
}
