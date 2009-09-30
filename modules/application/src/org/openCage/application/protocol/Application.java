package org.openCage.application.protocol;

import java.util.Collection;

import javax.swing.Icon;

public interface Application {
	public String                         gettName();
	public Version                        gettVersion();
	public Licence                        getLicence();
	public Collection<? extends Author>   getAuthors(); 
	public Collection<? extends Author>   getContributors();
	public String                         getDescprition();
	public Icon                           getIcon();
        public EmailAddress                   getSupportEmail();
        public Webpage                        getWebpage();
}
