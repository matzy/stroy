//package org.openCage.property;
//
//import org.openCage.appstd.AppStdArtifact;
//import org.openCage.lang.LangArtifact;
//import org.openCage.lang.artifact.Artifact;
//import org.openCage.lang.artifact.ArtifactProvider;
//import org.openCage.lang.artifact.Project;
//
//public class PropertyArtifact implements ArtifactProvider{
//
//    private final Project proj;
//    private final Artifact property;
//
//    public PropertyArtifact() {
//        proj = new LangArtifact().getProject();
//        proj.include( new AppStdArtifact().getProject());
//
//        property = proj.module( getClass(), "openCage", "openCage-property" ).
//                version( "0.1.0" ).
//                address( "http://stroy.wikidot.com", "stroy.wikidot.com" ).
//                mpl11().
//                depends( proj.get("openCage", "openCage-lang")).
//                depends( proj.external("commons-io", "commons-io").
//                    apache2().
//                    descriptionShort( "Apache IO tools. e.g. tools to cleanly close resources." ).
//                    address( "http://commons.apache.org/io", "apache").
//                    version( "1.4" )).
//                            depends( proj.external( "com.thoughtworks.xstream", "xstream" ).
//                            version( "1.3.1" ).
//                            descriptionShort( "The java to XML serialization library." ).
//                            address( "http://xstream.codehaus.org/", "codehaus.org" ).
//                            bsd()).
//                depends( proj.module( getClass(), "openCage", "openCage-io").
//                        address( "http://stroy.wikidot.com", "stroy.wikidot.com" ).
//                        version( "0.1.0").
//                        mpl11().
//                        depends( proj.get("openCage", "openCage-lang")).
//                        depends( proj.get("commons-lang", "commons-lang" ) ).
//                        depends( proj.external( "munchsoft", "sys" ).
//                            version("1.0").
//                            descriptionShort( "Tool to help make java apps behave and look like native osx apps. e.g. Menus in menu bar, Preferences open on command-, ... " ).
//                            address( "http://www.muchsoft.com/", "munchsoft.com" ).
//                            licence( "MuchSoft" ))).
//                testDepends( proj.get( "junit", "junit" )).
//                depends( proj.get( "openCage", "openCage-appstd" )).
//                depends( proj.external( "com.google.inject", "guice" ).
//                        apache2().
//                        java6p().
//                        descriptionShort( "Google dependency injection lib in pure java.").
//                        address( "http://code.google.com/p/google-guice/", "code.google" ).
//                        version( "2.0" ).
//                        depends( proj.external( "aopalliance", "aopalliance" ).
//                            version( "1.0" ).
//                            address( "http://aopalliance.sourceforge.net/", "sourceforge.net" ).
//                            publicDomain()));
//
//
//    }
//
//    @Override
//    public Artifact getArtifact() {
//        return property;
//    }
//
//    @Override
//    public Project getProject() {
//        return proj;
//    }
//}
