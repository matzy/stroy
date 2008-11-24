package org.openCage.stroy.fuzzyHash.file;

import com.google.inject.Inject;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
import org.openCage.stroy.fuzzyHash.FuzzyHashGenerator;
import org.openCage.stroy.fuzzyHash.FuzzyHashNever;
import org.openCage.stroy.text.ForJava;
import org.openCage.stroy.text.ForText;
import org.openCage.stroy.text.ForC;
import org.openCage.stroy.text.ForXML;
import org.openCage.stroy.file.FileTypes;
import org.openCage.util.logging.Log;

import java.io.File;

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

public class FuzzyHashGeneratorForFiles implements FuzzyHashGenerator<File> {

    private final FuzzyHashGenerator<File> javaHashGen;
    private final FuzzyHashGenerator<File> textHashGen;
    private final FuzzyHashGenerator<File> cHashGen;
    private final FuzzyHashGenerator<File> xmlHashGen;

//    private final FileDistanceBroker broker = new FileDistanceBroker();

    @Inject
    public FuzzyHashGeneratorForFiles(
                         @ForJava final FuzzyHashGenerator<File> javaHashGen,
                         @ForText final FuzzyHashGenerator<File> textHashGen,
                         @ForC final FuzzyHashGenerator<File> cHashGen,
                         @ForXML final FuzzyHashGenerator<File> xmlHashGen) {
        this.javaHashGen = javaHashGen;
        this.textHashGen = textHashGen;
        this.cHashGen    = cHashGen;
        this.xmlHashGen  = xmlHashGen;

    }


    public FuzzyHash generate( final File file ) {
        Log.finer( "fuzzy hash gen for: " + file.getAbsolutePath() );

//        final String ext = FileUtils.getExtension( file );
//
//        if ( !broker.contains( ext )) {
//            Log.warning( "unknown extension for FuzzyHashGeneration " + ext);
//
//            return new FuzzyHashNever();
//        }

        switch( FileTypes.create().getSimilarityAlgorithm( file.getName() )) {
            case java:
                return javaHashGen.generate( file );

            case text:
                return textHashGen.generate( file );

            case c:
                return cHashGen.generate( file );

            case xml:
                return xmlHashGen.generate( file );

            default:
                return new FuzzyHashNever();
        }
    }
}
