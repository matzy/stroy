package org.openCage.lang.artifact;

import org.jetbrains.annotations.NotNull;
import org.openCage.lang.structure.Once;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class Artifact {

    private final String name;
    private final String  groupId;

    private final Once<Licence> licence = new Once<Licence>( Licence.apache2 );
    private final Once<Version>  version = new Once<Version>( Version.valueOf("0.0.1") );
    private final Once<JavaVersion> javaVersion = new Once<JavaVersion>( JavaVersion.V6);
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
    private Once<Boolean> isApp = new Once<Boolean>( false );
    private Once<String> mainClass = new Once<String>( "" );
    private Set<String> languages = new HashSet<String>();
    private Once<String> screenshortUrl = new Once<String>("");
    private Once<String> iconUrl = new Once<String>("");
    private Once<String> downloadUrl = new Once<String>("");


    Artifact( @NotNull String groupId, @NotNull String name ) {
        if ( groupId.isEmpty() ) {
            throw new IllegalArgumentException( "groupId can not be empty" );
        }
        if ( name.isEmpty() ) {
            throw new IllegalArgumentException( "name can not be empty" );
        }
        this.name = name;
        this.groupId = groupId;
    }

    public Artifact licence( String name ) {
        licence.set( Licence.valueOf( name ) );
        return this;
    }

    public Artifact mpl11() {
        licence.set( Licence.mpl11 );
        return this;
    }

    public Artifact apache2() {
        licence.set( Licence.apache2 );
        return this;
    }

    public Artifact lgpl2() {
        licence.set( Licence.lgpl2 );
        return this;
    }

    public Artifact lgpl3() {
        licence.set( Licence.lgpl3 );
        return this;
    }

    public Artifact bsd() {
        licence.set( Licence.bsd );
        return this;
    }

    public Artifact creativeCommons() {
        licence.set( Licence.creativeCommons );
        return this;
    }

    public Artifact cpl() {
        licence.set( Licence.cpl);
        return this;
    }


    public Artifact mit() {
        licence.set( Licence.mit );
        return this;
    }

    public Artifact jsonlicence() {
        licence.set( Licence.json );
        return this;
    }

    public Artifact version( String ver) {
        version.set( Version.valueOf( ver ));

        return this;
    }

    public Artifact add( Version ver ) {
        version.set( ver );
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
        javaVersion.set( JavaVersion.V6P);
        return this;
    }

    public Artifact java6() {
        javaVersion.set( JavaVersion.V6);
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

//    public Artifact depends( Artifact artifact, Scope scope ) {
//        return this;
//    }


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
        if ( licence.isSet() && artifact.licence.isSet() ) {
            if ( !licence.get().canUse( artifact.licence.get() )) {
                throw new IllegalArgumentException( "licences are not compatible: " +
                        licence.get().getName() +
                        " can not use " +
                        artifact.licence.get().getName()  );
            }
        }
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

    public Collection<Artifact> getCompileDependencyClosure() {
        Set<Artifact> closure = new HashSet<Artifact>();

        for ( Artifact dep : compileDeps ) {
            closure.addAll( dep.getCompileDependencyClosure() );
        }

        closure.addAll( compileDeps );

        return closure;
    }

    public Version getVersion() {
        return version.get();
    }

    public final Artifact address( String page, String shrt ) {
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

    @Override public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Artifact)) { return false; }

        Artifact artifact = (Artifact) o;

        if (groupId != null ? !groupId.equals(artifact.groupId) : artifact.groupId != null) { return false; }
        if (name != null ? !name.equals(artifact.name) : artifact.name != null) { return false; }

        return true;
    }

    @Override public int hashCode() {
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

    @Override public String toString() {
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

    public Artifact deployApp() {
        isApp.set( true );
        return this;
    }

    public boolean isApp() {
        return isApp.get();
    }

    public Artifact mainClass(String fullyQualifiedName) {
        mainClass.set( fullyQualifiedName );
        return this;
    }

    public String getMainClass() {
        return mainClass.get();
    }

    public Collection<String> getLocalizations() {
        return languages;
    }

    public Artifact language(String lang) {
        languages.add( lang );
        return this;
    }

    public Artifact screenshotUrl(String url) {
        screenshortUrl.set( url );
        return this;
    }

    public Once<String> getScreenshotUrl() {
        return screenshortUrl;
    }

    public Artifact iconUrl(String url) {
        iconUrl.set( url );
        return this;
    }

    public Once<String> getIconUrl() {
        return iconUrl;
    }

    public Artifact downloadUrl(String url) {
        downloadUrl.set( url );
        return this;
    }

    public Once<String> getDownloadUrl() {
        return downloadUrl;
    }


    public String getModuleName() {
        if ( name.startsWith( groupId + "-" ) ) {
            return name.substring( groupId.length() + 1 );
        }

        return name;
    }
}
