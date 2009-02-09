package org.openCage.stroy2.find;

import org.apache.commons.cli.*;
import org.openCage.util.lang.Once;
import org.openCage.stroy.algo.tree.TreeFactory;
import org.openCage.stroy.algo.tree.Noed;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Feb 8, 2009
 * Time: 10:59:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class FindClt {


    public static void main( String[] args ) {

        Args arguments = new Args( args );
        if ( !arguments.isOk() ) {
            return;
        }

        TreeFactory tf = null;

        Noed where = tf.create( arguments.getWhere().getAbsolutePath(), false ).build( arguments.getWhere().getAbsolutePath());
    }

    private void run() {
        //To change body of created methods use File | Settings | File Templates.
    }



}
