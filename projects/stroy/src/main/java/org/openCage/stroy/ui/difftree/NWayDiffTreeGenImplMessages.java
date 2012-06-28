package org.openCage.stroy.ui.difftree;

import org.openCage.stroy.ui.difftree.NWayDiffPane;
import org.openCage.stroy.ui.difftree.ShowChangeTreeCellRenderer;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.content.Content;
import com.google.inject.Inject;
import org.openCage.stroy.ui.popup.PopupSelectorFactory;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

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
public class NWayDiffTreeGenImplMessages<T extends Content> implements NWayDiffPaneGenerator<T> {

    private final ShowChangeTreeCellRenderer showChangeTreeCellRenderer;
    private PopupSelectorFactory<T> popupSelectorFactory;

    @Inject
    public NWayDiffTreeGenImplMessages( final PopupSelectorFactory<T> popupSelectorFactory, final ShowChangeTreeCellRenderer showChangeTreeCellRenderer ) {
        this.showChangeTreeCellRenderer  = showChangeTreeCellRenderer;
        this.popupSelectorFactory = popupSelectorFactory;
    }


    public NWayDiffPane getDiffPane(  final List<TreeMatchingTask<T>> tasks,
                                      final List<DefaultMutableTreeNode>        roots ) {
        return new NWayDiffPaneMessages<T>( tasks, roots, showChangeTreeCellRenderer, popupSelectorFactory );
    }
}
