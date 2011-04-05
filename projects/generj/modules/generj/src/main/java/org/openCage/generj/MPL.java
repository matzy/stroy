package org.openCage.generj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (c) 2006 - 2010, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.public interface Statement {
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
