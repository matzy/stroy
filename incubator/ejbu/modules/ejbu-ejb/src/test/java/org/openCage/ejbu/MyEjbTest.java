package org.openCage.ejbu;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import javax.naming.InitialContext;

public class MyEjbTest
{
   @Test public void testMyEjbImpl()
   {
      EjbApi meineEJB = new EjbApiImpl();
      assertEquals( "3.0", meineEJB.compute("1", "2") );
   }

   /* Diesen Test nur bei vorhandenem InitialContextFactoryMock aktivieren:
   @Test public void testMyEjbClient() throws Exception
   {
      System.setProperty( "java.naming.factory.initial", InitialContextFactoryMock.class.getName() );
      (new InitialContext()).bind( "MyEjb#" + MyEjbIntf.class.getName(), new MyEjbImpl() );
      String s = MyEjbClient.execute( new String[] { "1", "2" } );
      assertEquals( " 3.0", s.substring( s.length() - 4 ) );
   }
   */
}