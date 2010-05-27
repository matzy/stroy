//package org.openCage.property.demo;
//
//import com.google.inject.Binder;
//import com.google.inject.Module;
//import com.google.inject.Singleton;
//import com.google.inject.TypeLiteral;
//import com.google.inject.name.Names;
//import org.openCage.property.PropStore;
//import org.openCage.property.Property;
//
///**
// * Created by IntelliJ IDEA.
// * User: stephan
// * Date: 28-mar-2010
// * Time: 13:20:25
// * To change this template use File | Settings | File Templates.
// */
//public class DemoWiring implements Module {
//
//    @Override
//    public void configure(Binder binder) {
////        binder.install( new PropertyWiring());
//
//        binder.bind( PropStore.class ).
//                annotatedWith( Names.named( "trans" )).
//                toProvider( AliasedPropStoreProvider.class );
//
//        binder.bind( new TypeLiteral<Property<String>>() {} ).
//                annotatedWith( Names.named( RestaurantProp.Key )).
//                toProvider( RestaurantProp.class ).
//                in( Singleton.class );
//
//        binder.bind( new TypeLiteral<Property<Meal>>() {} ).
//                annotatedWith( Names.named( MealProp.Key )).
//                toProvider( MealProp.class ).
//                in( Singleton.class );
//    }
//
//}
