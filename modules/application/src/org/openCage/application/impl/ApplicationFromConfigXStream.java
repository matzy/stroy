package org.openCage.application.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;
import org.openCage.application.impl.pojos.ApplicationByBuilder;
import org.openCage.application.impl.pojos.AuthorImpl;
import org.openCage.application.impl.pojos.VersionImpl;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.ApplicationFromConfig;
import org.openCage.lang.errors.Unchecked;
import org.openCage.withResource.protocol.With;

import com.google.inject.Inject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.openCage.application.impl.pojos.EmailAddressImpl;
import org.openCage.application.impl.pojos.WebpageImpl;
import org.openCage.lang.protocol.FE1;

public class ApplicationFromConfigXStream implements ApplicationFromConfig {

    @Inject
    private With with;

    public Application get( URL path, final URL iconPath) {

        InputStream stream = null;
        try {
            stream = path.openStream();
            XStream xs = new XStream(new DomDriver());
            xs.alias("Application", ApplicationByBuilder.class);
            xs.alias("Author", AuthorImpl.class);
            xs.alias("Version", VersionImpl.class);
            xs.alias("Email", EmailAddressImpl.class);
            xs.alias("Webpage", WebpageImpl.class);
            ApplicationByBuilder app = (ApplicationByBuilder) xs.fromXML(stream);

            if (iconPath != null) {
                app.setIcon(new ImageIcon(iconPath));
            }
            return app;

        } catch (IOException e) {
            throw new Unchecked( e );
        } finally {
            IOUtils.closeQuietly( stream );
        }
    }
}
