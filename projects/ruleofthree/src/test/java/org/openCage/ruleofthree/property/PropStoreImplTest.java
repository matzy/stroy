package org.openCage.ruleofthree.property;

import org.junit.Test;
import org.openCage.lang.structure.Ref;
import org.openCage.ruleofthree.Three;
import org.openCage.ruleofthree.ThreeHashMap;
import org.openCage.ruleofthree.ThreeKey;
import org.openCage.ruleofthree.ThreeMap;

import static junit.framework.Assert.assertEquals;
import static org.openCage.ruleofthree.Threes.THREE;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/6/12
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropStoreImplTest {

    @Test
    public void noUnnecessaryDirties() {

        final Ref<String> dirty = new Ref<String>("clean");

        PropertyStore ps = new PropStoreImpl( new PropertyStoreRW() {
            @Override
            public void setDirty() {
                dirty.set("dirty");
            }

            @Override
            public ThreeMap<Three> getThreedProps() {
                ThreeMap<Three> three = new ThreeHashMap<Three>();
                three.put( new ThreeKey("key"), THREE("val"));
                return three;
            }

            @Override
            public ThreeMap<Object> getProps() {
                return new ThreeHashMap<Object>();
            }
        });

        ps.get( new ThreeKey("key"), String.class );

        assertEquals( "clean", dirty.get());
    }

    @Test
    public void noDirtyOnAdd() {

        final Ref<String> dirty = new Ref<String>("clean");

        PropertyStore ps = new PropStoreImpl( new PropertyStoreRW() {
            @Override
            public void setDirty() {
                dirty.set("dirty");
            }

            @Override
            public ThreeMap<Three> getThreedProps() {
                ThreeMap<Three> three = new ThreeHashMap<Three>();
                return three;
            }

            @Override
            public ThreeMap<Object> getProps() {
                return new ThreeHashMap<Object>();
            }
        });

        ps.add( new ThreeKey("key"), "val");

        assertEquals("dirty", dirty.get());
    }

}
