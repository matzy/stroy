package org.openCage.application.impl.pojos;

import java.awt.Component;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;

import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.Author;
import org.openCage.application.protocol.EmailAddress;
import org.openCage.application.protocol.Licence;
import org.openCage.application.protocol.Version;
import org.openCage.application.protocol.Webpage;

public class ApplicationByBuilder implements Application {

    private final String name;
    private final List<AuthorImpl> authors;
    private final List<AuthorImpl> contributors;
    private final VersionImpl version;
    private final LicenceImpl licence;
    private Icon icon;
    private EmailAddressImpl email;
    private WebpageImpl webpage;

    public ApplicationByBuilder(final String name,
            final List<AuthorImpl> authors,
            final VersionImpl version,
            final LicenceImpl licence,
            final List<AuthorImpl> cont,
            String email,
            String webpage ) {
        this.name = name;
        this.authors = authors;
        this.version = version;
        this.licence = licence;
        this.contributors = cont;
        this.email = new EmailAddressImpl(email);
        this.webpage = new WebpageImpl( webpage );
    }

    public Collection<? extends Author> getAuthors() {
        return Collections.unmodifiableCollection(authors);
    }

    public Licence getLicence() {
        return licence;
    }

    public String gettName() {
        return name;
    }

    public Version gettVersion() {
        return version;
    }

    public Collection<? extends Author> getContributors() {
        if (contributors == null) {
            return Collections.EMPTY_LIST;
        }
        return contributors;
    }

    public String getDescprition() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        if (icon == null) {
            return new Icon() {
                public int getIconHeight() {
                    return 0;
                }

                public int getIconWidth() {
                    return 0;
                }

                public void paintIcon(Component c, Graphics g, int x, int y) {
                }
            };
        }

        return icon;
    }

    public void validate() {
//		contact.validate();
    }

    public EmailAddress getSupportEmail() {
        return email;
    }

    public Webpage getWebpage() {
        return webpage;
    }
}
