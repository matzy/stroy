package org.openCage.notabug;

import com.google.inject.Inject;
import com.sun.tools.javac.code.Type;
import org.openCage.lang.Forall;
import org.openCage.lang.functions.F1;
import org.openCage.ruleofthree.ThreeKey;
import org.openCage.ruleofthree.property.PropertyStore;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/23/12
 * Time: 5:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tasks {

    private final PropertyStore propertyStore;


    @Inject
    public Tasks(PropertyStore propertyStore) {
        this.propertyStore = propertyStore;
    }

    public Task create( String title ) {
        Task task = Task.next( title );

        propertyStore.add( new ThreeKey( "nab" + task.getId().toString()), task );

        return task;
    }

    public List<Task> getAll() {
        return propertyStore.getAll( Task.class );
    }

    public Task findUnique(String find) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public List<Task> find( final String match ) {
        return Forall.forall( getAll()).skip( new F1<Boolean, Task>() {
            @Override
            public Boolean call(Task task) {

                if ( task.getName().contains(match)) {
                    return false;
                }

                if ( task.getId().toString().startsWith(match)) {
                    return false;
                }

                return true;
            }
        }).toList();
    }

}
