//package org.openCage.appstd;
//
//import com.google.inject.Inject;
//import com.google.inject.Provider;
//import org.openCage.IOArtifact;
//import org.openCage.lang.LangArtifact;
//import org.openCage.lang.artifact.Artifact;
//import org.openCage.lang.artifact.ArtifactProvider;
//import org.openCage.lang.artifact.Project;
//
//public class AppStdArtifact implements ArtifactProvider, Provider<Artifact> {
//
//        private final Project proj;
//        private final Artifact appstd;
//
//    @Inject
//        public AppStdArtifact() {
//            proj = new LangArtifact().getProject();
//            proj.include( new IOArtifact().getProject() );
//
//            appstd = proj.module( getClass(), "openCage", "openCage-appstd" ).
//                    version( "0.0.1" ).
//                    depends( proj.get( "openCage", "openCage-lang" ) ).
//                    depends( proj.get( "openCage", "openCage-io" )).
//                    depends( proj.external( "com.google.inject", "guice" ).
//                            apache2().
//                            java6p().
//                            descriptionShort( "Google dependency injection lib in pure java.").
//                            address( "http://code.google.com/p/google-guice/", "code.google" ).
//                            version( "2.0" ).
//                            depends( proj.external( "aopalliance", "aopalliance" ).
//                                version( "1.0" ).
//                                address( "http://aopalliance.sourceforge.net/", "sourceforge.net" ).
//                                publicDomain()));
////                    depends( new SipArtifact().getArtifact() ).
//        }
//
//        @Override
//        public Artifact get() {
//            return appstd;
//        }
//
//        @Override
//        public Artifact getArtifact() {
//            return appstd;
//        }
//
//        @Override
//        public Project getProject() {
//            return proj;
//        }
//    }
