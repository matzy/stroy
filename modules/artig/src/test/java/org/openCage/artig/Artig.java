package org.openCage.artig;

import net.jcip.annotations.Immutable;
import org.openCage.lang.artifact.Artifact;
import org.openCage.lang.artifact.ArtifactProvider;
import org.openCage.lang.artifact.Project;

@Immutable
public class Artig implements ArtifactProvider {

    private final Artifact lang;
    private final Project  proj;

    public Artig() {
        proj = Project.get( "stroy" );
        proj.include( new Lang().getProject());
        proj.include( new IO().getProject() );

        lang = proj.module( getClass(), "openCage", "openCage-artig" ).
                mpl11().
                java6().
                version( "0.0.1").
                author( proj.author( "Stephan Pfab" ).email( "mailto:openCag@gmail.com" )).
                email( "mailto:openCag@gmail.com" ).
                address( "http://stroy.wikidot.com", "wikidot.com").
                descriptionShort( "a library with small java language level additions" ).
                depends( proj.get( "openCage", "openCage-lang" )).
                depends( proj.get( "openCage", "openCage-io" )).
                depends( proj.external("commons-io", "commons-io").
                        apache2().
                        descriptionShort( "Apache IO tools. e.g. tools to cleanly close resources." ).
                        address( "http://commons.apache.org/io", "apache").
                        version( "1.4" )).
//                depends( proj.external( "com.thoughtworks.xstream", "xstream" ).
//                        version( "1.3.1" ).
//                        descriptionShort( "The java to XML serialization library." ).
//                        address( "http://xstream.codehaus.org/", "codehaus.org" ).
//                        bsd());
            depends( proj.external( "com.google.inject", "guice" ).
                apache2().
                java6p().
                descriptionShort( "Google dependency injection lib in pure java.").
                address( "http://code.google.com/p/google-guice/", "code.google" ).
                version( "2.0" ).
                depends( proj.external( "aopalliance", "aopalliance" ).
                    version( "1.0" ).
                    address( "http://aopalliance.sourceforge.net/", "sourceforge.net" ).
                    publicDomain())).
                testDepends( proj.get( "junit", "junit" ));

    }

    public Artifact getArtifact() {
        return lang;
    }

    public Project getProject() {
        return proj;
    }
}
