package org.openCage.ruleofthree.property;

import org.openCage.ruleofthree.Three;
import org.openCage.ruleofthree.ThreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/6/12
 * Time: 8:10 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PropertyStoreRW {

    void             setDirty();
    ThreeMap<Three>  getThreedProps();
    ThreeMap<Object> getProps();
}

