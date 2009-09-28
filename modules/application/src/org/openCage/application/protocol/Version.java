package org.openCage.application.protocol;

public interface Version extends Comparable<Version>{
	public int getMajor();
	public int getMinor();
	public int getPatch();
	public int getBuildnumber();
}
