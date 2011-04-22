package org.openCage.ui.pref;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.fife.ui.rtextarea.ConfigurableCaret;
import org.jetbrains.annotations.NotNull;
import org.openCage.property.PersistentProp;
import org.openCage.property.Property;
import org.openCage.property.PropStore;
import org.openCage.property.PropertyConstants;

public class CaretStyleProperty implements Provider<Property<Integer>> {

    public static final String KEY = "Texteditor.CaretStyle";
    private PropStore store;

    @Inject 
    public CaretStyleProperty(@NotNull @Named( PropertyConstants.STANDARD_PROPSTORE) PropStore store) {
        this.store = store;
    }

    @Override
    public Property<Integer> get() {
        return PersistentProp.get( store, KEY, ConfigurableCaret.BLOCK_STYLE, "Caret style for text editors" );
    }
}
