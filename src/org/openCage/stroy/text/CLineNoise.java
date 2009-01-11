package org.openCage.stroy.text;

import java.util.regex.Pattern;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class CLineNoise implements LineNoise{

    private final Pattern pattern;

    @SuppressWarnings( {"HardCodedStringLiteral"} )
    public CLineNoise() {

        StringBuffer patternString = new StringBuffer( "( *\t*//.*)" );
        patternString.append( "|( *\t*/\\*.*)");
        patternString.append( "|( *\t*\\{ *\t*)");
        patternString.append( "|( *\t*\\} *\t*)");
        patternString.append( "|( *\t*\\*.*)");
        patternString.append( "|( *\t*\\@.*)");
        patternString.append( "|( *\t*)");
        patternString.append( "|( *\t*include.*)");

        pattern = Pattern.compile( patternString.toString() );
    }

    public boolean isGrayNoise(String text) {
        return pattern.matcher( text ).matches();
    }
}
