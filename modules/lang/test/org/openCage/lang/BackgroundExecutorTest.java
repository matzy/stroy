package org.openCage.lang;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.lang.mutable.MutableLong;
import org.junit.Test;
import org.openCage.lang.protocol.BackgroundExecutor;
import org.openCage.lang.protocol.FE0;
import org.openCage.lang.protocol.LangWiring;

import static junit.framework.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 1, 2009
 * Time: 3:05:33 PM
 * To change this template use File | Settings | File Templates.
 */


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
