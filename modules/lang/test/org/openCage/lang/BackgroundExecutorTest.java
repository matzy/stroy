package org.openCage.lang;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.lang.mutable.MutableLong;
import org.junit.Test;
import org.openCage.lang.protocol.BackgroundExecutor;
import org.openCage.lang.protocol.FE0;
import org.openCage.lang.protocol.LangWiring;

import static junit.framework.Assert.assertTrue;

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


public class BackgroundExecutorTest {

    @Test
    public void testPeriodic() throws InterruptedException {
        Injector injector = Guice.createInjector( new LangWiring() );
        BackgroundExecutor bg = injector.getInstance( BackgroundExecutor.class );

        final MutableLong count = new MutableLong(0);

        bg.addPeriodicTask( new FE0<Void>() {
            public Void call() throws Exception {
                count.increment();
                return null;
            }
        });

        Thread.sleep( 25000 );

        synchronized ( count ) {
            System.out.println( count.intValue());
            assertTrue( count.intValue() > 1 );
        }

    }
}
