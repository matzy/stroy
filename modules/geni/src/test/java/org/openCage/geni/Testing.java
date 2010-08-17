package org.openCage.geni;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Aug 17, 2010
 * Time: 9:30:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class Testing {

    @Test
    public void simple() {
        assertEquals( "package org.openCage.foo;\n\n"+
                      "public class Duh {\n" +
                      "}\n",
                new Clazz( "org.openCage.foo", Typ.s("Duh") ).toString());
    }


    @Test
    public void simpleMesod(){
        assertEquals( "   public void foo(){\n" +
                      "   }\n", 
                new Mesod(null, "public", "foo" ).toString());
    }

    @Test
    public void clazz1Mes() {
        assertEquals( "package org.openCage.foo;\n" +
                      "\n" +
                      "public class Duh {\n" +
                      "   public void hi(){\n" +
                      "   }\n" +
                      "}\n",
                      new Clazz("org.openCage.foo", Typ.s("Duh")).publc().method( "hi").retrn().toString());
    }

    @Test
    public void clazz1Fld() {
        assertEquals( "package org.openCage.foo;\n" +
                      "\n" +
                      "public class Duh {\n" +
                      "   private Arg huh;\n" +
                      "}\n",
                      new Clazz("org.openCage.foo", Typ.s("Duh")).privt().fild( Typ.s("Arg"), "huh" ).c().toString());
    }


}
