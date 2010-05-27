package org.openCage.lang.artifact;

import net.jcip.annotations.Immutable;
import org.openCage.lang.Constants;
import org.openCage.lang.annotations.HiddenCall;
import org.openCage.lang.errors.Unchecked;

import java.io.ObjectStreamException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public String getShort() {
        return shrt;
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
        return page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof WebPage)) { return false; }

        WebPage webPage = (WebPage) o;

        if (page != null ? !page.equals(webPage.page) : webPage.page != null) { return false; }
        if (shrt != null ? !shrt.equals(webPage.shrt) : webPage.shrt != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = page != null ? page.hashCode() : 0;
        result = Constants.HASHFACTOR * result + (shrt != null ? shrt.hashCode() : 0);
        return result;
    }
}
