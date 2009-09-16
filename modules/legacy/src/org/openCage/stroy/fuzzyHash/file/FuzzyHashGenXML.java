package org.openCage.stroy.fuzzyHash.file;

import com.google.inject.Inject;
import org.openCage.stroy.algo.hash.Hash;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
import org.openCage.stroy.fuzzyHash.FuzzyHashGenerator;
import org.openCage.stroy.fuzzyHash.FuzzyHashSetFactory;
import org.openCage.stroy.text.LineNoise;
import org.openCage.stroy.text.ForXML;
import org.openCage.util.io.FileUtils;
import org.openCage.util.lang.V1;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

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

public class FuzzyHashGenXML implements FuzzyHashGenerator<File> {

    private final LineNoise    noise;
    private final Hash<String> hash;
    private final FuzzyHashSetFactory fuzzyHashSetFactory;

    @Inject
    public FuzzyHashGenXML( @ForXML final LineNoise javaNoise,
                            @ForXML final Hash<String> javaHash,
                            FuzzyHashSetFactory fuzzyHashSetFactory ) {
        this.noise = javaNoise;
        this.hash = javaHash;
        this.fuzzyHashSetFactory = fuzzyHashSetFactory;
    }


    public FuzzyHash generate( final File file ) {


        final Set<Integer> set = new HashSet<Integer>();

        FileUtils.withIterator( file, new V1<Iterable<String>>() {
            public void call( Iterable<String> iterable ) {
                for ( final String line :iterable ) {
                    if ( !noise.isGrayNoise( line )) {
                        set.add( hash.getHash( line ) );
                    }
                }
            }
        } );


        return fuzzyHashSetFactory.create( set );
    }

}