package org.openCage.hampei;

import org.eclipse.jetty.server.Server;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 25.08.11
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */
public class ServerMain {
    // http://en.wikipedia.org/wiki/Coffee_berry_borer
    // http://wiki.eclipse.org/Jetty/Tutorial/Embedding_Jetty
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(9280);
        server.setHandler(new HelloHandler());

        server.start();
        server.join();
    }
}
