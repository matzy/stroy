package org.openCage.application.protocol;

public interface UpdateChecker {

	public Version getLatestVersion();

	public void check();

	public boolean checkAnyway();

	public boolean isConnected();

}