package org.openCage.util.prefs;

import java.util.*;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/
public class ProjectItemLists implements PrefItem<ArrayList<String>> {

    private final Map<String, ArrayList<String>> vals = new HashMap<String, ArrayList<String>>();
    private ArrayList<String>                    initial;
    private List<String>              possibles = new ArrayList<String>();


    private transient List<PreferencesChangeListener> listeners;


    ProjectItemLists( ) {
    }

    public ArrayList<String> get() {
        ArrayList<String> ret = vals.get( Prefs.getCurrentProject());

        if ( ret == null ) {
            if ( initial == null ) {
                return new ArrayList<String>();
            }

            set( initial );
        }

        return vals.get( Prefs.getCurrentProject());
    }


    public void set( ArrayList<String> val ) {
        String project = Prefs.getCurrentProject();

        if ( val.equals( vals.get( project ))) {
            return;
        }

//        if ( possibles.size() > 0 ) {
//            if ( ! possibles.contains( val )) {
//                throw new IllegalArgumentException( "not in the list of possibles: " + val );
//            }
//        }

        vals.put( project, val );

        checkTransients();
        for ( PreferencesChangeListener listr : listeners ) {
            listr.changed( val );
        }

        Prefs.setDirty();
    }


    public void addListener( PreferencesChangeListener<ArrayList<String>> listener ) {
        checkTransients();
        listeners.add( listener );
    }

    public void projectChanged( String project ) {

        ArrayList<String> val = vals.get( project );

        checkTransients();
        for ( PreferencesChangeListener listr : listeners ) {
            listr.changed( val );
        }

    }

    public void addPossible(String poss) {
        possibles.add( poss );
    }

    public List<String> getPossibles() {
        return possibles;
    }

    public boolean isGlobal() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setInitials(ArrayList<String> ini) {
        if ( initial == null ) {
            initial = ini;
            Prefs.setDirty();
        }
    }

    private void checkTransients() {
        if ( listeners == null ) {
            listeners = new ArrayList<PreferencesChangeListener>();
        }
    }



}
