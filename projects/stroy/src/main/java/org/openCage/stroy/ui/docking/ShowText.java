package org.openCage.stroy.ui.docking;

import org.openCage.kleinod.collection.Forall;
import org.openCage.kleinod.lambda.F1;
import org.openCage.kleinod.lambda.F2;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/14/12
 * Time: 8:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShowText {

    private static TextEditPane target;

    public static void openAsText( File file ) {


        target.setText(Forall.lines(file).join("", new F2<String, String, String>() {
            @Override
            public String call(String s, String s1) {
                return s + s1;
            }
        }).sep( new F1<String, String>() {
            @Override
            public String call(String s) {
                return s + "\n";
            }
        }).go());


    }

    public static void set( TextEditPane tep ) {
        target = tep;
    }
}
