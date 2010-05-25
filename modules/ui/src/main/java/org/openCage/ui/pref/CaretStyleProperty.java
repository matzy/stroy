package org.openCage.ui.pref;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.fife.ui.rtextarea.ConfigurableCaret;
import org.jetbrains.annotations.NotNull;
import org.openCage.property.Property;
import org.openCage.property.PropertyProvider;
import org.openCage.property.PropStore;

public class CaretStyleProperty extends PropertyProvider<Integer> implements Provider<Property<Integer>> {

    public static final String KEY = "Texteditor.CaretStyle";

    @Inject 
    public CaretStyleProperty(@NotNull @Named( "std") PropStore store) {
        super(store, KEY, ConfigurableCaret.BLOCK_STYLE, "Caret style for text editors" );
    }

}
