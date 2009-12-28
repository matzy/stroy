package org.openCage.application.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;
import org.openCage.application.impl.pojos.ApplicationByBuilder;
import org.openCage.application.impl.pojos.AuthorImpl;
import org.openCage.application.impl.pojos.EmailAddressImpl;
import org.openCage.application.impl.pojos.LicenceImpl;
import org.openCage.application.impl.pojos.VersionImpl;
import org.openCage.application.impl.pojos.WebpageImpl;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.ApplicationFromConfig;
import org.openCage.lang.errors.Unchecked;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

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

public class ApplicationFromConfigXStream implements ApplicationFromConfig {

    public Application get( URL path, final URL iconPath, String localizedDescription ) {

        InputStream stream = null;
        try {
            stream = path.openStream();
            XStream xs = new XStream(new DomDriver());
            setAliasesOnXStream( xs );
            @SuppressWarnings({"CastToConcreteClass"})
            ApplicationByBuilder app = (ApplicationByBuilder) xs.fromXML(stream);

            if (iconPath != null) {
                app.setIcon(new ImageIcon(iconPath));
            }

            app.setDescription( localizedDescription ) ;

            return app;

        } catch (IOException e) {
            throw new Unchecked( e );
        } finally {
            IOUtils.closeQuietly( stream );
        }
    }

    public static void setAliasesOnXStream( XStream xs ) {
        xs.alias("Application", ApplicationByBuilder.class);
        xs.alias("Author", AuthorImpl.class);
        xs.alias("Version", VersionImpl.class);
        xs.alias("Email", EmailAddressImpl.class);
        xs.alias("Webpage", WebpageImpl.class);
        xs.alias("Licence", LicenceImpl.class );
    }
}
