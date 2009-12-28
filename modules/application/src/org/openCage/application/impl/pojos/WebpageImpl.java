package org.openCage.application.impl.pojos;

import java.io.ObjectStreamException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.jcip.annotations.Immutable;
import org.openCage.application.protocol.Webpage;
import org.openCage.lang.annotations.HiddenCall;
import org.openCage.lang.errors.Unchecked;

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
public class WebpageImpl implements Webpage {

    private final String page;
    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    public WebpageImpl(String webpage) {
        page = webpage;
        getWithValidation();
    }

    @Override
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
            Logger.getLogger(WebpageImpl.class.getName()).log(Level.WARNING, null, ex);
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
