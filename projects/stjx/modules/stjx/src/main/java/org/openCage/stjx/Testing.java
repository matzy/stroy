//package org.openCage.generj;
//
//
//import org.junit.Test;
//
//import static junit.framework.Assert.assertEquals;
//
///**
// * Created by IntelliJ IDEA.
// * User: stephan
// * Date: Aug 17, 2010
// * Time: 9:30:21 AM
// * To change this template use File | Settings | File Templates.
// */
//public class Testing {
//
//    @Test
//    public void simple() {
//        assertEquals( "package org.openCage.foo;\n\n"+
//                      "public class Duh {\n" +
//                      "}\n",
//                new Class_( "org.openCage.foo", Typ.s("Duh") ).toString());
//    }
//
//
//    @Test
//    public void simpleMethod_(){
//        assertEquals( "   public void foo(){\n" +
//                      "   }\n",
//                new Method_(null, "public", "foo" ).toString());
//    }
//
//    @Test
//    public void clazz1Mes() {
//        assertEquals( "package org.openCage.foo;\n" +
//                      "\n" +
//                      "public class Duh {\n" +
//                      "   public void hi(){\n" +
//                      "   }\n" +
//                      "}\n",
//                      new Class_("org.openCage.foo", Typ.s("Duh")).public_().method( "hi").body().return_(null).toString());
//    }
//
//    @Test
//    public void clazz1Fld() {
//        assertEquals( "package org.openCage.foo;\n" +
//                      "\n" +
//                      "public class Duh {\n" +
//                      "   private Arg huh;\n" +
//                      "}\n",
//                      new Class_("org.openCage.foo", Typ.s("Duh")).privat_().field( Typ.s("Arg"), "huh" ).c().toString());
//    }
//
//    @Test
//    public void testHelloWorld() {
//
//        Class_ hr = new Class_("org.open.hello", Typ.s("HelloWorld")).
//                        public_()._static().method( Typ.VOID, "main" ).
//                           arg( Typ.array( "String" ), "args" ).
//                           body().call("System.out.println", Exp.s("Hello World\\n")).r().c();
//
//        assertEquals( "package org.open.hello;\n" +
//                      "\n" +
//                      "public class HelloWorld {\n" +
//                      "\n" +
//                      "   public static void main( String ... args ){\n" +
//                      "      System.out.println( \"Hello World\\n\" );\n" +
//                      "   }\n" +
//                      "}\n",
//                      hr.toString());
//    }
//
//
//}
