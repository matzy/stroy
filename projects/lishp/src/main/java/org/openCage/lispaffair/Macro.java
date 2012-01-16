/*
 * Macro.java
 *
 * Created on August 27, 2004, 11:18 AM
 */

package org.openCage.lispaffair;
import java.util.*;

/**
 *
 * @author  SPfab
 */
public interface Macro {

    public Object       expand( List lst );
    public int          argNum();
    public StringBuffer format( StringBuffer toAppendTo );

}
