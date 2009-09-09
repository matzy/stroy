package org.openCage.util.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.Date;
import java.text.SimpleDateFormat;

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

public class SimpleFormatter extends Formatter {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd HH.mm.ss.SSS" );

    public String format(LogRecord logRecord) {

        Date date = new Date( logRecord.getMillis() );
        return /*logRecord.getLevel().toString() + "  " + */dateFormat.format( date ) + ":  " +logRecord.getMessage() + "\n";
    }
}
