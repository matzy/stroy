package org.openCage.artig;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;

public class Fausterize implements ArtifactProvider, Provider<Artifact> {

    private final Project proj;
    private final Artifact fausterize;

    @Inject
    public Fausterize() {
        proj = new UI().getProject();
        proj.include( new IO().getProject());

        fausterize = proj.module( getClass(), "openCage", "openCage-fausterize" ).
                version( "0.10.0" ).
                address( "http://stroy.wikidot.com/fausterize", "stroy.wikidot.com").
                mpl11().
                author( proj.author( "Stephan Pfab" ) ).
                contributer( proj.author( "Misa Inabe")).
                contributer( proj.author( "Miguel Cuadron Marion")).
                contributer( proj.author( "Johann Wolfgang von Goethe (via Project Guttenberg)")).
                contributer( proj.author( "Scott Schulz")).
                email( "mailto:openCage@gmail.com" ).
                iconResourcePath( "faust.png" ).
                deployApp().
                mainClass( "org.openCage.gpad.UIMain" ).
                descriptionShort( "a small encrypting cute texteditor, based on one-time pads" ).
                language( "English" ).
                language( "German" ).
                java6().
                screenshotUrl( "http://stroy.wikidot.com/local--files/screenshots/fausterize-0.6.png").
                iconUrl( "http://stroy.wikidot.com/local--files/screenshots/faust-small.png").
                downloadUrl( "http://stroy.wikidot.com/local--files/download" ).
                depends( proj.get("openCage", "openCage-property")).
                depends( proj.get("openCage", "openCage-localization")).
                depends( proj.get("openCage", "openCage-io")).
                depends( proj.get("openCage", "openCage-lang")).
                depends( proj.get("openCage", "openCage-ui")).
                depends( proj.get("net.java.dev.designgridlayout", "designgridlayout")).
                depends( proj.external( "args4j", "args4j" ).
                    version("2.0.12").
                    descriptionShort( "Argument parsing as it should be done (via annotations)" ).
                    address( "https://args4j.dev.java.net/", "dev.java.net").
                    mit()).
                depends( proj.external("eu.medsea.mimeutil", "mime-util").
                        descriptionShort( "mime type detection utility, works via extension and/or content").
                        address( "http://www.medsea.eu/mime-util/", "medsea.eu" ).
                        apache2().
                        version( "2.1.3" ).
                        depends( proj.external( "org.slf4j", "slf4j-api" ).
                            descriptionShort( "The simple logging facade for java abstracts several java logging implementations").
                            address( "http://www.slf4j.org/", "slf4.org" ).
                            mit().
                            version("1.5.6")).
                        depends( proj.external( "org.slf4j", "slf4j-log4j12" ). // runtime
                            descriptionShort( "The simple logging facade for java abstracts several java logging implementations").
                            address( "http://www.slf4j.org/", "slf4.org" ).
                            mit().
                            version("1.5.6"))
                ).
                testDepends( proj.get( "junit", "junit" ));
    }


    @Override
    public Artifact get() {
        return fausterize;
    }

    @Override
    public Artifact getArtifact() {
        return fausterize;
    }

    @Override
    public Project getProject() {
        return proj;
    }
}
