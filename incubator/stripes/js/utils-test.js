/**
 * @author SPF
 */

 $(document).ready(function(){
 	
	var vals = [ 't','e','s','i','n','g' ]; // not alphabeticaly sorted
 	
	$( "body" ).append( "is(t,e, vals): false   -- " + compy.fct("is")( "t", "e", vals  ) + "<br>");
	$( "body" ).append( "is(n,n, vals): true   -- " + compy.fct("is")( "n", "n", vals  ) + "<br>");

	$( "body" ).append( "any(e,s, vals): true   -- " + compy.fct("any")( "e", "s", vals  ) + "<br>");

	$( "body" ).append( "upto(e,e, vals): true   -- " + compy.fct("upto")( "e", "e", vals  ) + "<br>");
	$( "body" ).append( "upto(t,e, vals): true   -- " + compy.fct("upto")( "t", "e", vals  ) + "<br>");
	$( "body" ).append( "upto(s,e, vals): false   -- " + compy.fct("upto")( "s", "e", vals  ) + "<br>");
	
 })
 