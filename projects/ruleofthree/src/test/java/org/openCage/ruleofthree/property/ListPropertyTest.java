package org.openCage.ruleofthree.property;

import org.junit.Test;
import org.openCage.lang.functions.VF0;
import org.openCage.lang.iterators.Count;
import org.openCage.lang.structure.Ref;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/3/12
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListPropertyTest {

    @Test
    public void add() {
        ListProperty<String> list = new ListProperty<String>();
        final Ref<Integer> i = new Ref<Integer>(0);

        list.getListenerControl().add( new VF0() {
            @Override
            public void call() {
                i.set(7);
            }
        });

        list.add( "foo" );

        assertEquals( 7, i.get().intValue());
    }
}
