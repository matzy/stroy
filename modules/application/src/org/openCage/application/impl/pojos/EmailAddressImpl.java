package org.openCage.application.impl.pojos;

import java.io.ObjectStreamException;
import java.net.URI;
import java.net.URISyntaxException;

import net.jcip.annotations.Immutable;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.openCage.application.protocol.EmailAddress;
import org.openCage.lang.annotations.HiddenCall;

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
public class EmailAddressImpl implements EmailAddress {

	private final String  email;
    private static final String MAILTO = "mailto";

    public EmailAddressImpl( @NonNls @NotNull String email ) {
		this.email = email;
		getWithValidation();
	}

 	@Override
     public URI gettEmail() {
         return getWithValidation();
	}

    // called by deserialization
    // guaranties correctness
    @SuppressWarnings({"RedundantThrowsDeclaration"})
    @HiddenCall
 	private Object readResolve() throws ObjectStreamException {
 		getWithValidation();
 		return this;
 	}
 	
    private URI getWithValidation() {
        try {
            URI emailURI = new URI(email);
            if ( emailURI.getScheme() == null || !emailURI.getScheme().equals(MAILTO)) {
                throw new IllegalStateException( "email address without mailto:" );
            }

            return emailURI;

        } catch (URISyntaxException e) {
            throw new IllegalStateException( "not a valid email string " + e);
        }
    }

    @Override
    public String toString() {
        return "EmailAddress{" +
                "email='" + email + '\'' +
                '}';
    }
}
