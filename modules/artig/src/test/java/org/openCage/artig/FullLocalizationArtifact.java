package org.openCage.artig;

import org.openCage.IOArtifact;
import org.openCage.localization.LocalizationArtifact;

public class FullLocalizationArtifact extends LocalizationArtifact {

    public FullLocalizationArtifact() {
        super();

        getArtifact().testDepends( new IOArtifact().getArtifact()).
                testDepends( getProject().get( "junit", "junit"));

    }

}
