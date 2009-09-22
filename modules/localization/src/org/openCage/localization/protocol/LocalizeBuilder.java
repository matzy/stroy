package org.openCage.localization.protocol;

import java.util.List;

public interface LocalizeBuilder {
	public Localize build( String fullyqualifiedName, List<Localize> parents );
	public Localize get();
}
