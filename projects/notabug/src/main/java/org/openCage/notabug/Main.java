package org.openCage.notabug;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.openCage.lang.Forall;
import org.openCage.lang.functions.VF1;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/23/12
 * Time: 7:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new RuntimeModule());



        //PropertyStore propertyStore = injector.getInstance(PropertyStore.class );

//        Task task = propertyStore.get( S("123"), Task.class );
//
//        System.out.println(task);

//        List<Task> tasks = propertyStore.getAll( Task.class );
//
//        Forall.forall(tasks).skip( new
//                                   F1<Boolean, Task>() {
//                                       @Override
//                                       public Boolean call(Task task) {
//                                           return task.getState() == State.fixed;
//                                       }
//                                   }).act(new VF1<Task>() {
//            @Override
//            public void call(Task task) {
//                System.out.println(task);
//            }
//        });

        Tasks tasks = injector.getInstance( Tasks.class );

        tasks.create( "nice cli" );

        Forall.forall( tasks.getAll()).act( new VF1<Task>() {
            @Override
            public void call(Task task) {
                System.out.println( task );
            }
        });



        System.exit(0);
    }

}
