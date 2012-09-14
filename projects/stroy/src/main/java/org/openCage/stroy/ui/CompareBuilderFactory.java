package org.openCage.stroy.ui;

import com.google.inject.Inject;
import org.openCage.stroy.filter.IgnoreCentral;
import org.openCage.stroy.graph.matching.strategy.combined.WatchFull;
import org.openCage.stroy.ui.difftree.NWayDiffPaneGenerator;
import org.openCage.stroy.ui.menu.PortableMenuFactory;

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

public class CompareBuilderFactory  {

    private final WatchFull watchFull;
    private final NWayDiffPaneGenerator diffPaneGen;
    private final PortableMenuFactory menuFactory;
    private final IgnoreCentral ignoreCentral;


    @Inject
    public CompareBuilderFactory(final PortableMenuFactory menuFactory,
                                 final NWayDiffPaneGenerator diffPaneGen,
                                 final WatchFull strat,
                                 final IgnoreCentral ignoreCentral) {
        this.watchFull = strat;
        this.diffPaneGen = diffPaneGen;
        this.menuFactory = menuFactory;
        this.ignoreCentral = ignoreCentral;
    }

    public CompareBuilder get( String ... dirs ) {
        return new CompareBuilder( menuFactory, diffPaneGen, watchFull, ignoreCentral, dirs );
    }
}
