<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="javax.ejb.*, javax.naming.*, de.meinefirma.meinprojekt.MyEjbIntf" %>
<%! // Falls JBoss:
    @javax.ejb.EJB
    private static EjbApi meineEJB;
    private void ejbLookup() {
      try {
        // Falls GlassFish oder WebLogic (fuer andere App-Server eventuell anpassen):
        Context ctx = new InitialContext();
        meineEJB = (EjbApi) ctx.lookup( "MyEjb#" + EjbApi.class.getName() );
      } catch( Exception ex ) {
        throw new RuntimeException( ex );
      }
    } %>
<%  if( meineEJB == null ) ejbLookup();
    String wert1 = request.getParameter( "wert1" );
    String wert2 = request.getParameter( "wert2" );
    if( wert1 == null ) wert1 = "";
    if( wert2 == null ) wert2 = "";
    String ergebnis = meineEJB.berechne( wert1, wert2 ); %>
<html>
<head><title>JSP mit Berechnungsformular</title></head>
<body>
<h2>JSP mit Berechnungsformular</h2>
<form name="meinFormular" method="get"><pre>
Wert1 <input type="text" name="wert1" value='<%= wert1 %>' size=10 maxlength=10><br>
Wert2 <input type="text" name="wert2" value='<%= wert2 %>' size=10 maxlength=10><br>
      <input type="submit" value="compute"> <input type="text" value='<%= ergebnis %>' size=10 readonly><br>
</pre></form>
</body>
</html>