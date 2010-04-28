package org.openCage.ui.pref;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.fife.ui.rtextarea.ConfigurableCaret;
import org.jetbrains.annotations.NotNull;
import org.openCage.property.protocol.AbstractPropertyProvider;
import org.openCage.property.protocol.PropStore;

public class CaretStyleProperty extends AbstractPropertyProvider<Integer> {

    public static final String KEY = "Texteditor.CaretStyle";

    @Inject 
    public CaretStyleProperty(@NotNull @Named( "std") PropStore store) {
        super(store, KEY, ConfigurableCaret.BLOCK_STYLE, "Caret style for text editors" );
    }

}
