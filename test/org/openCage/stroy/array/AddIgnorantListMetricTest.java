package org.openCage.stroy.array;

import junit.framework.TestCase;

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

public class AddIgnorantListMetricTest extends TestCase {

    private AddIngnorantListMetric metric;

    protected void setUp() throws Exception {
        super.setUp();
        metric = new AddIngnorantListMetric();
    }

    public void testSame() throws Exception {
        assertEquals( 0.0, metric.measure( 100, 100, 100, 0, 0));
    }

    public void testOnlyNewStuff() throws Exception {
        assertEquals( 0.0, metric.measure( 100, 200, 100, 0, 100));
    }

    public void testFewDeleted() throws Exception {
        assertTrue( 0.2 > metric.measure( 100, 90, 90, 10, 0));
    }

    public void testSmallFileToBig() throws Exception {
        assertTrue( 0.2 > metric.measure( 10, 100, 10, 90, 0));
    }
}
