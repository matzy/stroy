package org.openCage.application.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;
import org.openCage.application.impl.pojos.*;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.ApplicationFromConfig;
import org.openCage.lang.errors.Unchecked;
import org.openCage.withResource.protocol.With;

import com.google.inject.Inject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.openCage.lang.protocol.FE1;

public class ApplicationFromConfigXStream implements ApplicationFromConfig {

    public Application get( URL path, final URL iconPath, String localizedDescription ) {

        InputStream stream = null;
        try {
            stream = path.openStream();
            XStream xs = new XStream(new DomDriver());
            setAliasesOnXStream( xs );
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
