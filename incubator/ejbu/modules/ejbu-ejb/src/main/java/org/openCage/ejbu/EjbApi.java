package org.openCage.ejbu;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 5/23/11
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
@javax.ejb.Remote
public interface EjbApi
{
   public String compute( String d1, String d2 );
}