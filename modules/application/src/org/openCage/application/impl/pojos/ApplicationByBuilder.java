package org.openCage.application.impl.pojos;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.Author;
import org.openCage.application.protocol.Contact;
import org.openCage.application.protocol.Licence;
import org.openCage.application.protocol.Version;

public class ApplicationByBuilder implements Application{

	private final String        name;
	private final List<AuthorImpl>  authors;
	private final List<AuthorImpl>  contributors;
	private final VersionImpl       version;
	private final LicenceImpl       licence;
	private final Icon              icon;
	
	public ApplicationByBuilder( final String                 name, 
								 final List<AuthorImpl> authors,
								 final VersionImpl                version,
								 final LicenceImpl licence,
								 final List<AuthorImpl> cont,
								 final Icon icon ) {
		this.name    = name;
		this.authors = authors;
		this.version = version;
		this.licence = licence;
		this.contributors = cont;
		this.icon = icon;
	}
	
	public Collection<? extends Author> getAuthors() {
		return Collections.unmodifiableCollection( authors );
	}

	public Licence getLicence() {
		return licence;
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

	public Collection<? extends Author> getContributors() {
		if ( contributors == null ) {
			return Collections.EMPTY_LIST;
		}
		return contributors;
	}

	public String getDescprition() {
		// TODO Auto-generated method stub
		return null;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon imageIcon) {
		this.icon = icon;
	}
}
