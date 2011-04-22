package org.openCage.ui.impl;

import com.google.inject.Inject;

import java.util.Locale;

import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.openCage.localization.CombinedLocalize;
import org.openCage.localization.DictLocalize;
import org.openCage.localization.Localize;
import org.openCage.localization.LocalizeFactory;
import org.openCage.localization.impl.LocalePropertyProvider;
import org.openCage.property.Property;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class UILocalizeProvider implements Provider<Localize> {

    private LocalizeFactory factory;
    private DictLocalize dict;

    @Inject
    public UILocalizeProvider( LocalizeFactory factory, DictLocalize dict  ) {
        this.factory = factory;
        this.dict = dict;
    }


    @Override
    public Localize get() {
        return factory.get( "org.openCage.ui.uitexts", dict.get() );
    }
}
