package org.openCage.stjx;

public class FausterizeProps {

    public static void main(String[] args) {
        Stjx stjx = new Stjx( "Fausterize" );

        stjx.struct("Fausterize").
                locale( "theLocale" ).integer( "cursor");
    }
}
