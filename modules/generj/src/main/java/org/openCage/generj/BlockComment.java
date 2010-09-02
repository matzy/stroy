package org.openCage.generj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * <p/>
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * <p/>
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * <p/>
 * The Original Code is stroy code
 * <p/>
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 * <p/>
 * Contributor(s):
 * **** END LICENSE BLOCK ****
*/
public class BlockComment implements Statement{
    private List<String> lines = new ArrayList<String>();

    public BlockComment( String ... lines) {
        this.lines.addAll( Arrays.asList(lines));
    }

    public BlockComment(List<String> comments) {
        this.lines.addAll( comments );
    }


    @Override
    public String toString(String prefix) {
        String ret = prefix + "/**\n";

        for ( String line : lines ) {
            ret += prefix + " * " + line + "\n";
        }

        ret += prefix + "*/\n";


        return ret;
    }

    public String toString() {
        return toString("");
    }
}
