package org.openCage.ejbu;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 5/23/11
 * Time: 10:35 AM
 * To change this template use File | Settings | File Templates.
 */
@javax.ejb.Stateless( mappedName = "MyEjb" )
public class EjbApiImpl implements EjbApi
{
   public String compute( String d1, String d2 )
   {
      try {
         return "" + (Double.parseDouble( d1 ) + Double.parseDouble( d2 ));
      } catch( Exception ex ) {
         return "";
      }
   }
}