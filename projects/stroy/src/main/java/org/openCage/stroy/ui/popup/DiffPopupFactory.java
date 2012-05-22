package org.openCage.stroy.ui.popup;

import com.google.inject.Inject;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.prefs.PrefsUI;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/6/12
 * Time: 9:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class DiffPopupFactory<T extends Content> {

    private PrefsUI prefsUI;

    @Inject
    public DiffPopupFactory( PrefsUI prefsUI ) {
        this.prefsUI = prefsUI;
    }

    public DiffPopup get( final TreeMatchingTask<T> taskLeft,
                          final TreeMatchingTask<T> taskRight ) {

        return new DiffPopup( prefsUI, taskLeft, taskRight );
    }

}
