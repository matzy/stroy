//package org.openCage.ui;
//
//import org.openCage.IOArtifact;
//import org.openCage.lang.artifact.Artifact;
//import org.openCage.lang.artifact.ArtifactProvider;
//import org.openCage.lang.artifact.Project;
//import org.openCage.localization.LocalizationArtifact;
//import org.openCage.property.PropertyArtifact;
//
//public class UIArtifact implements ArtifactProvider {
//
//    private final Project proj;
//    private final Artifact ui;
//
//    public UIArtifact() {
//        proj = new PropertyArtifact().getProject();
//        proj.include( new IOArtifact().getProject());
//        proj.include( new LocalizationArtifact().getProject());
//
//        ui = proj.module( getClass(), "openCage", "openCage-ui" ).
//                version( "0.1.0" ).
//                address( "http://stroy.wikidot.com", "stroy.wikidot.com").
//                mpl11().
//                depends( proj.get("openCage", "openCage-property")).
//                depends( proj.get("openCage", "openCage-localization")).
//                depends( proj.get("openCage", "openCage-io")).
//                depends( proj.get("openCage", "openCage-lang")).
//                depends( proj.external( "com.google.inject", "guice" ).
//                        apache2().
//                        java6p().
//                        descriptionShort( "Google dependency injection lib in pure java.").
//                        address( "http://code.google.com/p/google-guice/", "code.google" ).
//                        version( "2.0" ).
//                        depends( proj.external( "aopalliance", "aopalliance" ).
//                            version( "1.0" ).
//                            address( "http://aopalliance.sourceforge.net/", "sourceforge.net" ).
//                            publicDomain())).
//                depends( proj.external( "net.java.dev.designgridlayout", "designgridlayout" ).
//                        depends( proj.external( "net.java.dev.swing-layout", "swing-layout" ).
//                                address( "https://swing-layout.dev.java.net/", "dev.java").
//                                lgpl3().
//                                version("1.0.2")).
//                        descriptionShort( "Swing layout manager based on the use of canonical grids for user interface design." ).
//                        address( "https://designgridlayout.dev.java.net/", "dev.java").
//                        apache2().
//                        version( "0.9" )).
//                depends( proj.external( "fifesoft", "RSyntaxTextArea" ).
//                        descriptionShort( "A textarea subclass enhanced by a lot of typical texteditor feature").
//                        address( "http://fifesoft.com/rsyntaxtextarea/", "fifesoft.org" ).
//                        lgpl3().
//                        version( "1.4.1" )).
//                depends( proj.external( "com.jgoodies", "binding" ).
//                        descriptionShort( "Twoway binding of domain objects and ui objects. Changes to the domain object are displayed by the ui object, changes to the ui object modify the domain object." ).
//                        address( "http://www.jgoodies.com/downloads/libraries.html", "jgoodies").
//                        bsd().
//                        version( "2.0.6" )).
//                depends( proj.external("javagraphics", "preferencepanel").
//                        descriptionShort( "A OSX style preference panel." ).
//                        address( "https://javagraphics.dev.java.net/", "dev.java").
//                        bsd().
//                        version( "1.0" )).
//                depends( proj.external( "macwidgets", "macwidgets" ).
//                        descriptionShort( "Library with OSX inspired swing components. e.g. a toolbar that blends in with the title bar, better buttons ..." ).
//                        address( "http://code.google.com/p/macwidgets/", "code.google.com").
//                        lgpl3().
//                        version( "0.9.5" ).
//                        depends( proj.external( "com.jgoodies", "forms" ).
//                        descriptionShort( "JGoodies Forms library," ).
//                        address( "http://www.jgoodies.com/downloads/libraries.html", "jgoodies").
//                        bsd().
//                        version( "1.2.1" ))).
//                depends( proj.external( "org.json", "json" ).
//                        version("20090211").
//                        address( "http://www.json.org/java/index.html", "json.org").
//                        jsonlicence()).
//                depends( proj.get( "com.intellij", "annotations" )).
//                testDepends( proj.get( "junit", "junit" ));
//    }
//
//    @Override
//    public Artifact getArtifact() {
//        return ui;
//    }
//
//    @Override
//    public Project getProject() {
//        return proj;
//    }
//}
