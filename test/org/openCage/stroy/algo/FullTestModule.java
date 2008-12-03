package org.openCage.stroy.algo;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import org.openCage.stroy.algo.tree.TreeFactory;
import org.openCage.stroy.algo.tree.TreeFactoryImpl;
import org.openCage.stroy.algo.tree.NoedGenerator;
import org.openCage.stroy.algo.tree.filesystem.FileSystem;
import org.openCage.stroy.algo.tree.filesystem.FSNoedGenerator;
import org.openCage.stroy.algo.tree.zip.ZipArchive;
import org.openCage.stroy.algo.tree.zip.ZipNoedGenerator;
import org.openCage.stroy.algo.tree.singleFile.SingleFile;
import org.openCage.stroy.algo.tree.singleFile.SingleFileGenerator;
import org.openCage.stroy.algo.matching.TreeTaskFactory;
import org.openCage.stroy.algo.matching.TreeTaskFactoryImpl;
import org.openCage.stroy.algo.matching.strategies.TreeStrategy;
import org.openCage.stroy.algo.matching.strategies.base.StandardTreeMatching;
import org.openCage.stroy.algo.fingerprint.FingerPrint;
import org.openCage.stroy.algo.fingerprint.FileFingerPrint;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.filter.IgnoreByLists;

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

public class FullTestModule extends AbstractModule {
    protected void configure() {
        bind( TreeFactory.class ).
                to( TreeFactoryImpl.class );

        // NoedGenerator
        bind( NoedGenerator.class ).
                annotatedWith( SingleFile.class ).
                to( SingleFileGenerator.class );
        bind( NoedGenerator.class ).
                annotatedWith( ZipArchive.class ).
                to( ZipNoedGenerator.class );
        bind( NoedGenerator.class ).
                annotatedWith( FileSystem.class ).
                to( FSNoedGenerator.class );

        bind( Ignore.class).to( IgnoreByLists.class );

        bind( TreeTaskFactory.class ).to( TreeTaskFactoryImpl.class);

        bind( TreeStrategy.class ).to( StandardTreeMatching.class );

        // Fingerprint        
        bind( new TypeLiteral<FingerPrint<File>>(){} ).
                annotatedWith( FileSystem.class ).
                to( FileFingerPrint.class );
    }
}
