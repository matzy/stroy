package org.openCage.stjx;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Sep 8, 2010
 * Time: 2:03:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class StjxDescr {

    public static void main(String[] args) {


        Stjx stjx = new Stjx("Stjx");

        stjx.struct( "Stjx").list("attributes").of("Attributes");
    }

}
