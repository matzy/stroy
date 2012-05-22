package org.openCage.stroy.ui.popup;

import com.google.inject.Inject;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.matching.TreeMatchingTask;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/6/12
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class PopupSelectorFactory<T extends Content> {

    private DiffPopupFactory<T> dpf;

    @Inject
    public PopupSelectorFactory(DiffPopupFactory<T> dpf) {
        this.dpf = dpf;

    }

    public PopupSelector get( final TreeMatchingTask<T> taskLeft, final TreeMatchingTask<T> taskRight) {
        return new PopupSelector( dpf, taskLeft, taskRight);
    }
}
