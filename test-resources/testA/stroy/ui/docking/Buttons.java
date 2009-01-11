package org.openCage.stroy.ui.docking;

import zappini.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1/GPL 2.0
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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU General Public License Version 2 or later (the "GPL"),
* in which case the provisions of the GPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL, and not to allow others to
* use your version of this file under the terms of the MPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the MPL, the GPL.
*
***** END LICENSE BLOCK *****/

public class Buttons extends JPanel {

    private final GraphicalDiffMyDoggy graphialDiff;
    private final JCheckBox diffMerge = new JCheckBox( "merge" );
    private final JCheckBox matchSelection = new JCheckBox( "match selection" );

    public Buttons( GraphicalDiffMyDoggy graphialDiff ) {
        this.graphialDiff = graphialDiff;
        createLayout();
        addListeners();
    }

    private void addListeners() {
        diffMerge.addItemListener( new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                graphialDiff.setMerging( itemEvent.getStateChange() == ItemEvent.SELECTED );
            }
        });

        matchSelection.setSelected( true );
        matchSelection.addItemListener( new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                graphialDiff.setMatchSelection( itemEvent.getStateChange() == ItemEvent.SELECTED );
            }
        });
    }

    private void createLayout() {


        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        layout.row().add( diffMerge ).add( matchSelection );

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER  );
    }
}
