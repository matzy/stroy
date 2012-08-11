package org.openCage.ruleofthree.property;

import org.openCage.lang.listeners.Observer;
import org.openCage.ruleofthree.Property;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/6/12
 * Time: 7:49 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ListProperty<T> extends Property, Observer, List<T> {
}
