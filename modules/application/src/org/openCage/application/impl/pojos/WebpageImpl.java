/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.application.impl.pojos;

import java.io.ObjectStreamException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openCage.application.protocol.Webpage;
import org.openCage.lang.errors.Unchecked;

/**
 *
 * @author stephan
 */
public class WebpageImpl implements Webpage {

    private String page;

    public WebpageImpl(String webpage) {
        page = webpage;
        getWithValidation();
    }

    public URI gettPage() {
        return getWithValidation();
    }

    private Object readResolve() throws ObjectStreamException {
        getWithValidation();
        return this;
    }

    private URI getWithValidation() {
        try {
            URI uri = new URI(page);
            String scheme = uri.getScheme();
            if ( scheme == null || !(scheme.equals( "http" ) || scheme.equals("https") )) {
                throw new Unchecked( null ); // TODO
            }

            return uri;
        } catch (URISyntaxException ex) {
            Logger.getLogger(WebpageImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Unchecked(ex);
        }
    }


}
