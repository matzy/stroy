// 	    Tips 'N Tricks
//
//Java Tip 66: Control browsers from your Java application
//Tired of using Java HTML widgets that don't quite work? Use this simple class to control your system's native browser
//
//    Summary
//    With a little platform-specific Java code, you can easily use your system's default browser to display any URL. Instead of purchasing a Java HTML widget for a help system, just use this class to control Netscape or Internet Explorer from Windows or Unix. (400 words) 
//
//By Steven Spencer
//
//
//Printer-friendly version Printer-friendly version | Send this article to a friend Mail this to a friend
//
//
//Advertisement
//
//It's great that Java applets and browsers are so tightly integrated, but what if you want to have your Java application display a URL? There's no API call in any Java package that can help you with that.
//
//However, using the exec() command, you can fork a process and issue a command to the underlying OS. The only problem is figuring out just which command needs to be issued to control the browsers on each platform.
//
//On Unix, for Netscape, it was easy to figure this out as you only need to type "netscape -help". If Netscape is already running, the command is this:
//
//netscape -remote openURL(http://www.javaworld.com)
//
//And, if the browser is not already running, you type:
//
//netscape http://www.javaworld.com
//
//Under Windows, it took much exploration and a bit of luck to find something equivalent that wouldn't open a new browser windows for each request. This command, in fact, works better then the Unix command, as you don't have to know whether or not the browser is already running, and the command invokes the default browser -- it is not hard-coded to run a Netscape browser. If Microsoft's Internet Explorer is your default browser, then this will dislay the page in Internet Explorer. To display a page, issue the following command (you can try this in a DOS Shell): rundll32 url.dll,FileProtocolHandler http://www.javaworld.com
//
//  Follow-up Tip!  
//
//from Ryan Stevens: For Mac users, here's an easy way to open a Web page in the default browser, usi ng MRJ 2.2:


//import com.apple.mrj.MRJFileUtils;
import java.io.*;

//class Open {
//String url = "http://www.yourpage.com/";
//
//public static void main(String[] args) { new
//Open(); }
//
//Open() {
//try {
//MRJFileUtils.openURL(url);
//} catch (IOException ex) {}
//  }
//}
//
//from Mark Weakly:
//
//You can launch the command line stuff on Mac similar to Unix (MacOS 8 and 9), except you must place the command-line tokens into a java.lang.String array. The array gets passed to the process exec() method. For example:
//
//
//   String[] commandLine = { "netscape", "http://www.javaworld.com/" };
//   Process process = Runtime.getRuntime().exec(commandLine);
//
//The BrowserControl code
//The class I have written, called BrowserControl takes the above into account and will work for both Windows and Unix platforms. For the Unix platform, you must have Netscape installed and in your path in order for this to work unmodified. If you're a Mac user and know how to invoke a browser from within a Java application, let me know...
//
//To display a page in your default browser, just call the following method from your application:
//
//
//    BrowserControl.displayURL("http://www.javaworld.com")
//
//Note: You must include the URL protocol ("http://" or "file://").
//
//Here is the code for BrowserControl.java:
//

import java.io.IOException;

/**
* A simple, static class to display a URL in the system browser.


*
* Under Unix, the system browser is hard-coded to be 'netscape'.
* Netscape must be in your PATH for this to work.  This has been
* tested with the following platforms: AIX, HP-UX and Solaris.


*
* Under Windows, this will bring up the default browser under windows,
* usually either Netscape or Microsoft IE.  The default browser is
* determined by the OS.  This has been tested under Windows 95/98/NT.


*
* Examples:


    * * BrowserControl.displayURL("http://www.javaworld.com")
      *
    * BrowserControl.displayURL("file://c:\\docs\\index.html")
      *
    * BrowserContorl.displayURL("file:///user/joe/index.html");
      * 


* Note - you must include the url type -- either "http://" or
* "file://".
*/public class BrowseToUrl
{
    /**
     * Display a file in the system browser.  If you want to display a
     * file, you must include the absolute path name.
     *
     * @param url the file's url (the url must start with either "http://"
or
     * "file://").
     */
    public static void displayURL(String url)
    {
        boolean windows = isWindowsPlatform();
        String cmd = null;
        try
        {
            if (windows)
            {
                // cmd = 'rundll32 url.dll,FileProtocolHandler http://...'
                cmd = WIN_PATH + " " + WIN_FLAG + " " + url;
                Process p = Runtime.getRuntime().exec(cmd);
            }
            else
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
        catch(IOException x)
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
    public static boolean isWindowsPlatform()
    {
        String os = System.getProperty("os.name");
        if ( os != null && os.startsWith(WIN_ID))
            return true;
        else
            return false;

    }
    /**
     * Simple example.
     */
    public static void main(String[] args)
    {
        displayURL("http://www.javaworld.com");
    }
    // Used to identify the windows platform.
    private static final String WIN_ID = "Windows";
    // The default system browser under windows.
    private static final String WIN_PATH = "rundll32";
    // The flag to display a url.
    private static final String WIN_FLAG = "url.dll,FileProtocolHandler";
    // The default browser under unix.
    private static final String UNIX_PATH = "/home/oc/opt/MozillaFirebird/MozillaFirebird";
    // The flag to display a url.
    private static final String UNIX_FLAG = "-remote openURL";
}