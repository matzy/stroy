package org.openCage.application.protocol;

import java.io.File;
import java.net.URL;

public interface ApplicationFromConfig {
	public Application get( URL path, URL iconPath, String localizedDescription );
}
