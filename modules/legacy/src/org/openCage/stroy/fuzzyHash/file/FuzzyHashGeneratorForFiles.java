package org.openCage.stroy.fuzzyHash.file;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.lang.protocol.HasDistance;
import org.openCage.stroy.fuzzyHash.FuzzyHashGenerator;
import org.openCage.stroy.fuzzyHash.FuzzyHashNever;
import org.openCage.stroy.file.FileTypes;

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

public class FuzzyHashGeneratorForFiles implements FuzzyHashGenerator<File> {

    static private final Logger LOG = Logger.getLogger(  FuzzyHashGeneratorForFiles.class.getName());

    private final FuzzyHashGenerator<File> javaHashGen;
    private final FuzzyHashGenerator<File> textHashGen;
    private final FuzzyHashGenerator<File> cHashGen;
    private final FuzzyHashGenerator<File> xmlHashGen;

//    private final FileDistanceBroker broker = new FileDistanceBroker();

    @Inject
    public FuzzyHashGeneratorForFiles(
                         @Named( "java" ) final FuzzyHashGenerator<File> javaHashGen,
                         @Named( "text" ) final FuzzyHashGenerator<File> textHashGen,
                         @Named( "c" ) final FuzzyHashGenerator<File> cHashGen,
                         @Named( "xml" ) final FuzzyHashGenerator<File> xmlHashGen) {
        this.javaHashGen = javaHashGen;
        this.textHashGen = textHashGen;
        this.cHashGen    = cHashGen;
        this.xmlHashGen  = xmlHashGen;

    }


    public HasDistance generate( final File file ) {
        LOG.finer( "fuzzy hash gen for: " + file.getAbsolutePath() );

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
