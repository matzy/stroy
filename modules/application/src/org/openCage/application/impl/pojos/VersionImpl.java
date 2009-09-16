package org.openCage.application.impl.pojos;

import org.openCage.application.protocol.Version;


public class VersionImpl implements Version {

	private int major;
	private int minor;
	private int patch;
	private int build;

	public VersionImpl( int major, int minor, int patch, int build ) {
		this.major = major; 
		this.minor = minor;
		this.patch = patch;
		this.build = build;
	}
	
	public int getBuildnumber() {
		// TODO Auto-generated method stub
		return build;
	}

	public int getMajor() {
		// TODO Auto-generated method stub
		return major;
	}

	public int getMinor() {
		// TODO Auto-generated method stub
		return minor;
	}

	public int getPatch() {
		// TODO Auto-generated method stub
		return patch;
	}

	@Override
	public String toString() {
		return "" + major + "." + minor + "." + patch + "." + build;
	}
	
	

}
