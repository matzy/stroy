package org.openCage.stroy.algo.tree.filesystem;

import org.openCage.lang.functions.F0;
import org.openCage.lang.structure.Lazy;
import org.openCage.stroy.algo.tree.Fiel;
import org.openCage.stroy.algo.tree.IOStateImpl;
import org.openCage.stroy.algo.tree.IOState;
import org.openCage.stroy.algo.fingerprint.FingerPrint;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
import org.openCage.util.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;

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

/**
 * A standard file system file as seen through fiel eyes
 */
public class FSFiel implements Fiel {
    private final File file;
    private IOState ioState = new IOStateImpl();

    private String           type;
    private Lazy<FuzzyHash> fuzzy;

    private final Lazy<String> calcChecksum;

    public FSFiel( @NotNull final File file, @NotNull final FingerPrint<File> calc ) {
        this.file = file;
        type = FileUtils.getExtension( file );

        calcChecksum = new Lazy<String>( new F0<String>() {
            public String call() {
                return calc.getFingerprint( file, ioState );
            }
        } );

        fuzzy = new Lazy<FuzzyHash>( new F0<FuzzyHash>() {
            public FuzzyHash call() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        } );


    }

    public String getFingerprint() {
        return calcChecksum.get();
    }

    public String getType() {
        return type;
    }

    public FuzzyHash getFuzzyHash() {
        return fuzzy.get();
    }

    public long getSize() {
        return file.length();
    }

    public boolean hasReadError() {
        return ioState.isError();
    }
}
