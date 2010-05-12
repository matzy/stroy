package org.openCage.lang;

import org.openCage.lang.functions.F0;
import org.openCage.lang.functions.FV;

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

public interface BackgroundExecutor {

    /**
     * the task will run periodically and also at the end of the program
     * @param task
     */
    public void addPeriodicAndExitTask( FV task );

    /**
     * the task will be run every 10 seconds
     * te task should run fairly quick
     * @param task
     */
    public void addPeriodicTask( FV task );

    /**
     * the task will be executed at the end of the program
     * independent of how the program ends
     * @param task
     */
    public void addExitTask( FV task );

}
