package org.openCage.peleph;

import net.jcip.annotations.Immutable;
import org.openCage.lang.annotations.HiddenCall;
import org.openCage.lang.errors.Unchecked;

import java.io.ObjectStreamException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Immutable
public class WebPage {

    private final String page;
    private String shrt;
    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    public WebPage(String webpage) {
        page = webpage;
        getWithValidation();
    }

    public WebPage shrt( String name ) {
        this.shrt = name;
        return this;
    }

    public URI gettPage() {
        return getWithValidation();
    }

    @SuppressWarnings({"RedundantThrowsDeclaration"})
    @HiddenCall
    private Object readResolve() throws ObjectStreamException {
        getWithValidation();
        return this;
    }

    private URI getWithValidation() {
        try {
            URI uri = new URI(page);
            String scheme = uri.getScheme();
            if ( scheme == null || !(scheme.equals(HTTP) || scheme.equals(HTTPS) )) {
                throw new IllegalStateException( "not a web page scheme " + scheme );
            }

            return uri;
        } catch (URISyntaxException ex) {
            Logger.getLogger(WebPage.class.getName()).log(Level.WARNING, null, ex);
            throw new Unchecked(ex);
        }
    }


    @Override
    public String toString() {
        return "Webpage{" +
                "page='" + page + '\'' +
                '}';
    }
}
