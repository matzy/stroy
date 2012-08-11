package org.openCage.webserv;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 4/2/12
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestParser {

    private String request;

    public RequestParser( String request ) {
        this.request = request;
    }

    public boolean isGet() {
        return request.toUpperCase().startsWith("GET");
    }


}
