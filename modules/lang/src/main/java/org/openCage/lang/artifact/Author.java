package org.openCage.lang.artifact;

import org.jetbrains.annotations.NotNull;
import org.openCage.lang.structure.Once;

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

/**
 * A Author, i.e. name and email of a person
 */
public class Author {

    private final String name;
    private final Once<EmailAddress> email = new Once<EmailAddress>( new EmailAddress( "mailto:404" ));

    Author( String name ) {
        this.name = name;
    }

    public final Author email( @NotNull String addr ) {
        email.set( new EmailAddress( addr ));
        return this;
    }


    public final String getName() {
        return name;
    }

    public static Author c( String name ) {
        return new Author( name );
    }

    public EmailAddress getEmailAddress() {
        return email.get();
    }

}
