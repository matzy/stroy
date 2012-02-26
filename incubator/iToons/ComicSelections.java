/*
 * ComicSelection.java
 *
 * Created on September 17, 2004, 7:54 PM
 */

import javax.swing.*;
import java.util.*;


/**
 *
 * @author  SPfab
 */
public class ComicSelections extends AbstractListModel {
    
    /** Creates a new instance of ComicSelection */
    public ComicSelections() {
        selections = new Vector();
    }
    
    public Object getElementAt(int index) {
        return ((Selection)selections.get(index)).getName();        
    }
    
    public int getSize() {
        return selections.size();
    }
    
    public Selection getSelection( int idx ) {
        return (Selection)selections.get(idx);
    }
    
    public void add( Selection sel ) {
        selections.add( sel );
        fireIntervalAdded( this, selections.size() -2, selections.size() - 1);
    }
    
    public void update() {
        for ( int i = 0; i < selections.size(); i++ ) {
            ((Selection)selections.get(i)).update();
        }
    }

    private Vector selections;
}
