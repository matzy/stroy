package org.openCage.stroy.file;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.kleinod.observe.VoidListenerControl;
import org.openCage.kleinod.observe.VoidListeners;
import org.openCage.ruleofthree.Property;
import org.openCage.ruleofthree.Three;
import org.openCage.ruleofthree.Threes;

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


public class Action implements Property {
    private String description;
    private SimilarityAlgorithm algo;
    private String diff;
    private String open;

    private boolean isText;
    private boolean isXML;
    private boolean isJSON;
    private boolean isBundle;

    private VoidListeners observers = new VoidListeners();

    private static String NONE = "--none--";


    public Action( String description )  {
        this.algo = SimilarityAlgorithm.none;
        this.diff = NONE;
        this.open = NONE;
        this.description = description;
    }



    @Inject
    public Action( @Named("description") String description,
                   @Named("similarityAlgorithm") SimilarityAlgorithm algo,
                   @Named("diff") String diff,
                   @Named("open") String open,
                   @Named("isText") boolean isText,
                   @Named("isXML")  boolean isXML ,
                   @Named("isJSON") boolean isJSON ) {
        this.algo = algo;
        this.diff = diff;
        this.open = open;
        this.description = description;
        this.isText = isText;
        this.isXML = isXML;
        this.isJSON = isJSON;
    }

    public Action( String description,
                   SimilarityAlgorithm algo,
                   String diff,
                   String open,
                   boolean isText ) {
        this.algo = algo;
        this.diff = diff;
        this.open = open;
        this.description = description;
        this.isText = isText;
    }

    @Override
    public Three toThree() {
        Three ret = Threes.newMap().
                put( ("description"), description).
                put( ("similarityAlgorithm"), algo ).
                put( ("diff"), diff).
                put( ("open"), open);

        if ( isText ) {
            ret.put( "isText", true );
        }
        if ( isXML ) {
            ret.put( "isXML", true );
        }
        if ( isJSON ) {
            ret.put( "isJSON", true );
        }

        return ret;

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

    public boolean getIsText() {
        return isText;
    }

    public void setIsText(Boolean text) {
        isText = text;
    }

    public boolean getIsXML() {
        return isXML;
    }

    public void setIsXML(Boolean XML) {
        isXML = XML;
    }

    public boolean getIsJSON() {
        return isJSON;
    }

    public void setIsJSON(Boolean JSON) {
        isJSON = JSON;
    }

    public boolean getIsBundle() {
        return isBundle;
    }

    public void setIsBundle(Boolean bundle) {
        isBundle = bundle;
    }

}
