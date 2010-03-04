package org.openCage.ui.impl.pref;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.fife.ui.rtextarea.ConfigurableCaret;
import org.jetbrains.annotations.NotNull;
import org.openCage.property.clazz.AbstractPropertyProvider;
import org.openCage.property.protocol.PropStore;

public class CaretStyleProvider extends AbstractPropertyProvider<Integer> {

    public static final String KEY = "Texteditor.CaretStyle";

    @Inject 
    public CaretStyleProvider(@NotNull @Named( "std") PropStore store) {
        super(store, KEY, ConfigurableCaret.BLOCK_STYLE );
    }

}
