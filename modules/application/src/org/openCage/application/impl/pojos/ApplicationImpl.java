package org.openCage.application.impl.pojos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.Author;
import org.openCage.application.protocol.Contact;
import org.openCage.application.protocol.Licence;
import org.openCage.application.protocol.Version;

public class ApplicationImpl implements Application{

	public final String name;
	private ArrayList<Author> authors = new ArrayList<Author>();
	
	public ApplicationImpl( final String name ) {
		this.name = name;
	}
	
	public ApplicationImpl with( Author author ) {
		authors.add( author );
		return this;
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
		// TODO Auto-generated method stub
		return null;
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
