package org.openCage.stroy.file;

import org.openCage.lang.functions.F1;
import org.openCage.lang.functions.F2;
import org.openCage.lang.functions.VF0;
import org.openCage.lang.structure.ObservableRef;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/18/12
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImmuBridge<T> {

    private F2<Void, T, String> setter;
    private F1<String, T> getter;
    private ObservableRef<String> prop;
    private VF0 last = null;

    public ImmuBridge(F1<String, T> getter, F2<Void, T, String> setter) {
        this.getter = getter;
        this.setter = setter;

        prop = new ObservableRef<String>("");
    }

    public void set( final T obj ) {
        if ( last != null ) {
            prop.getListenerControl().remove(last);
        }

        prop.set( getter.call(obj));

        last = new VF0() {
            @Override
            public void call() {
                setter.call(obj,prop.get());
            }
        };

        prop.getListenerControl().add( last );
    }

    public ObservableRef<String> getProp() {
        return prop;
    }


}
