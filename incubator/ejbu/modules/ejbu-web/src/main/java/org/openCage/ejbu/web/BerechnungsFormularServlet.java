package org.openCage.ejbu.web;

import org.openCage.ejbu.EjbApi;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BerechnungsFormularServlet extends HttpServlet
{
   @javax.ejb.EJB
   EjbApi meineEJB;

   @Override
   public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
   {
      doGetOrPost( request, response );
   }

   @Override
   public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
   {
      doGetOrPost( request, response );
   }

   private void doGetOrPost( HttpServletRequest request, HttpServletResponse response ) throws IOException
   {
      // Request:
      String wert1 = request.getParameter( "wert1" );
      String wert2 = request.getParameter( "wert2" );
      if( wert1 == null ) wert1 = "";
      if( wert2 == null ) wert2 = "";
      // EJB-Aufruf:
      String ergebnis = meineEJB.compute(wert1, wert2);
      // Response:
      response.setContentType( "text/html; charset=ISO-8859-1" );
      PrintWriter out = response.getWriter();
      out.printf(
         "<html>\n" +
         "<head><title>Servlet mit Berechnungsformular</title></head>\n" +
         "<body>\n" +
         "<h2>Servlet mit Berechnungsformular</h2>\n" +
         "<form name='meinFormular' method='get'><pre>\n" +
         "Wert1 <input type='text' name='wert1' value='%1$s' size=10 maxlength=10><br>\n" +
         "Wert2 <input type='text' name='wert2' value='%2$s' size=10 maxlength=10><br>\n" +
         "      <input type='submit' value='berechne'> <input type='text' value='%3$s' size=10 readonly><br>\n" +
         "</pre></form>\n" +
         "</body>\n" +
         "</html>\n",
         wert1, wert2, ergebnis );
   }
}