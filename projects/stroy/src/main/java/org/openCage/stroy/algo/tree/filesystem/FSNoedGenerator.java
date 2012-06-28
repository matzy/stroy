package org.openCage.stroy.algo.tree.filesystem;

import org.openCage.stroy.algo.tree.NoedGenerator;
import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.algo.tree.NoedImpl;
import org.openCage.stroy.algo.fingerprint.FingerPrint;
import org.openCage.stroy.filter.Ignore;
import org.jetbrains.annotations.NotNull;

import java.io.File;

import com.google.inject.Inject;

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

public class FSNoedGenerator implements NoedGenerator {

    private final Ignore            ignore;
    private final FingerPrint<File> checksum;

    @Inject
    public FSNoedGenerator( @NotNull             final Ignore            ignore,
                            @NotNull @FileSystem final FingerPrint<File> checksum ) {
        this.ignore   = ignore;
        this.checksum = checksum;
    }

    public Noed build( @NotNull String path ) {

        return build( ignore, new File( path ) );
    }


    private Noed build( Ignore ignore, File file ) {

        if ( ignore.match( file.getPath() )) {
            return null;
        }

        if ( !file.isDirectory()) {
            return NoedImpl.makeLeafNoed( file.getName(), new FSFiel( file, checksum ));
        }

        Noed parent = NoedImpl.makeDirNoed( file.getName() );

        for ( File child : file.listFiles() ) {
            Noed noed = build( ignore, child );
            if ( noed != null ) {
                parent.addChild( noed );
            }
        }

        return parent;
    }
}
