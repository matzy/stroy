package org.openCage.stroy.tree.str;

import org.openCage.stroy.algo.tree.Fiel;
import org.openCage.stroy.fuzzyHash.FuzzyHash;

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

public class StringFiel implements Fiel {
    private String content;
    private String type;

    public StringFiel( String typ, String content ) {
        this.type = typ;
        this.content = content;
    }

    public String getChecksum() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getType() {
        return type;
    }

    public FuzzyHash getFuzzyHash() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long getSize() {
        return content.length();
    }

    public boolean hasReadError() {
        return false;
    }
}
