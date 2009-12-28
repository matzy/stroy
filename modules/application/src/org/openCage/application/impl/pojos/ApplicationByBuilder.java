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

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class ApplicationByBuilder implements Application {

    private final String name;
    private final List<AuthorImpl> authors;
    private final List<AuthorImpl> contributors;
    private final VersionImpl version;
    private final LicenceImpl licence;
    private Icon icon;
    private final EmailAddressImpl email;
    private final WebpageImpl webpage;
    private String description;

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
            return Collections.<Author>emptyList();
        }
        return contributors;
    }

    public String getDescription() {
        return description;
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

    public void setDescription(String localizedDescription) {
        this.description = localizedDescription;
    }

    @Override
    public String toString() {
        return "ApplicationByBuilder{" +
                "name='" + name + '\'' +
                ", authors=" + authors +
                ", contributors=" + contributors +
                ", version=" + version +
                ", licence=" + licence +
                ", icon=" + icon +
                ", email=" + email +
                ", webpage=" + webpage +
                ", description='" + description + '\'' +
                '}';
    }
}
