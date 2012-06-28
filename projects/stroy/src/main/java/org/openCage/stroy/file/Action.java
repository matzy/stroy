package org.openCage.stroy.file;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.comphy.*;
import org.openCage.lang.listeners.VoidListenerControl;
import org.openCage.lang.listeners.VoidListeners;

import static org.openCage.lang.inc.Strng.S;

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


public class Action implements Property{
    private String description;
    private SimilarityAlgorithm algo;
    private String diff;
    private String open;
    private VoidListeners observers = new VoidListeners();

    private static String NONE = "--none--";
//    public boolean file;
//    public boolean ignored;


    public Action( String description )  {
        this.algo = SimilarityAlgorithm.none;
        this.diff = NONE;
        this.open = NONE;
        this.description = description;
    }



    @Inject
    public Action( @Named(value = "description") String description,
                   @Named(value = "similarityAlgorithm") SimilarityAlgorithm algo,
                   @Named(value = "diff") String diff,
                   @Named(value = "open") String open ) {
//        file = true;
//        this.ignored = ignored;
        this.algo = algo;
        this.diff = diff;
        this.open = open;
        this.description = description;
    }

    @Override
    public org.openCage.comphy.Readable toReadable() {
        return Readables.Map().
                put( S("description"), description).
                put( S("similarityAlgorithm"), algo ).
                put( S("diff"), diff).
                put( S("open"), open);

    }

    @Override
    public VoidListenerControl getListenerControl() {
        return observers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        observers.shout();
    }

    public SimilarityAlgorithm getAlgo() {
        return algo;
    }

    public void setAlgo(SimilarityAlgorithm algo) {
        this.algo = algo;
        observers.shout();
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
        observers.shout();
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
        observers.shout();
    }

    public boolean hasOpen(String extension) {
        return !open.equals(NONE);
    }

    public boolean hasDiff(String extension) {
        return !diff.equals(NONE);
    }
}
