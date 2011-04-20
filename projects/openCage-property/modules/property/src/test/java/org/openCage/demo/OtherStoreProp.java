//package org.openCage.demo;
//
//import com.google.inject.Inject;
//import com.google.inject.Provider;
//import com.google.inject.name.Named;
//import org.openCage.property.PropStore;
//import org.openCage.property.Property;
//import org.openCage.property.PropertyProvider;
//
//public class OtherStoreProp extends PropertyProvider<String> implements Provider<Property<String>> {
//
//    public final static String KEY = "Other";
//
//    @Inject
//    public OtherStoreProp( @Named( "trans" ) PropStore store ) {
//        super( store, KEY, "totally different", "other");
//    }
//}
