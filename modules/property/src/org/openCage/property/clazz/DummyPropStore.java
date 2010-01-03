package org.openCage.property.clazz;

import org.jetbrains.annotations.NotNull;
import org.openCage.property.protocol.PropStore;
import org.openCage.property.protocol.Property;

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

/**
 * A propstore implementation ignoring all
 * Props still work but are not peristet
 */
public class DummyPropStore implements PropStore {

    @Override
    public Property get(@NotNull String key) {
        return null;
    }

    @Override
    public void setProperty(@NotNull String key, @NotNull Property prop) {
    }

    @Override
    public void setDirty() {
    }
}
