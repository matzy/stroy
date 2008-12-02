package org.openCage.stroy.fuzzyHash.file;

import com.google.inject.Inject;
import org.openCage.util.iterator.Iterators;
import org.openCage.stroy.Hash;
import org.openCage.stroy.fuzzyHash.FuzzyHash;
import org.openCage.stroy.fuzzyHash.FuzzyHashGenerator;
import org.openCage.stroy.fuzzyHash.FuzzyHashSetFactory;
import org.openCage.stroy.text.ForJava;
import org.openCage.stroy.text.LineNoise;
import org.openCage.util.logging.Log;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

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

public class FuzzyHashGenJava implements FuzzyHashGenerator<File> {

    private final LineNoise    noise;
    private final Hash<String> hash;
    private final FuzzyHashSetFactory fuzzyHashSetFactory;

    @Inject
    public FuzzyHashGenJava( @ForJava final LineNoise javaNoise,
                             @ForJava final Hash<String> javaHash,
                             FuzzyHashSetFactory fuzzyHashSetFactory ) {
        this.noise = javaNoise;
        this.hash = javaHash;
        this.fuzzyHashSetFactory = fuzzyHashSetFactory;
    }


    public FuzzyHash generate( final File file ) {


            final Set<Integer> set = new HashSet<Integer>();

            try {
                for ( final String line : Iterators.lines( file ) ) {
                    if ( !noise.isGrayNoise( line )) {
                        set.add( hash.getHash( line ) );
                    }
                }
            } catch ( Exception exp ) {
                Log.warning( "can't read file: " + file.getAbsolutePath() );
            }

        return fuzzyHashSetFactory.create( set );
    }

}
