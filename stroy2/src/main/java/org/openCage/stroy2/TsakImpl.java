package org.openCage.stroy2;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jun 30, 2009
 * Time: 9:21:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class TsakImpl implements Tsak {

    List<Noed> lefts = new ArrayList<Noed>();
    
    public void addNoed(Noed noed) {
        lefts.add( noed );
    }

    public List<Noed> getLefts() {
        return lefts;
    }


}
