package org.openCage.property;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openCage.lang.Lazy;
import org.openCage.lang.Tic;
import org.openCage.lang.functions.F0;

import static junit.framework.Assert.assertEquals;

public class NonPersiTicTest {

    @BeforeClass
    public static void setup() {
        Tic.bindSingleton( Property.class, "aaa", new F0<Property>() {
            @Override
            public Property<String> call() {
                return new SimpleProp("dflt");
            }
        });

//        Tic.bindSingleton( PropStore.class, new F0<PropStore>() {
//            @Override
//            public PropStore call() {
//                return new NonPersistingPropStore();
//            }
//        });
    }

    @Test
    public void test() {
        Property<String> prop = Tic.get( Property.class, "aaa"  );
        Property<String> prop2 = Tic.get( Property.class, "aaa"  );

        assertEquals( prop, prop2 );

    }

    @Test( expected = ClassCastException.class )
    public void test2() {
        Property<Integer> prop = Tic.get( Property.class, "aaa"  );

        Integer ii = prop.get();

    }
}
