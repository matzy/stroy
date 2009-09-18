package org.openCage.application.protocol;

import java.io.File;
import java.net.URL;

public interface ApplicationFromConfig {
	public Application get( File path, URL iconPath );
}
