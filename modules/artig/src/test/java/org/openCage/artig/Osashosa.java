package org.openCage.artig;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;

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

public class Osashosa implements ArtifactProvider, Provider<Artifact> {

    private final Project proj;
    private final Artifact osashosa;

    @Inject
    public Osashosa() {
        proj = new Lang().getProject();

        osashosa = proj.module( getClass(), "openCage", "openCage-osashosa" ).
                version( "0.1.0" ).
                mpl11().
                depends( proj.get( "openCage", "openCage-lang" ) ).
                testDepends( proj.get( "junit", "junit" ) );
    }

    @Override
    public Artifact get() {
        return osashosa;
    }

    @Override
    public Artifact getArtifact() {
        return osashosa;
    }

    @Override
    public Project getProject() {
        return proj;
    }
}
