package org.openCage.stroy2;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jun 28, 2009
 * Time: 10:15:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stroy {

    public Stroy( String left, String right ) {
        Tsak task = new TsakImpl(); // todo guice
        final NoedBuilder noedBuilder = new FSNoedBuilder( task, left );
        final NoedBuilder noedBuilder2 = new FSNoedBuilder( task, right );

        noedBuilder.go();
        noedBuilder2.go();
    }
}
