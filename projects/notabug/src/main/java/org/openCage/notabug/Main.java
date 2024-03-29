package org.openCage.notabug;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.openCage.kleinod.lambda.F1;

import static org.openCage.kleinod.collection.Forall.forall;

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

        if ( args.length == 0 ) {
            System.out.println("Usage: ...");
            System.exit(0);
        }

        if ( args[0].startsWith("l")) {


            final ListArgs la = new ListArgs();
            CmdLineParser parser = new CmdLineParser(la);
            parser.setUsageWidth(80); // width of the error display area

            try {
                parser.parseArgument(args);
            } catch (CmdLineException e) {
                System.err.println(e.getMessage());
                System.err.println("Usage l[ist] [options...] ");
                parser.printUsage( System.err );
                // print the list of available options
                parser.printUsage(System.err);
                System.err.println();
                System.exit(1);
            }

            forall( tasks.getAll() ).skip( new F1<Boolean, Task>() {
                @Override
                public Boolean call(Task task) {
                    return !la.matches(task);
                }
            }).println();

        }

        if ( args[0].startsWith("n")) {


            newTask(args, tasks);

        }


//        tasks.create( "find tasks" );
//
//        Forall.forall( tasks.getAll()).act( new VF1<Task>() {
//            @Override
//            public void call(Task task) {
//                System.out.println( task );
//            }
//        });

//        forall(tasks.find("find")).act( new VF1<Task>() {
//            @Override
//            public void call(Task task) {
//                System.out.println(task);
//            }
//        });



        System.exit(0);
    }

    private static void newTask(String[] args, Tasks tasks) {
        final NewArgs newArgs = new NewArgs();
        CmdLineParser parser = new CmdLineParser(newArgs);
        parser.setUsageWidth(80); // width of the error display area

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("notabug [options...] arguments...");
            parser.printUsage( System.err );
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();
            System.exit(1);
        }

        Task task = tasks.create( newArgs.getName());
        if ( newArgs.getKind() != null ) {
            task.setKind( newArgs.getKind() );
        }
    }

}
