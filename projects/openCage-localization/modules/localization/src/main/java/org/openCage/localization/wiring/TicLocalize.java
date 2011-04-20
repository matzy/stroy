//package org.openCage.localization.wiring;
//
//import org.openCage.lang.Sisl;
//import org.openCage.lang.functions.F0;
//import org.openCage.localization.DictLocalize;
//import org.openCage.localization.Localize;
//import org.openCage.localization.impl.LocalePropertyProvider;
//import org.openCage.property.PersistentProp;
//import org.openCage.property.PropStore;
//import org.openCage.property.Property;
//
//import java.util.Locale;
//
//public class TicLocalize {
//
//    public static void bind() {
//        Sisl.bind( Localize.class, new F0<Localize>() {
//            @Override
//            public Localize call() {
//                return new DictLocalize( Sisl.get(Property.class, LocalePropertyProvider.THE_LOCALE ));
//            }
//        });
//
//        Sisl.bind( Property.class, LocalePropertyProvider.THE_LOCALE,
//                new F0<Property>() {
//                    @Override
//                    public Property<Locale> call() {
//                        return PersistentProp.get( Sisl.get(PropStore.class), // TODO tag ??
//                                                   LocalePropertyProvider.THE_LOCALE,
//                                                   Locale.ENGLISH, "locale prop" );
//                    }
//                });
//
//        Sisl.bind( PropStore.class,
//                  new F0<PropStore>() {
//                      @Override
//                      public PropStore call() {
//                          return null;  //To change body of implemented methods use File | Settings | File Templates.
//                      }
//                  });
//
//
//
//    }
//
////    private static FSPath getPreferences() {
////        if ( SystemUtils.IS_OS_MAC_OSX ) {
////            return FSPathBuilder.getHome().add( "Library", "Preferences");
////        }
////
////        if ( SystemUtils.IS_OS_UNIX ) {
////            // follow freedesktop.org xdg base dir spec
////            String conf = System.getenv( "XDG_CONFIG" );
////            if ( conf != null ) {
////                return getPath( conf );
////            }
////
////            return FSPathBuilder.getHome().add( ".config" );
////        }
////
////        if ( SystemUtils.IS_OS_WINDOWS ) {
////            return getPath( System.getenv("APPDATA"));
////        }
////
////        throw new Error( "unknown os" );
////    }
//
//}
