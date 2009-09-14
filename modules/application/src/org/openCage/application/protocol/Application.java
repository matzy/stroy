package org.openCage.application.protocol;

import java.util.Collection;

public interface Application {
	public String                       getName();
	public Version                      getVersion();
	public Licence                      getLicence();
	public Collection<? extends Author> getAuthors();
	public Collection<Author>           getContributors();
	public String                       getDescprition();
	public Contact                      getContact();
}
