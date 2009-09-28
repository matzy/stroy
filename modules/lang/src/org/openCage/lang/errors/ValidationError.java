package org.openCage.lang.errors;

public class ValidationError extends Error {

	private static final long serialVersionUID = -6539329615674537054L;
	
	public ValidationError( Class clas, String message ) {
		super( "Class " + clas.getName() + "has validation error " + message  );
	}
	
	public ValidationError( Class clas, String message, Throwable cause ) {
		super( "Class " + clas.getName() + "has validation error " + message, cause  );		
	}
}
