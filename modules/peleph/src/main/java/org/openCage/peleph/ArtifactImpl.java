package org.openCage.peleph;

import org.jetbrains.annotations.NotNull;
import org.openCage.lang.clazz.Once;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 05-abr-2010
 * Time: 0:41:42
 * To change this template use File | Settings | File Templates.
 */
public class ArtifactImpl implements Artifact {

    private final String name;
    private final Once<Licence> licence = new Once<Licence>( LicenceImpl.apache2() );
    private final Once<String>  groupId = new Once<String>( "Anonymous" );
    private final Once<Version>  version = new Once<Version>( Version.parse("0.0.1") );
    private final Once<JavaVersion> javaVersion = new Once<JavaVersion>( JavaVersion.v6plus );
    private final Once<String> descriptionShort = new Once<String>( "a program doing foo" );
    private final Once<String> descriptionFull = new Once<String>( "a lengthier description of the prog" );
    private final Once<String> iconResourceOSX = new Once<String>( "" );

    public ArtifactImpl( @NotNull String name ) {
        this.name = name;
    }

    public ArtifactImpl mpl11() {
        licence.set( LicenceImpl.mpl11() );
        return this;
    }

    public ArtifactImpl withAuthor( String name ) {
        return this;
    }

    public ArtifactImpl version( String ver) {
        version.set( Version.parse( ver ));

        return this;
    }

    public ArtifactImpl iconResourceOSX( String path ) {
        if ( !path.endsWith(".icns")) {
            throw new IllegalArgumentException( "osx icon ends must end with .icns" );
        }

        
        return this;
    }

    public ArtifactImpl root( String path ) {
        return this;
    }

    public ArtifactImpl java6p() {
        javaVersion.set( JavaVersion.v6plus);
        return this;
    }

    public ArtifactImpl descriptionShort( String str ) {
        descriptionShort.set( str );
        return this;
    }

    public ArtifactImpl descriptionFull( String str ) {
        descriptionFull.set( str );
        return this;
    }

    public ArtifactImpl depends( Artifact artifact, Scope scope ) {
        return this;
    }


    @Override public String gettName() {
        return name;
    }

    @Override public Licence gettLicence() {
        return licence.get();
    }


}
