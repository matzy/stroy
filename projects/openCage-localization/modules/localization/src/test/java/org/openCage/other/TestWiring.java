package org.openCage.other;

import com.google.inject.*;
import com.google.inject.name.Names;
import org.junit.Ignore;
import org.openCage.lang.BackgroundExecutorImpl;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.localization.BundleCheck;
import org.openCage.localization.Localize;
import org.openCage.localization.impl.BundleCheckImpl;
import org.openCage.localization.impl.LocalePropertyProvider;
import org.openCage.property.NonPersistingPropStore;
import org.openCage.property.PropStore;
import org.openCage.property.Property;
import org.openCage.property.PropertyConstants;

import java.util.Locale;

@Ignore
public class TestWiring implements Module {

    public void configure(Binder binder) {

        binder.bind( PropStore.class ).
                annotatedWith( Names.named(PropertyConstants.STANDARD_PROPSTORE)).
                to( NonPersistingPropStore.class).
                in( Singleton.class );

        binder.bind( new TypeLiteral<Property<Locale>>() {} ).
                annotatedWith( Names.named( LocalePropertyProvider.THE_LOCALE)).
                toProvider( LocalePropertyProvider.class ).
                in( Singleton.class );

        binder.bind( BundleCheck.class).to( BundleCheckImpl.class );

        binder.bind( Localize.class ).
                annotatedWith( Names.named("testing")).
//                to( TestLocalize.class );
                toProvider( LocalizeProvider.class );

        binder.bind(BackgroundExecutor.class ).
                to(BackgroundExecutorImpl.class );


    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof TestWiring;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
