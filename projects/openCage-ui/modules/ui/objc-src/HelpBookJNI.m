/*
**    JNI hook for involking "NSApplication showHelp:"
**
**   Notice that the function name is created using the string "Java_" and the package name
**   using an underscore instead of a period, then the class name of bridge file. Finally the 
**   name of the 'public static native' method is appended.
*/ 

#import <JavaVM/jni.h>
#import <Cocoa/Cocoa.h>

JNIEXPORT void JNICALL Java_org_openCage_stroy_ui_help_HelpBookLauncher_launch( JNIEnv *env, jclass clazz) {
	[[NSApplication sharedApplication] performSelectorOnMainThread:@selector(showHelp:) withObject:NULL waitUntilDone:NO];
}
