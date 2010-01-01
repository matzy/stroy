package org.openCage.property.clazz;

import org.jetbrains.annotations.NotNull;
import org.openCage.property.protocol.PropStore;
import org.openCage.property.protocol.Property;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 01.01.2010
 * Time: 11:44:32
 * To change this template use File | Settings | File Templates.
 */
public class DummyPropStore implements PropStore {

    @Override
    public Property get(@NotNull String key) {
        return null;
    }

    @Override
    public void setProperty(@NotNull String key, @NotNull Property prop) {
    }

    @Override
    public void setDirty() {
    }
}
