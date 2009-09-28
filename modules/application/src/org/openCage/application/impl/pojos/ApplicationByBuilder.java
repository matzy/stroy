package org.openCage.application.impl.pojos;

import java.awt.Component;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;

import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.Author;
import org.openCage.application.protocol.Contact;
import org.openCage.application.protocol.EmailAddress;
import org.openCage.application.protocol.Licence;
import org.openCage.application.protocol.Version;

public class ApplicationByBuilder implements Application{

	private final String        name;
	private final List<AuthorImpl>  authors;
	private final List<AuthorImpl>  contributors;
	private final VersionImpl       version;
	private final LicenceImpl       licence;
//	private ContactImpl       contact;
	private Icon icon;
	private EmailAddressImpl email;
	
	public ApplicationByBuilder( final String                 name, 
								 final List<AuthorImpl> authors,
								 final VersionImpl                version,
								 final LicenceImpl licence,
								 final List<AuthorImpl> cont,
								 ContactImpl contact,
								 String email ) {
		this.name    = name;
		this.authors = authors;
		this.version = version;
		this.licence = licence;
		this.contributors = cont;
//		this.contact = contact;
		this.email = new EmailAddressImpl(email);
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
		return null; //contact;
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

	public void setIcon( Icon icon) {
		this.icon = icon;
	}

	public Icon getIcon() {
		if ( icon == null ) {
			return new Icon() {

				public int getIconHeight() {
					// TODO Auto-generated method stub
					return 0;
				}

				public int getIconWidth() {
					// TODO Auto-generated method stub
					return 0;
				}

				public void paintIcon(Component c, Graphics g, int x, int y) {
					// TODO Auto-generated method stub
					
				}};
		}
		
		return icon ;
	}
	
	public void validate() {
//		contact.validate();
	}
	
}
