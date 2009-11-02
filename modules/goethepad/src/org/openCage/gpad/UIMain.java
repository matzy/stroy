package org.openCage.gpad;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 1, 2009
 * Time: 8:14:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class UIMain {


    public static void main(String[] args) {
        Injector injector = Guice.createInjector( new FaustWiring() );
        FaustUI faust = injector.getInstance( FaustUI.class );
//
//        ApplicationBuilder ab = injector.getInstance( ApplicationBuilder.class );

        faust.setVisible(true);
    }
}
