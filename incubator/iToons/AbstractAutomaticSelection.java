/*
 * Selection.java
 *
 * Created on September 17, 2004, 8:01 PM
 */
import java.util.*;

/**
 *
 * @author  SPfab
 */
public abstract class AbstractAutomaticSelection implements Selection{
    
    /** Creates a new instance of Selection */
    public AbstractAutomaticSelection( String name, Vector comics ) {
        all = comics;
        this.name = name;
        my = new Vector();
        update();
    }

    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public String getName() {
        return this.name;
    }
    
    public abstract boolean belongs( Comic comic );
    public abstract boolean isSystem();
    
    public void update() {
        my.clear();
        for ( int i = 0; i < all.size(); ++i ) {
            if ( belongs( (Comic)all.get(i))) {
                my.add( all.get(i));
            }
        }        
    }
    
    public Comic get( int idx ) {
        return (Comic)my.get(idx);
    }
    
    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {
        if ( !isSystem() ) {
            this.name = name;
        }
    }
    
    public int size() {
        return my.size();
    }
    
    
    /**
     * Holds value of property name.
     */
    private String name;
    private Vector all;
    private Vector my;

    
}
