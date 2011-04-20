//package org.openCage.property.demo;
//
//import com.google.inject.Inject;
//import com.google.inject.name.Named;
//import org.openCage.lang.functions.F1;
//import org.openCage.property.Property;
//
//public class Sue {
//
//    @Inject
//    public Sue( @Named( RestaurantProp.Key ) Property<String> dinner ) {
//
//        dinner.addPropertyChangeListener( new F1<Void, String>() {
//            @Override public Void call(String newRestaurant ) {
//                System.out.println("Sue will meet Joe at " + newRestaurant );
//                return null;
//            }
//        });
//    }
//
//}
