package org.openCage.other;

import org.openCage.localization.protocol.Localize;

public class DummyLocalize implements Localize {

	public String localize(String key) {
		return key;
	}

}
