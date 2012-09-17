package org.openCage.stroy.graph.matching;

import org.openCage.kleinod.collection.ReiHashMap;
import org.openCage.kleinod.type.TypeLit;
import org.openCage.stroy.task.MatchingTask;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 9/14/12
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class TypedTasks<T> {

    private Map<String, MatchingTask<T>> tasks = new ReiHashMap<String, MatchingTask<T>>( new TypeLit<String>(){});


}
