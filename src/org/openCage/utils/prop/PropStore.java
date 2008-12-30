package org.openCage.utils.prop;

import org.openCage.utils.persistence.Persistable;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 30, 2008
 * Time: 1:23:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PropStore extends Persistable {

    public Prop   get( String key );
    public void   init( String key, Prop val );
}
