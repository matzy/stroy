package org.openCage.stroy2;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jun 30, 2009
 * Time: 9:49:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class FSNoedBuilder implements NoedBuilder {

    private final Tsak task;
    private final String root;

    public FSNoedBuilder(Tsak task, String path ) {
        this.task = task;
        this.root = path;
    }

    public void go() {
        new Thread() {
            @Override
            public void run() {
                super.run();    //To change body of overridden methods use File | Settings | File Templates.
                System.out.println( root );
                for ( String child : new File(root).list()) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                    System.out.println( root + " - " +child);
                    task.addNoed( new StdNoed(child ));
                }
            }
        }.start();
    }
}
