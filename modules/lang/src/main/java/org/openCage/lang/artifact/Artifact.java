package org.openCage.lang.artifact;

import com.sun.jdi.VirtualMachine;
import com.sun.tools.javac.main.JavaCompiler;
import org.jetbrains.annotations.NotNull;
import org.openCage.lang.Once;

import javax.swing.*;
import javax.xml.stream.events.EntityDeclaration;
import java.security.AlgorithmParameters;
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


    private final Once<String> iconResourcePath = new Once<String>( "" );

    private final Once<Class> base = new Once<Class>( null );


    Artifact( @NotNull String groupId, @NotNull String name ) {
        if ( groupId.isEmpty() ) {
            throw new IllegalArgumentException( "groupId can not be empty" );
        }
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

    public Artifact lgpl() {
        licence.set( Licence.lgpl() );
        return this;
    }

    public Artifact bsd() {
        licence.set( Licence.bsd() );
        return this;
    }

    public Artifact creativeCommosns() {
        licence.set( Licence.creativeCommons() );
        return this;
    }

    public Artifact cpl() {
        licence.set( Licence.cpl());
        return this;
    }

    public Artifact openIfUnchanged() {
        licence.set( Licence.openIf() );
        return this;
    }

    public Artifact mit() {
        licence.set( Licence.mit() );
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artifact)) return false;

        Artifact artifact = (Artifact) o;

        if (groupId != null ? !groupId.equals(artifact.groupId) : artifact.groupId != null) return false;
        if (name != null ? !name.equals(artifact.name) : artifact.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        return result;
    }

    public void validate() {
        if ( !version.isSet()) {
            throw new IllegalStateException( "no version on " + this );
        }

        if ( !webpage.isSet()) {
            throw new IllegalStateException( "no webpage on " + this );
        }

        if ( !licence.isSet()) {
            throw new IllegalStateException( "no licence on " + this );
        }


    }

    @Override
    public String toString() {
        return "Artifact{" +
                "name='" + name + '\'' +
                ", groupId='" + groupId + '\'' +
                ", licence=" + licence +
                ", version=" + version +
                ", javaVersion=" + javaVersion +
                ", descriptionShort=" + descriptionShort +
                ", descriptionFull=" + descriptionFull +
                ", iconResourceOSX=" + iconResourceOSX +
                ", compileDeps=" + compileDeps +
                ", testDeps=" + testDeps +
                ", knowhowDeps=" + knowhowDeps +
                ", contributors=" + contributors +
                ", authors=" + authors +
                ", email=" + email +
                ", webpage=" + webpage +
                '}';
    }

    public JavaVersion getJavaVersion() {
        return javaVersion.get();
    }

    public List<Artifact> getTestDependencies() {
        return testDeps;
    }

    public String getDescriptionShort() {
        return descriptionShort.get();
    }

    public WebPage getWebpage() {
        return webpage.get();
    }

    public Version gettVersion() {
        return version.get();
    }

    public Licence getLicence() {
        return licence.get();
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Author> getContributors() {
        return contributors;
    }

    public EmailAddress getEmail() {
        return email.get();
    }

    public void merge( Artifact other ) {
        if ( !equals( other )) {
            throw new IllegalArgumentException( "can't merge complete different Artifact: " + this + " with " + other );
        }

        licence.setIf( other.licence );
        version.setIf( other.version );
        javaVersion.setIf( other.javaVersion );
        descriptionShort.setIf( other.descriptionShort );
        descriptionFull.setIf( other.descriptionFull );

        // ...
    }

    public Artifact iconResourcePath( String str ) {
        iconResourcePath.set( str );

        return this;
    }

    public Icon getIcon() {
        if ( !base.isSet() || !iconResourcePath.isSet() ) {
            throw new IllegalStateException( "icon resource not set" );
        }
        return new ImageIcon( base.get().getResource( iconResourcePath.get() ));
    }

    public Artifact base( @NotNull Class resourceBase) {
        base.set( resourceBase );
        return this;
    }
}
