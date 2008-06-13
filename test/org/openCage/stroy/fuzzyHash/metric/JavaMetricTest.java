package org.openCage.stroy.fuzzyHash.metric;

import junit.framework.TestCase;
import com.google.inject.Injector;
import com.google.inject.Guice;
import org.openCage.stroy.RuntimeModule;

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

public class JavaMetricTest extends TestCase {

    private CountChangeMetric metric;

    protected void setUp() throws Exception {
        super.setUp();

        final Injector injector = Guice.createInjector( new RuntimeModule() );

        metric = injector.getInstance( CountChangeMetric.class );
    }
    


    public void testEqual() {
        assertEquals( 0.0, metric.distance( 100, 100, 100 ));
    }

    public void testAddMethodToBigClass() {
        assertTrue( metric.distance( 200, 200, 220 ) < 0.1);
    }

    public void testAddMethodToSmallClass() {
        assertTrue( metric.distance( 50, 50, 70 ) < 0.1);
    }

    public void testDelMethodfromBigClass() {
        assertTrue( metric.distance( 200, 180, 180 ) < 0.1);
    }

    public void testDelMethodFromSmall() {
        assertTrue( metric.distance( 50, 30, 30 ) < 0.1);
    }

    public void testAddDelFromBigClass() {
        // e.g. refactor
        assertTrue( metric.distance( 200, 180, 200 ) < 0.1);
    }

    public void testAddDelFromSmall() {
        // e.g. refactor
        assertTrue( metric.distance( 50, 30, 50 ) < 0.1);
    }


    public void testUnrelated() {
        assertTrue( metric.distance( 100, 30, 100 ) > 0.3);
    }

    public void testTargetZero() {
        assertTrue( metric.distance( 2, 0, 0 ) > 0.3);
    }

    public void testSourceZero() {
        assertTrue( metric.distance( 0, 0, 10 ) > 0.3);
    }

    public void testSourceAndTargetZero() {
        assertTrue( metric.distance( 0, 0, 0 ) < 1.0 );
        assertTrue( metric.distance( 0, 0, 0 ) > 0.4 );
    }
}
