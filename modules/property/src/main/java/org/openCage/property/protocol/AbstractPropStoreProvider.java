package org.openCage.property.protocol;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.lang.SingletonApp;
import org.openCage.property.PropStore;
import org.openCage.property.PropStoreImpl;

import java.io.File;
import java.util.Map;

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

public abstract class AbstractPropStoreProvider implements Provider<PropStore> {

    @Inject private BackgroundExecutor executor;
    private SingletonApp               singletonApp;
    private final File                 backing;
    private final Map<String, Class>   aliases;

    public AbstractPropStoreProvider( File backing, SingletonApp singletonApp, Map<String, Class> aliases ) {
        this.backing = backing;
        this.aliases = aliases;
        this.singletonApp = singletonApp;
    }

    @Override
    public PropStore get() {
        return new PropStoreImpl( executor, backing, aliases, singletonApp );
    }
}
