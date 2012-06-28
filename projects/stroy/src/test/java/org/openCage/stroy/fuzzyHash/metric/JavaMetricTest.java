package org.openCage.stroy.fuzzyHash.metric;

import junit.framework.TestCase;
import com.google.inject.Injector;
import com.google.inject.Guice;
import org.openCage.stroy.RuntimeModule;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
