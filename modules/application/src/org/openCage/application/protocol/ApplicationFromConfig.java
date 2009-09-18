package org.openCage.application.protocol;

import java.io.File;

public interface ApplicationFromConfig {
	public Application get( File xmlPath, String iconPath );
}
