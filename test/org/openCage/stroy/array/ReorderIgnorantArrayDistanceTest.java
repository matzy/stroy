package org.openCage.stroy.array;

import org.jmock.Mock;
import org.jmock.MockObjectTestCase;
import org.jmock.core.Constraint;

import java.util.Arrays;
import java.util.List;

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

@SuppressWarnings({"HardCodedStringLiteral"})
public class ReorderIgnorantArrayDistanceTest extends MockObjectTestCase {

    private ListChangeMetric                      metric;
    private Mock                                  metricMock;
    private ReorderIgnorantArrayDistance<Integer> dist;
    private List<Integer>                         from = Arrays.asList( 1,2,3,4,5 );


    protected void setUp() throws Exception {
        super.setUp();
        metricMock = mock( ListChangeMetric.class );
        metric = (ListChangeMetric) metricMock.proxy();
        dist = new ReorderIgnorantArrayDistance<Integer>( metric );
    }


    public void testSame() {

        Constraint args[] = {eq(5), eq(5), eq( 5 ), eq(0), eq(0)};
        metricMock.expects( once() ).method( "measure" ).with( args )
                .will( returnValue( 0.0 ));
        dist.distance(from,from);
    }

    public void testReordered() {

        List<Integer> to = Arrays.asList( 4,1,3,2,5 );

        Constraint args[] = {eq(5), eq(5), eq( 5 ), eq(0), eq(0)};
        metricMock.expects( once() ).method( "measure" ).with( args )
                .will( returnValue( 0.0 ));
        dist.distance(from,to);

    }

    public void testDeleted() {

        List<Integer> to = Arrays.asList( 1,2,3,5 );

        Constraint args[] = {eq(5), eq(4), eq( 4 ), eq(1), eq(0)};
        metricMock.expects( once() ).method( "measure" ).with( args )
                .will( returnValue( 0.0 ));
        dist.distance(from,to);

    }
}
