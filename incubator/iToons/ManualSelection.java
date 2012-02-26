/*
 * ManualSelection.java
 *
 * Created on 19. September 2004, 09:26
 */

import java.util.*;
/**
 *
 * @author  Stephan
 */
public class ManualSelection implements Selection{
    
    /** Creates a new instance of ManualSelection */
    public ManualSelection( String name ) {
        this.name = name;
        comics    = new Vector();
    }
    
    public ManualSelection( List lst ) {
        comics    = new Vector();
        Symbol sName    = Symbol.get( "Name");

        for ( int i = 1; i < lst.size(); i++) {
            List tag = (List)lst.get(i);
            
            if ( sName == tag.get(0)) {
                name = (String)tag.get(1);
            } else {
                
            }
        }        
        
    }
    
    public boolean belongs(Comic comic) {
        return false;
    }
    
    public Comic get(int idx) {
        return (Comic)comics.get(idx);
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isSystem() {
        return false;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int size() {
        return comics.size();
    }
    
    public void update() {
    }
    
    public String toString(){
        String ret = "(ManualSelection (Name \"" + name + "\")" ;
        for( int i = 0; i < comics.size(); ++i ){
            ret += " \"" + ((Comic)comics.get(i)).getID() + "\"";
        }
        
        ret += ")\n";
        
        return ret;
    }
    

    private String name;
    private Vector comics;
}
