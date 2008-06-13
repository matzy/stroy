package org.openCage.util.prefs;

import java.util.Map;
import java.util.List;

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
public class GlobalItemMap implements PrefItem<Map<String,String>>{
    public Map<String, String> get() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void set(Map<String, String> val) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addListener(PreferencesChangeListener<Map<String, String>> preferencesChangeListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addPossible(String poss) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<String> getPossibles() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isGlobal() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setInitials(Map<String, String> ini) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
