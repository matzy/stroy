package org.openCage.generj;

import org.openCage.lang.Strings;

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
public class Enm implements ClassI {
    private String name;
    private List<String> vals = new ArrayList<String>();
    private BlockComment comment;
    private String pack;

    public Enm( String pack, String name, String ... vals  ) {
        this.pack = pack;
        this.name = name;
        this.vals.addAll( Arrays.asList( vals ));
    }

    public Enm( String pack, String name, List<String> vals) {
        this.pack = pack;
        this.name = name;
        this.vals.addAll( vals );
    }

    @Override
    public ClassI comment(BlockComment comment) {
        this.comment = comment;
        return this;
    }

    public String toString( String prefix ) {
        String ret = "";

        if ( pack != null ) {
            ret += "package " + pack + ";\n";
        }

        if ( comment != null ) {
            ret += comment.toString( prefix );
        }
        ret += prefix + "public enum " + name + " {";

        ret += Strings.join( vals );

        ret += "}\n";


        return ret;
    }

    public String toString() {
        return toString("");
    }
}
