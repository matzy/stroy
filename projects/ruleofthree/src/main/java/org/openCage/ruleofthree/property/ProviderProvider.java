package org.openCage.ruleofthree.property;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import org.openCage.ruleofthree.ThreeKey;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/9/12
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProviderProvider {

    public static abstract class PropProp<T> implements Provider<T> {
        @Inject
        public PropertyStore store;
    }

    public static <T> Provider<T> getProvider(final TypeLiteral<T> literal, final ThreeKey key, final T deflt) {
        return  new PropProp<T>() {

            @Override
            public T get() {
                T ret = (T) store.get( key, literal );

                if (ret == null) {
                    ret = deflt;
                    if ( ret == null ) {
                        throw new IllegalArgumentException("default value of property must not be null " + literal);
                    }
                    store.add( key, ret);
                }

                return ret;
            }
        };
    }

}
