package org.openCage.osashosa;

import com.google.inject.BindingBuilder;

public class StackList {
    
    private StackList      link;
    private BindingBuilder top;

    public StackList( BindingBuilder top, StackList link ) {
        this.top = top;
        this.link = link;
    }
    
    public boolean contains( BindingBuilder key ) {
        StackList ptr = this;

        while( true ) {
            if ( ptr == null ) {
                return false;
            }

            if ( ptr.top.equals(key)) {
                return true;
            }

            ptr = ptr.link;
        }
    }

    @Override
    public String toString() {

        String ret = "[ ";
        StackList ptr = this;
        while( true ) {
            if ( ptr == null ) {
                break;
            }

            ret += ptr.top + ", ";

            ptr = ptr.link;
        }
        
        return ret + "]"; 
    }
}
