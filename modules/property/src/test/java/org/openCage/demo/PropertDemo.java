//package org.openCage.demo;
//
//import com.google.inject.Guice;
//import com.google.inject.Inject;
//import com.google.inject.Injector;
//import com.google.inject.name.Named;
//import org.openCage.lang.functions.F1;
//import org.openCage.property.protocol.Property;
//
///**
// * Created by IntelliJ IDEA.
// * User: stephan
// * Date: Dec 23, 2009
// * Time: 2:23:06 PM
// * To change this template use File | Settings | File Templates.
// */
//public class PropertDemo {
//
//    @Inject @Named( "demo")
//    public Property<String> prop;
//
//    @Inject @Named( DuhProp.HICKER )
//    public Property<Integer> prop2;
//
//    @Inject @Named( OtherStoreProp.KEY )
//    public Property<String> otherProp;
//
//    public static void main(String[] args) {
//        Injector injector = Guice.createInjector( new PropertyDemoWiring() );
//
//        PropertDemo demo = injector.getInstance( PropertDemo.class );
//
//        System.out.println( demo.prop.get() );
//        System.out.println( demo.prop2.get() );
//        demo.prop2.modify( new F1<Integer, Integer>() {
//            @Override
//            public Integer call(Integer integer) {
//                return 77;
//            }
//        });
//        System.out.println( demo.prop2.get() );
//        demo.prop2.setDefault();
//        System.out.println( demo.prop2.get() );
//
//        System.out.println( demo.otherProp.get());
//    }
//}
