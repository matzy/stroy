package org.openCage.generj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * <p/>
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * <p/>
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * <p/>
 * The Original Code is stroy code
 * <p/>
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 * <p/>
 * Contributor(s):
 * **** END LICENSE BLOCK ****
*/
public class MPL {

    String[] comments1 = {
            "** BEGIN LICENSE BLOCK *****",
            "Version: MPL 1.1",
            "<p/>",
            "The contents of this file are subject to the Mozilla Public License Version",
            "1.1 (the \"License\"); you may not use this file except in compliance with",
            "the License. You may obtain a copy of the License at",
            "http://www.mozilla.org/MPL/",
            "<p/>",
            "Software distributed under the License is distributed on an \"AS IS\" basis,",
            "WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License",
            "for the specific language governing rights and limitations under the",
            "License.",
            "<p/>" };
    String[] comments2 = {
            "All Rights Reserved.",
            "<p/>",
            "Contributor(s):",
            "**** END LICENSE BLOCK ****" };
    private String time;
    private String author;
    private String project;
    private String email;


    public MPL( String author, String email, String time, String project ) {
        this.author = author;
        this.time = time;
        this.project = project;
        this.email = email;
    }

    public BlockComment getComment() {
        List<String> comments = new ArrayList<String>();

        comments.addAll( Arrays.asList( comments1 ));
        comments.add( "The Original Code is " + project + " code");
        comments.add( "<p/>");
        comments.add( "The Initial Developer of the Original Code is " + author + " <" + email + ">.");
        comments.add( "Portions created by " + author + " are Copyright (C) " + time + ".");
        comments.addAll( Arrays.asList( comments2 ));

        return new BlockComment( comments );

    }
}
