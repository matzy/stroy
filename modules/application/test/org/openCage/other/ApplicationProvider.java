//package org.openCage.other;
//
//import org.openCage.application.protocol.Application;
//import org.openCage.application.protocol.ApplicationBuilder;
//
//import com.google.inject.Inject;
//import com.google.inject.Provider;
//
//public class ApplicationProvider implements Provider<Application> {
//
//	@Inject
//	public ApplicationBuilder ab;
//
//
//	public Application get() {
//        return ab.with( ab.author().name( "me" ).build()).
//	              with( ab.author().name( "you" ).build()).
//	              name( "TestApp" ).
//	              build();
//	}
//
//}
