package org.openCage.ruleofthree.property;

import org.openCage.kleinod.observe.Observer;
import org.openCage.ruleofthree.Property;
import org.openCage.ruleofthree.ThreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/8/12
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MapProperty<T> extends Property, ThreeMap<T>, Observer {
}
