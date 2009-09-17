package org.openCage.application.impl.pojos;

import org.openCage.application.protocol.Licence;

public class LicenceImpl implements Licence{

	private String name;
	
	public LicenceImpl( String name ) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public boolean isOpenSource() {
		return true;
	}

}
