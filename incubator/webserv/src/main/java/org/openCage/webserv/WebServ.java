package org.openCage.webserv;//package webs;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Title: A simple Webserver Tutorial
 NO warranty, NO guarantee, MAY DO damage to FILES, SOFTWARE, HARDWARE!!
 * Description:
  This is a simple tutorial on making a webserver
  posted on http://turtlemeat.com .
  Go there to read the tutorial!
  This program and sourcecode is free for all, and you can
  copy and modify it as you like, but you should
  give credit and maybe a link to turtlemeat.com,
  you know R-E-S-P-E-C-T. You gotta respect the
  work that has been put down.

 * Copyright:    Copyright (c) 2002
 * Company: TurtleMeat
 * @author: Jon Berg <jon.berg[on_server]turtlemeat.com
 * @version 1.0
 */
//file: WebServ.java
//the real (http) serverclass
//it extends thread so the WebServ is run in a different
//thread than the gui, that is to make it responsive.
//it'sendToLog really just a macho coding thing.
public class WebServ extends Thread {

    final private static Logger LOG = Logger.getLogger( WebServ.class.getName()); 
    
    private webserver_starter message_to; //the starter class, needed for gui
    private int port; //port we are going to listen to
    
//the constructor method
//the parameters it takes is what port to bind to, the default tcp port
//for a httpserver is port 80. the other parameter is a reference to
//the gui, this is to pass messages to our nice interface
  public WebServ(int listen_port, webserver_starter to_send_message_to) {
    message_to = to_send_message_to;
    port = listen_port;

//this makes a new thread, as mentioned before,it'sendToLog to keep gui in
//one thread, WebServ in another. You may argue that this is totally
//unnecessary, but we are gonna have this on the web so it needs to
//be a bit macho! Another thing is that real pro webservers handles
//each request in a new thread. This WebServ dosen't, it handles each
//request one after another in the same thread. This can be a good
//assignment!! To redo this code so that each request to the WebServ
//is handled in its own thread. The way it is now it blocks while
//one client access the WebServ, ex if it transferres a big file the
//client have to wait real long before it gets any response.
    this.start();
  }

    private void sendToLog(String message) { //an alias to avoid typing so much!
        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine( message );
        }
        message_to.send_message_to_window(message);
    }


//this is a overridden method from the Thread class we extended from
  public void run() {
    //we are now inside our own thread separated from the gui.
    ServerSocket serversocket = null;
    //To easily pick up lots of girls, change this to your name!!!
    sendToLog("The simple httpserver v. 0000000000\nCoded by Jon Berg" +
            "<jon.berg[on WebServ]turtlemeat.com>\n\n");
    //Pay attention, this is where things starts to cook!
    try {
      //print/send message to the guiwindow
      sendToLog("Trying to bind to localhost on port " + Integer.toString(port) + "...");
      //make a ServerSocket and bind it to given port,
      serversocket = new ServerSocket(port);
    }
    catch (Exception e) { //catch any errors and print errors to gui
      sendToLog("\nFatal Error:" + e.getMessage());
      return;
    }
    sendToLog("OK!\n");
    //go in a infinite loop, wait for connections, process request, send response
    while (true) {
      sendToLog("\nReady, Waiting for requests...\n");
      try {
        //this call waits/blocks until someone connects to the port we
        //are listening to
        Socket connectionsocket = serversocket.accept();
        //figure out what ipaddress the client commes from, just for show!
        InetAddress client = connectionsocket.getInetAddress();
        //and print it to gui
        sendToLog(client.getHostName() + " connected to WebServ.\n");
        //Read the http request from the client from the socket interface
        //into a buffer.
        BufferedReader input =
            new BufferedReader(new InputStreamReader(connectionsocket.
            getInputStream()));
        //Prepare a outputstream from us to the client,
        //this will be used sending back our response
        //(header + requested file) to the client.
        DataOutputStream output =
            new DataOutputStream(connectionsocket.getOutputStream());

//as the name suggest this method handles the http request, see further down.
//abstraction rules
          new ResponseHandler( input, output, message_to ).start();
      }
      catch (Exception e) { //catch any errors, and print them
        sendToLog("\nError:" + e.getMessage());
      }

    } //go back in loop, wait for next request
  }



} //class phhew caffeine yes please!
