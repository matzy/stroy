package org.openCage.ejbu.client;

import org.openCage.ejbu.EjbApi;

import javax.naming.Context;
import javax.naming.InitialContext;

public class MyEjbClient
{
   public static void main( String[] args ) throws Exception
   {
      System.out.println( "\n" + execute( args ) );
   }

   public static String execute( String[] args ) throws Exception
   {
      if( args.length < 2 )
         return "Bitte zwei Zahlen uebergeben (eventuell zusaetzlich den Context.lookup()-String).";
      // Context.lookup()-String fuer EJB ist leider App-Server-spezifisch:
      String    ejbJndiName = ( args.length > 2 ) ? args[2] : ("MyEjb#" + EjbApi.class.getName());
      Context   ctx = new InitialContext();
      EjbApi meineEJB = (EjbApi) ctx.lookup( ejbJndiName );
      return "MyEjbIntf.berechne( " + args[0] + ", " + args[1] + " ) = " +
               meineEJB.compute(args[0], args[1]);
   }
}