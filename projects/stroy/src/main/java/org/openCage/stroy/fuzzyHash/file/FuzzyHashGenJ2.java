package org.openCage.stroy.fuzzyHash.file;

import com.google.inject.Inject;
import org.openCage.lang.Forall;
import org.openCage.lang.functions.F1;
import org.openCage.lang.iterators.Iterators;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
import org.openCage.stroy.algo.hash.HashDecider;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.file.FileTypes;
import org.openCage.stroy.fuzzyHash.FuzzyHashGenerator;
import org.openCage.stroy.fuzzyHash.FuzzyHashNever;
import org.openCage.stroy.fuzzyHash.FuzzyHashSetFactory;
import org.openCage.stroy.text.LineNoiseDecider;

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

public class FuzzyHashGenJ2 implements FuzzyHashGenerator<Content> {

    private final FuzzyHashSetFactory fuzzyHashSetFactory;
    private final LineNoiseDecider lineNoiseDecider;
    private final HashDecider hashDecider;
    private final FileTypes fileTypes;

    @Inject
    public FuzzyHashGenJ2(FuzzyHashSetFactory fuzzyHashSetFactory, LineNoiseDecider lineNoiseDecider, HashDecider hashDecider, FileTypes fileTypes) {
        this.fuzzyHashSetFactory = fuzzyHashSetFactory;
        this.lineNoiseDecider = lineNoiseDecider;
        this.hashDecider = hashDecider;
        this.fileTypes = fileTypes;
    }

    @Override
    public FuzzyHash call(Content content) {

        if ( isText( content.getType() )) {
            return fuzzyHashSetFactory.create(
                    Forall.lines(content.getStream()).
                            skip(lineNoiseDecider.get(content)).
                            trans(hashDecider.get(content)).
                            toSet());
        } else {
            return new FuzzyHashNever();
        }

    }

    private boolean isText(String type) {
        switch ( fileTypes.getAlgo( type ) ) {
            case c:
            case java:
            case text:
            case xml:
                return false;
            default:
                return false;
        }
    }
}
