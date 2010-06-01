package org.openCage.localization.wiring;

import org.apache.commons.lang.SystemUtils;
import org.openCage.io.fspath.FSPath;
import org.openCage.lang.Tic;
import org.openCage.lang.functions.F0;
import org.openCage.localization.DictLocalize;
import org.openCage.localization.Localize;
import org.openCage.localization.impl.LocaleProperty;
import org.openCage.property.PersistentProp;
import org.openCage.property.PropStore;
import org.openCage.property.Property;

import java.util.Locale;

public class TicLocalize {

    public static void bind() {
        Tic.bind( Localize.class, new F0<Localize>() {
            @Override
            public Localize call() {
                return new DictLocalize( Tic.get(Property.class, LocaleProperty.THE_LOCALE ));
            }
        });

        Tic.bind( Property.class, LocaleProperty.THE_LOCALE,
                new F0<Property>() {
                    @Override
                    public Property<Locale> call() {
                        return PersistentProp.get( Tic.get(PropStore.class), // TODO tag ?? 
                                                   LocaleProperty.THE_LOCALE,
                                                   Locale.ENGLISH, "locale prop" );
                    }
                });

        Tic.bind( PropStore.class,
                  new F0<PropStore>() {
                      @Override
                      public PropStore call() {
                          return null;  //To change body of implemented methods use File | Settings | File Templates.
                      }
                  });



    }

//    private static FSPath getPreferences() {
//        if ( SystemUtils.IS_OS_MAC_OSX ) {
//            return FSPathBuilder.getHome().add( "Library", "Preferences");
//        }
//
//        if ( SystemUtils.IS_OS_UNIX ) {
//            // follow freedesktop.org xdg base dir spec
//            String conf = System.getenv( "XDG_CONFIG" );
//            if ( conf != null ) {
//                return getPath( conf );
//            }
//
//            return FSPathBuilder.getHome().add( ".config" );
//        }
//
//        if ( SystemUtils.IS_OS_WINDOWS ) {
//            return getPath( System.getenv("APPDATA"));
//        }
//
//        throw new Error( "unknown os" );
//    }
    
}
