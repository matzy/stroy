package org.openCage.peleph;

import com.sun.jndi.dns.DnsName;
import org.jetbrains.annotations.NotNull;
import org.openCage.lang.Once;

import java.util.ArrayList;
import java.util.List;

public class Artifact {

    private final String name;
    private final String  groupId;

    private final Once<Licence> licence = new Once<Licence>( Licence.apache2() );
    private final Once<Version>  version = new Once<Version>( Version.parse("0.0.1") );
    private final Once<JavaVersion> javaVersion = new Once<JavaVersion>( JavaVersion.v6plus );
    private final Once<String> descriptionShort = new Once<String>( "a program doing foo" );
    private final Once<String> descriptionFull = new Once<String>( "a lengthier description of the prog" );
    private final Once<String> iconResourceOSX = new Once<String>( "" );

    private List<Artifact> compileDeps = new ArrayList<Artifact>();
    private List<Artifact> testDeps = new ArrayList<Artifact>();
    private List<Artifact> knowhowDeps = new ArrayList<Artifact>();

    private final List<Author> contributors = new ArrayList<Author>();
    private final List<Author> authors = new ArrayList<Author>();
    private Once<EmailAddress> email = new Once<EmailAddress>( new EmailAddress( "mailto:anonymous" ));
    private Once<WebPage> webpage = new Once<WebPage>( new WebPage( "http://404" ));

    Artifact( @NotNull String groupId, @NotNull String name ) {
        this.name = name;
        this.groupId = groupId;
    }

    public Artifact mpl11() {
        licence.set( Licence.mpl11() );
        return this;
    }

    public Artifact apache2() {
        licence.set( Licence.apache2() );
        return this;
    }

    public Artifact withAuthor( String name ) {
        return this;
    }

    public Artifact version( String ver) {
        version.set( Version.parse( ver ));

        return this;
    }

    public Artifact iconResourceOSX( String path ) {
        if ( !path.endsWith(".icns")) {
            throw new IllegalArgumentException( "osx icon ends must end with .icns" );
        }

        
        return this;
    }

    public Artifact root( String path ) {
        return this;
    }

    public Artifact java6p() {
        javaVersion.set( JavaVersion.v6plus);
        return this;
    }

    public Artifact descriptionShort( String str ) {
        descriptionShort.set( str );
        return this;
    }

    public Artifact descriptionFull( String str ) {
        descriptionFull.set( str );
        return this;
    }

    public Artifact depends( Artifact artifact, Scope scope ) {
        return this;
    }


    public String gettName() {
        return name;
    }

    public Licence gettLicence() {
        return licence.get();
    }


    public String getGroupId() {
        return groupId;
    }

    public Artifact depends( Artifact artifact ) {
        compileDeps.add( artifact );
        return this;
    }

    public Artifact testDepends(Artifact artifact ) {
        testDeps.add( artifact );
        return this;
    }

    public Artifact knowHowDepends(Artifact artifact ) {
        knowhowDeps.add( artifact );
        return this;
    }

    public List<Artifact> getCompileDependencies() {
        return compileDeps;
    }

    public Version getVersion() {
        return version.get();
    }

    public Artifact address( String page, String shrt ) {
        webpage.set( new WebPage( page ).shrt(shrt) );
        return this;
    }

    public Artifact author(Author author) {
        authors.add( author );
        return this;
    }

    public Artifact contributer(Author contr) {
        contributors.add( contr ) ;
        return this;
    }

    public Artifact email(String addr) {
        email.set( new EmailAddress( addr ));
        return this;
    }
}
