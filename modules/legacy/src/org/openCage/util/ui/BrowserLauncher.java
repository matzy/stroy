package org.openCage.util.ui;

import java.lang.reflect.*;

import java.net.*;

/**
* A Simple, static class to display a URL in the system browser.
*
*
* Under Unix, the system browser is hard-coded to be 'netscape'.
* Netscape must be in your PATH for this to work.  This has been
* tested with the following platforms: AIX, HP-UX and Solaris.
*
*
*
* Under Windows, this will bring up the default browser under windows,
* usually either Netscape or Microsoft IE.  The default browser is
* determined by the OS.  This has been tested under Windows 95/98/NT.
*
* we should add some os x support
*
* Note - you must include the url type -- either "http://" or
* "file://".
*/

// TODO !!!!

public class BrowserLauncher
{
    // flag for specifying windows os
    private static final int    UNKNOWN_OS  = -1;
    private static final int    WINDOWS     = 0;
    private static final int    MAC_OS_X    = 1;

    // Used to identify the windows platform.
    private static final String WIN_ID = "Windows";
    // The default system browser under windows.
    private static final String WIN_PATH = "rundll32";
    // The flag to display a url.
    private static final String WIN_FLAG = "url.dll,FileProtocolHandler";
    // The default browser under unix.
    private static final String UNIX_PATH = "netscape";
    // The flag to display a url.
    private static final String UNIX_FLAG = "-remote openURL";

    /**
     * Display a file in the system browser.  If you want to display a
     * file, you must include the absolute path name.
     *
     * @param launchUrl the file's url (the url must start with either "http://"
or
     * "file://").
     */

    public static void displayURL( String launchUrl ) throws Exception {

        URL url = new URL( launchUrl );

        displayURL( url );
    }

    public static void displayURL( URL launchUrl ) throws NullPointerException
    {


        if ( launchUrl == null )
          throw new NullPointerException("please specify url for BrowserLauncher.displayURL");

        String url = launchUrl.toExternalForm();
        int os = getPlatformID();


        String cmd = null;
        try
        {
            switch ( os ) {

             case WINDOWS:

                  {
                    if ( url.startsWith( "file:/" ) ) {
                        url = "file:///" + url.substring( 6 );
                    }
                  // cmd = 'rundll32 url.dll,FileProtocolHandler http://...'
                  cmd = WIN_PATH + " " + WIN_FLAG + " " + url;
                  Process p = Runtime.getRuntime().exec(cmd);
                  break;
                  }

              case MAC_OS_X:

                  // if local file has to be opened, add localHost, if not already present
                    if ( url.startsWith( "file:/" ) ) {
                        url = "file:///" + url.substring( 6 );
                    }
                /*
                  if ( url.startsWith( "file:/" ) ) {
                    if ( !url.startsWith( "file:///localhost" ) )
                      url = "file:///localhost" + url.substring( 5);

                  }
                */
                  Class clazz = ClassLoader.getSystemClassLoader().loadClass("com.apple.mrj.MRJFileUtils" );
                  if ( clazz != null ) {
                    Method m = clazz.getMethod( "openURL", new Class[] { String.class }  );
                    if ( m != null ) {
                      Object object = clazz.newInstance();
                      if ( object != null )
                        m.invoke( object, new Object[] { url } );
                    }
                  }
                  break;

              //guess it's unix type os
              default:
              {
                // Under Unix, Netscape has to be running for the "-remote"
                // command to work.  So, we try sending the command and
                // check for an exit value.  If the exit command is 0,
                // it worked, otherwise we need to start the browser.
                // cmd = 'netscape -remote openURL(http://www.javaworld.com)'
                  cmd = UNIX_PATH + " " + UNIX_FLAG + "(" + url + ")";
                  Process p = Runtime.getRuntime().exec(cmd);
                  try
                  {
                    // wait for exit code -- if it's 0, command worked,
                    // otherwise we need to start the browser up.
                    int exitCode = p.waitFor();
                    if (exitCode != 0)
                    {
                        // Command failed, start up the browser
                        // cmd = 'netscape http://www.javaworld.com'
                        cmd = UNIX_PATH + " "  + url;
                        p = Runtime.getRuntime().exec(cmd);
                    }
                  }
                  catch(InterruptedException x)
                  {
                    System.err.println("Error bringing up browser, cmd='" +
                                       cmd + "'");
                    System.err.println("Caught: " + x);
                  }
                }
            }
        }
        catch(Exception x)
        {
            // couldn't exec browser
            System.err.println("Could not invoke browser, command=" + cmd);
            System.err.println("Caught: " + x);
        }
    }
    /**
     * Try to determine whether this application is running under Windows
     * or some other platform by examing the "os.name" property.
     *
     * @return true if this application is running under a Windows OS
     */
    private static int getPlatformID()
    {
        String os = System.getProperty("os.name");
        if ( os != null ) {
          if ( os.startsWith(WIN_ID))
            return WINDOWS;
          else if ( os.equals("Mac OS X") )
            return MAC_OS_X;
        }

        return ( UNKNOWN_OS );
    }


    /**
     * Simple example.
     */
    public static void main(String[] args)
    {
        //displayURL( "http://www.javaworld.com");
        //displayURL("file:/c:/development/Tekadence/Projects/Magik/DebugPlugins/classes/tdoc/package-overview.html" );
    }

}



