package org.openCage.stroy.dir;

import org.openCage.lang.functions.F0;
import org.openCage.lang.functions.F0E;
import org.openCage.lang.functions.F1;
import org.openCage.lang.structure.Lazy;
import org.openCage.lang.structure.Lazy1;
import org.openCage.util.io.FileUtils;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
import org.openCage.util.checksum.FullFileMD5;
import org.openCage.util.logging.Log;

import java.io.File;
import java.io.IOException;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/
public class FileContent implements Content {

    private final File                     file;
    private final Lazy<String> checksum;
    private final Lazy1<FuzzyHash, File> fuzzy;

    public FileContent( final F1<FuzzyHash, File> fuzzyGen, final File file ) {
        this.file     = file;
        this.checksum = new Lazy<String>( new F0E<String>() {
            public String callWithException() throws Exception{
                Log.finer( "computing fingerprint of " + file.getAbsolutePath() );
                return new FullFileMD5().getChecksum( file );
            }
        } );

        this.fuzzy = new Lazy1<FuzzyHash, File>( fuzzyGen );
    }

    public String getName() {
        return file.getName();
    }                              

    public String getChecksum() {
        return checksum.get();
    }

    public FuzzyHash getFuzzyHash() {
        return fuzzy.get( file );
    }

    public String getType() {
        return FileUtils.getExtension( file.getName() );
    }

    // lets refactor
    public String getLocation() {
        return file.getAbsolutePath();
    }


    public String toString() {
        return file.getPath();
    }

    public File getFile() {
        return file;
    }

}
