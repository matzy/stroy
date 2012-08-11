package org.openCage.ruleofthree.property;

import com.google.inject.*;
import org.junit.Test;
import org.openCage.lang.structure.ObservableRef;
import org.openCage.ruleofthree.ThreeKey;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
* Created with IntelliJ IDEA.
* User: stephan
* Date: 8/9/12
* Time: 8:15 AM
* To change this template use File | Settings | File Templates.
*/
public class InjectTest {

    @Test
    public void obrefString() {
        Injector inj = Guice.createInjector( new Module() {
            @Override
            public void configure(Binder binder) {

                binder.bind(PropertyStore.class ).toInstance(new PropertyStore() {
                    @Override
                    public void add(ThreeKey key, Object prop) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public <T> T get(ThreeKey key, Class<T> clazz) {
                        return null;  //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public <T> T get(ThreeKey key, TypeLiteral<T> typeLiteral) {
                        return (T) new ObservableRef<String>("duh");
                    }

                    @Override
                    public <T> List<T> getAll(Class<T> clazz) {
                        return null;  //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public <T> List<T> getAll(TypeLiteral<T> literal) {
                        return null;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                });

//                binder.bind( new TypeLiteral<ObservableRef<String>>(){} ).toProvider(
//                        new ProviderProvider().getProvider(new TypeLiteral<ObservableRef<String>>() {},
//                                new TypeLiteral<ObservableRef<String>>() {},
//                                ThreeKey.valueOf("duh"),
//                                new ObservableRef<String>("foo")));
                binder.bind( new TypeLiteral<ObservableRef<String>>(){} ).toProvider(
                        ProviderProvider.getProvider(
                                new TypeLiteral<ObservableRef<String>>() {},
                                ThreeKey.valueOf("duh"),
                                new ObservableRef<String>("foo")));
            }
        });

        assertEquals( "duh", inj.getInstance( new TypeLiteral<ObservableRef<String>>(){}).get());
    }

    @Test
    public void obrefStringAdd() {
        Injector inj = Guice.createInjector( new Module() {
            @Override
            public void configure(Binder binder) {


                binder.bind(PropertyStore.class ).toInstance(new PropertyStore() {
                    private Object prop;

                    @Override
                    public void add(ThreeKey key, Object prop) {
                        this.prop = prop;
                    }

                    @Override
                    public <T> T get(ThreeKey key, Class<T> clazz) {
                        return null;  //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public <T> T get(ThreeKey key, TypeLiteral<T> typeLiteral) {
                        return (T) prop;
                    }

                    @Override
                    public <T> List<T> getAll(Class<T> clazz) {
                        return null;  //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public <T> List<T> getAll(TypeLiteral<T> literal) {
                        return null;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                });

//                binder.bind( new TypeLiteral<ObservableRef<String>>(){} ).toProvider(
//                        new ProviderProvider().getProvider(new TypeLiteral<ObservableRef<String>>() {},
//                                new TypeLiteral<ObservableRef<String>>() {},
//                                ThreeKey.valueOf("duh"),
//                                new ObservableRef<String>("foo")));
                binder.bind( new TypeLiteral<ObservableRef<String>>(){} ).toProvider(
                        ProviderProvider.getProvider(
                                new TypeLiteral<ObservableRef<String>>() {},
                                ThreeKey.valueOf("duh"),
                                new ObservableRef<String>("foo")));
            }
        });

        assertEquals( "foo", inj.getInstance( new TypeLiteral<ObservableRef<String>>(){}).get());
    }
}
