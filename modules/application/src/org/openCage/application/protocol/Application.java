package org.openCage.application.protocol;

import java.util.Collection;

import javax.swing.Icon;

public interface Application {
	public String                         getName();
	public Version                        getVersion();
	public Licence                        getLicence();
	public Collection<? extends Author>   getAuthors(); 
	public Collection<? extends Author>   getContributors();
	public String                         getDescprition();
	public Contact                        getContact();
	public Icon                           getIcon();
}
