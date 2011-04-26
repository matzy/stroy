/**
 * @author SPF
 */

 var compy = function() {
	var that = {};
	
	that.vals = ["any", "upto", "is", "at least"];

	that.leq = function(x, y, vals ) {
		if ( isNaN(x)) {
			return $.inArray(x,vals) <= $.inArray(y,vals);	
		}
		return x <= y;
	} 
	
	that.eq = function( x, y ) {
		return x == y;		
	}
	
	that.geq = function( x, y, vals ) {
		if ( isNaN(x)) {
			return $.inArray(x,vals) >= $.inArray(y,vals);	
		}
		
		return x >= y;
	} 
	
	that.any = function( x, y, vals ) {
		return true;
	}
	
	that.fct = function( str ) {
		if ( str == "is") {
			return that.eq;
		}
		
		if ( str == "upto" ) {
			return that.leq;
		}
		
		if ( str == "any" ) {
			return that.any;	
		}
		
		return that.geq;
	}
	
	return that;	
}();
