//package org.openCage.property.demo;
//
//import com.google.inject.Inject;
//import com.google.inject.Provider;
//import com.google.inject.name.Named;
//import org.openCage.property.PropStore;
//import org.openCage.property.Property;
//import org.openCage.property.PropertyProvider;
//
//
//public class MealProp extends PropertyProvider<Meal> implements Provider<Property<Meal>> {
//
//    public static final String Key = "Meal";
//
//    @Inject
//    public MealProp( @Named( "trans" ) PropStore store) {
//        super( store, Key, new Meal("Tomato Soup", "Cheese Burger"), "remember the last meal"  );
//    }
//}
