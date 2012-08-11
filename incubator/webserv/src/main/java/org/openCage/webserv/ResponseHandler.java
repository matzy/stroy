package org.openCage.webserv;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.openCage.webserv.XmlBuilder.*;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 4/1/12
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseHandler extends Thread {

    final private static Logger LOG = Logger.getLogger(ResponseHandler.class.getName());


    private final BufferedReader input;
    private final DataOutputStream output;
    private webserver_starter message_to; //the starter class, needed for gui


    public ResponseHandler(BufferedReader input, DataOutputStream output, webserver_starter message_to) {
        this.input = input;
        this.output = output;
        this.message_to = message_to;

    }

    @Override
    public void run() {
        http_handler();
    }

    private void sendToLog(String message) { //an alias to avoid typing so much!
        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine(message);
        }
        message_to.send_message_to_window(message);
    }


    //our implementation of the hypertext transfer protocol
    //its very basic and stripped down
    private void http_handler() {
        int method = 0; //1 get, 2 head, 0 not supported
        String http = new String(); //a bunch of strings to hold
        String path = new String(); //the various things, what http v, what path,
        String file = new String(); //what file
        String user_agent = new String(); //what user_agent
        try {
            //This is the two types of request we can handle
            //GET /index.html HTTP/1.0
            //HEAD /index.html HTTP/1.0
            String tmp = input.readLine(); //read from the stream
            String tmp2 = new String(tmp);
            tmp.toUpperCase(); //convert it to uppercase
            if (tmp.startsWith("GET")) { //compare it is it GET
                method = 1;
            } //if we set it to method 1
            if (tmp.startsWith("HEAD")) { //same here is it HEAD
                method = 2;
            } //set method to 2

            if (method == 0) { // not supported
                try {
                    output.writeBytes(construct_http_header(501, 0));
                    output.close();
                    return;
                } catch (Exception e3) { //if some error happened catch it
                    sendToLog("error:" + e3.getMessage());
                } //and display error
            }
            //}

            //tmp contains "GET /index.html HTTP/1.0 ......."
            //find first space
            //find next space
            //copy whats between minus slash, then you get "index.html"
            //it'sendToLog a bit of dirty code, but bear with me...
            int start = 0;
            int end = 0;
            for (int a = 0; a < tmp2.length(); a++) {
                if (tmp2.charAt(a) == ' ' && start != 0) {
                    end = a;
                    break;
                }
                if (tmp2.charAt(a) == ' ' && start == 0) {
                    start = a;
                }
            }
            path = tmp2.substring(start + 2, end); //fill in the path
        } catch (Exception e) {
            sendToLog("errorr" + e.getMessage());
        } //catch any exception

        //path do now have the filename to what to the file it wants to open
        sendToLog("\nClient requested:" + new File(path).getAbsolutePath() + "\n");
        FileInputStream requestedfile = null;

        try {
            //NOTE that there are several security consideration when passing
            //the untrusted string "path" to FileInputStream.
            //You can access all files the current user has read access to!!!
            //current user is the user running the javaprogram.
            //you can do this by passing "../" in the url or specify absoulute path
            //or change drive (win)

            //try to open the file,

            if ( new File( path ).isDirectory() ) {
                output.writeBytes(construct_http_header(200, 5));
//                output.writeBytes( "<html><head><title>404</title></head><body><h1>File not found</h1></body> </html>\r\n" );

                final String fpath = path;
                String foo =                         html(
                        head(title(path)),
                        body(h1("it's a dir"),
                                ulist(new F1<XmlBuilder, String>() {
                                    public XmlBuilder call(String txt) {
                                        return href( "http://127.0.0.1:9090/" + fpath, txt);
                                    }
                                },
                                        new File(path).list())))
                        .toString();


                output.writeBytes(
                        html(
                                head(title(path)),
                                body(h1("it's a dir"),
                                        ulist(new F1<XmlBuilder, String>() {
                                                    public XmlBuilder call(String txt) {
                                                        return href( "http://127.0.0.1:9090/" + fpath  + "/" + txt, txt);
                                                    }
                                                },
                                                new File(path).list())))
                                .toString());
                //close the stream
                output.close();
                return;

            } else {

            }
            requestedfile = new FileInputStream(path);

        } catch (Exception e) {

            try {
                //if you could not open the file send a 404
                output.writeBytes(construct_http_header(404, 5));
//                output.writeBytes( "<html><head><title>404</title></head><body><h1>File not found</h1></body> </html>\r\n" );
                output.writeBytes(
                        html(
                                head(title("404")),
                                body(h1("File not found: " + path)))
                                .toString());
                //close the stream
                output.close();
            } catch (Exception e2) {
            }
            sendToLog("error " + e.getMessage());
            return;
        } //print error to gui

        //happy day scenario
        try {
            int type_is = 0;
            //find out what the filename ends with,
            //so you can construct a the right content type
            if (path.endsWith(".zip")) {
                type_is = 3;
            }
            if (path.endsWith(".jpg") || path.endsWith(".jpeg")) {
                type_is = 1;
            }
            if (path.endsWith(".gif")) {
                type_is = 2;
            }
            if (path.endsWith(".ico")) {
                type_is = 3;
            }
            //write out the header, 200 ->everything is ok we are all happy.
            output.writeBytes(construct_http_header(200, 5));

            //if it was a HEAD request, we don't print any BODY
            if (method == 1) { //1 is GET 2 is head and skips the body
                byte[] buffer = new byte[1024];
                while (true) {
                    //read the file from filestream, and print out through the
                    //client-outputstream on a byte per byte base.
                    int b = requestedfile.read(buffer, 0, 1024);
                    if (b == -1) {
                        break; //end of file
                    }
                    output.write(buffer, 0, b);
                }
                //clean up the files, close open handles

            }
            output.close();
            requestedfile.close();
        } catch (Exception e) {
        }

    }

    //this method makes the HTTP header for the response
    //the headers job is to tell the browser the result of the request
    //among if it was successful or not.
    private String construct_http_header(int return_code, int file_type) {
        String s = "HTTP/1.0 ";
        //you probably have seen these if you have been surfing the web a while
        switch (return_code) {
            case 200:
                s = s + "200 OK";
                break;
            case 400:
                s = s + "400 Bad Request";
                break;
            case 403:
                s = s + "403 Forbidden";
                break;
            case 404:
                s = s + "404 Not Found";
                break;
            case 500:
                s = s + "500 Internal Server Error";
                break;
            case 501:
                s = s + "501 Not Implemented";
                break;
        }

        s = s + "\r\n"; //other header fields,
        s = s + "Connection: close\r\n"; //we can't handle persistent connections
        s = s + "Server: WebServ v0.2\r\n"; //WebServ name

        //Construct the right Content-Type for the header.
        //This is so the browser knows what to do with the
        //file, you may know the browser dosen't look on the file
        //extension, it is the servers job to let the browser know
        //what kind of file is being transmitted. You may have experienced
        //if the WebServ is miss configured it may result in
        //pictures displayed as text!
        switch (file_type) {
            //plenty of types for you to fill in
            case 0:
                break;
            case 1:
                s = s + "Content-Type: image/jpeg\r\n";
                break;
            case 2:
                s = s + "Content-Type: image/gif\r\n";
                break;
            case 3:
                s = s + "Content-Type: application/x-zip-compressed\r\n";
                break;
            case 4:
                s = s + "Content-Type: image/x-icon\r\n";
                break;
            default:
                s = s + "Content-Type: text/html\r\n";
                break;
        }

        ////so on and so on......
        s = s + "\r\n"; //this marks the end of the httpheader
        //and the start of the body
        //ok return our newly created header!
        return s;
    }


}
