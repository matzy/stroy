package org.openCage.lang.impl;

import org.openCage.lang.protocol.F1;
import org.openCage.lang.protocol.Nullable;

/**
 * A class that encapsels a nullabe object
 * it gives acces to this object only via a method that guaranties nonnullness. 
 * @author stephan
 *
 * @param <T>
 */
public class MaybeImpl<T extends Nullable> {
	
	private final T obj; 
	
	public MaybeImpl( T obj ) {
		this.obj = obj;
	}
	
	public void use( F1<Void, T> fun ) {
		if ( obj.isNull() ) {
			return;
		}
		
		fun.call( obj );
	}
	
	public <R> R use( R els, F1<R,T> fun ) {
		if ( obj.isNull() ) {
			return els;
		}
		
		return fun.call( obj );
	}
}
