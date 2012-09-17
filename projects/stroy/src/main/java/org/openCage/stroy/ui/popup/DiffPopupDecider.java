package org.openCage.stroy.ui.popup;

import org.openCage.kleinod.io.FileUtils;
import org.openCage.kleinod.type.Null;
import org.openCage.lindwurm.LindenNode;
import org.openCage.lindwurm.content.Content;
import org.openCage.stroy.file.FileTypes;
import org.openCage.util.logging.Log;
import org.openCage.util.platform.Platform;

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
 * Methods to decide which menu items to show in the diff popup dialog
 */
public class DiffPopupDecider {

    private final FileTypes fileTypes;

    public DiffPopupDecider(FileTypes fileTypes) {
        this.fileTypes = fileTypes;
        if (Null.is(fileTypes)) {
            throw new IllegalArgumentException("oops");
        }
    }

    public boolean showOpen( LindenNode node ) {

        if ( node.isLeaf() ) {
            return node.getContent() != null && hasOpenApp( node );
        }

        if ( Platform.isBundle(( node.getContent()).getName())) {
            return hasOpenApp( node );
        }

        return false;
    }

    public boolean showOpenWith( LindenNode node ) {

        if ( node.isLeaf() ) {
            return node.getContent() != null && !hasOpenApp( node );
        }

        if ( Platform.isBundle(( (Content)node.getContent()).getName())) {
            return !hasOpenApp( node );
        }

        return false;
    }

    public boolean showOpenAsText( LindenNode node ) {
        return node.isLeaf() && node.getContent() != null;
    }

    private boolean hasOpenApp( LindenNode node ) {
        if ( node.getContent() == null ) {
            throw Log.log( new IllegalArgumentException( "node has no content" ));
        }

//        Object obj = node.getContent();
//        if ( !(obj instanceof Content)) {
//            int i = 0;
//        }
//        Content cnt = (Content)obj;
//        if ( cnt.getName() == null ) {
//            int i  = 0;
//        }
        return fileTypes.hasOpen( FileUtils.getExtension(((Content) node.getContent()).getName()));
    }


    public boolean showDiff( LindenNode node ) {
        boolean leaf  =  node.isLeaf();
        boolean content = node.getContent() != null;
        boolean diffType = fileTypes.hasDiffType( FileUtils.getExtension(((Content)node.getContent()).getName() ));

        return node.isLeaf() &&
               node.getContent() != null &&
               fileTypes.hasDiffType( FileUtils.getExtension(((Content)node.getContent()).getName() ));
    }

    public boolean showDiffWith( LindenNode node ) {
        return node.isLeaf() &&
               node.getContent() != null &&
               !fileTypes.hasDiffType( FileUtils.getExtension(((Content)node.getContent()).getName() ));
    }
}
