package org.openCage.application.impl.pojos;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.Author;
import org.openCage.application.protocol.Contact;
import org.openCage.application.protocol.Licence;
import org.openCage.application.protocol.Version;

public class ApplicationByBuilder implements Application{

	private final String        name;
	private final List<Author>  authors;
	private final Version       version;
	
	public ApplicationByBuilder( final String name, 
								 final List<Author> authors,
								 final Version      version ) {
		this.name    = name;
		this.authors = authors;
		this.version = version;
	}
	
	public Collection<? extends Author> getAuthors() {
		return Collections.unmodifiableCollection( authors );
	}

	public Licence getLicence() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return name;
	}

	public Version getVersion() {
		return version;
	}

	public Contact getContact() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Author> getContributors() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescprition() {
		// TODO Auto-generated method stub
		return null;
	}
}
