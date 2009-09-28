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
		return build;
	}

	public int getMajor() {
		return major;
	}

	public int getMinor() {
		return minor;
	}

	public int getPatch() {
		return patch;
	}

	@Override
	public String toString() {
		return "" + major + "." + minor + "." + patch + "." + build;
	}

	public int compareTo(Version version ) {
        if ( major !=  version.getMajor()  ) {
            return major - version.getMajor();
        }

        if ( minor != version.getMinor() ) {
            return minor - version.getMinor();
        }

        if ( patch != version.getPatch() ) {
            return patch - version.getPatch();
        }

        return build - version.getBuildnumber();
	}
}
