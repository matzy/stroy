package org.openCage.lang;

import net.jcip.annotations.Immutable;
import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;
import org.openCage.lang.artifact.Version;

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
public class LangArtifact implements ArtifactProvider {

    private final Artifact lang;
    private final Project  proj;

    public LangArtifact() {
        proj = Project.get( "stroy" );

        lang = proj.module( getClass(), "openCage", "openCage-lang" ).
                mpl11().
                java6().
                add( Version.valueOf( "0.3.0" ).build( 573 )).
                author( proj.author( "Stephan Pfab" ).email( "mailto:openCag@gmail.com" )).
                email( "mailto:openCage@gmail.com" ).
                address( "http://stroy.wikidot.com", "wikidot.com").
                descriptionShort( "a library with small java language level additions" ).
                depends( proj.external("com.intellij", "annotations" ).
                        descriptionShort( "Nullable annotations (bundled with Idea CE)" ).
                        address( "http://www.jetbrains.com/index.html", "jetbrains").
                        apache2().
                        version( "7.0.3")).
                depends( proj.external("net.jcip", "jcip-annotations" ).
                        address( "http://www.javaconcurrencyinpractice.com/", "javaconcurrencyinpractice" ).
                        creativeCommons().
                        version( "1.0" )).
                testDepends( proj.external("junit", "junit" ).
                        address( "http://www.junit.org/", "junit.org" ).
                        cpl().
                        version( "4.4" )).
                testDepends( proj.external("commons-lang", "commons-lang" ).
                        descriptionShort( "Apache java language level tools. Random number helpers, system property tools, string tools." ).
                        address( "http://commons.apache.org/lang/", "apache.org").
                        apache2().
                        version( "2.4" ))
                ;



    }

    public Artifact getArtifact() {
        return lang;
    }

    public Project getProject() {
        return proj;
    }
}
