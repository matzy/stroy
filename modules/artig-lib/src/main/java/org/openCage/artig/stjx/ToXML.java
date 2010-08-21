package org.openCage.artig.stjx;

import java.util.List;
public class ToXML {
   public static String toStringlicences( String prefix, List<Licence> licences ){
      if( licences.isEmpty() ){
      return "";
   }
;
      String ret = prefix;
      ret += "<licences>\n";
      for ( Licence vr : licences ) {
      ret += toStringLicence( prefix + "   ", vr );
   }
;
      ret += prefix + "</licences>\n";
      return ret ;
   }
   public static String toStringAuthor( String prefix, Author author ){
      String ret = prefix;
      ret += "<Author ";
      if( author.getName(  ) != null ){
      ret += "name= \"" + author.getName(  ) + "\" ";
   }
;
      if( author.getEmail(  ) != null ){
      ret += "email= \"" + author.getEmail(  ) + "\" ";
   }
;
      ret += "/>\n";
      return ret ;
   }
   public static String toStringLicence( String prefix, Licence licence ){
      String ret = prefix;
      ret += "<Licence ";
      if( licence.getName(  ) != null ){
      ret += "name= \"" + licence.getName(  ) + "\" ";
   }
;
      if( licence.getVersion(  ) != null ){
      ret += "version= \"" + licence.getVersion(  ) + "\" ";
   }
;
      ret += ">\n";
      if( licence.getAddress(  ) != null ){
      ret += toStringAddress( prefix + "   ", licence.getAddress(  ) );
   }
;
      if( licence.getPositives(  ) != null ){
      ret += toStringpositives( prefix + "   ", licence.getPositives(  ) );
   }
;
      if( licence.getNegatives(  ) != null ){
      ret += toStringnegatives( prefix + "   ", licence.getNegatives(  ) );
   }
;
      ret += prefix + "</Licence>\n";
      return ret ;
   }
   public static String toStringnegatives( String prefix, List<LicenceRef> negatives ){
      if( negatives.isEmpty() ){
      return "";
   }
;
      String ret = prefix;
      ret += "<negatives>\n";
      for ( LicenceRef vr : negatives ) {
      ret += toStringLicenceRef( prefix + "   ", vr );
   }
;
      ret += prefix + "</negatives>\n";
      return ret ;
   }
   public static String toStringexternals( String prefix, List<Artifact> externals ){
      if( externals.isEmpty() ){
      return "";
   }
;
      String ret = prefix;
      ret += "<externals>\n";
      for ( Artifact vr : externals ) {
      ret += toStringArtifact( prefix + "   ", vr );
   }
;
      ret += prefix + "</externals>\n";
      return ret ;
   }
   public static String toStringModule( String prefix, Module module ){
      String ret = prefix;
      ret += "<Module ";
      ret += ">\n";
      if( module.getArtifact(  ) != null ){
      ret += toStringArtifact( prefix + "   ", module.getArtifact(  ) );
   }
;
      if( module.getApp(  ) != null ){
      ret += toStringApp( prefix + "   ", module.getApp(  ) );
   }
;
      ret += prefix + "</Module>\n";
      return ret ;
   }
   public static String toStringJava( String prefix, Java java ){
      String ret = prefix;
      ret += "<Java ";
      if( java.getMin(  ) != null ){
      ret += "min= \"" + java.getMin(  ) + "\" ";
   }
;
      if( java.getMax(  ) != null ){
      ret += "max= \"" + java.getMax(  ) + "\" ";
   }
;
      ret += "/>\n";
      return ret ;
   }
   public static String toStringKind( String prefix, Kind kind ){
      if( kind instanceof Module ){
      return toStringModule( prefix, ((Module)kind) );
   }
;
      if( kind instanceof Project ){
      return toStringProject( prefix, ((Project)kind) );
   }
;
      if( kind instanceof Complete ){
      return toStringComplete( prefix, ((Complete)kind) );
   }
;
      throw new IllegalStateException( "no a valid suptype of Kind" );
   }
   public static String toStringpositives( String prefix, List<LicenceRef> positives ){
      if( positives.isEmpty() ){
      return "";
   }
;
      String ret = prefix;
      ret += "<positives>\n";
      for ( LicenceRef vr : positives ) {
      ret += toStringLicenceRef( prefix + "   ", vr );
   }
;
      ret += prefix + "</positives>\n";
      return ret ;
   }
   public static String toStringlanguages( String prefix, List<Language> languages ){
      if( languages.isEmpty() ){
      return "";
   }
;
      String ret = prefix;
      ret += "<languages>\n";
      for ( Language vr : languages ) {
      ret += toStringLanguage( prefix + "   ", vr );
   }
;
      ret += prefix + "</languages>\n";
      return ret ;
   }
   public static String toStringauthors( String prefix, List<Author> authors ){
      if( authors.isEmpty() ){
      return "";
   }
;
      String ret = prefix;
      ret += "<authors>\n";
      for ( Author vr : authors ) {
      ret += toStringAuthor( prefix + "   ", vr );
   }
;
      ret += prefix + "</authors>\n";
      return ret ;
   }
   public static String toStringModuleRef( String prefix, ModuleRef moduleRef ){
      String ret = prefix;
      ret += "<ModuleRef ";
      if( moduleRef.getName(  ) != null ){
      ret += "name= \"" + moduleRef.getName(  ) + "\" ";
   }
;
      ret += "/>\n";
      return ret ;
   }
   public static String toStringreferences( String prefix, List<Artifact> references ){
      if( references.isEmpty() ){
      return "";
   }
;
      String ret = prefix;
      ret += "<references>\n";
      for ( Artifact vr : references ) {
      ret += toStringArtifact( prefix + "   ", vr );
   }
;
      ret += prefix + "</references>\n";
      return ret ;
   }
   public static String toStringLicenceRef( String prefix, LicenceRef licenceRef ){
      String ret = prefix;
      ret += "<LicenceRef ";
      if( licenceRef.getName(  ) != null ){
      ret += "name= \"" + licenceRef.getName(  ) + "\" ";
   }
;
      ret += "/>\n";
      return ret ;
   }
   public static String toStringArtifactDescription( String prefix, ArtifactDescription artifactDescription ){
      String ret = prefix;
      ret += "<ArtifactDescription ";
      if( artifactDescription.getVersion(  ) != null ){
      ret += "version= \"" + artifactDescription.getVersion(  ) + "\" ";
   }
;
      ret += ">\n";
      if( artifactDescription.getKind(  ) != null ){
      ret += toStringKind( prefix + "   ", artifactDescription.getKind(  ) );
   }
;
      ret += prefix + "</ArtifactDescription>\n";
      return ret ;
   }
   public static String toStringdependencies( String prefix, List<Artifact> dependencies ){
      if( dependencies.isEmpty() ){
      return "";
   }
;
      String ret = prefix;
      ret += "<dependencies>\n";
      for ( Artifact vr : dependencies ) {
      ret += toStringArtifact( prefix + "   ", vr );
   }
;
      ret += prefix + "</dependencies>\n";
      return ret ;
   }
   public static String toStringReferences( String prefix, References references ){
      String ret = prefix;
      ret += "<References ";
      ret += ">\n";
      if( references.getReferences(  ) != null ){
      ret += toStringreferences( prefix + "   ", references.getReferences(  ) );
   }
;
      ret += prefix + "</References>\n";
      return ret ;
   }
   public static String toStringProject( String prefix, Project project ){
      String ret = prefix;
      ret += "<Project ";
      if( project.getName(  ) != null ){
      ret += "name= \"" + project.getName(  ) + "\" ";
   }
;
      if( project.getGroupId(  ) != null ){
      ret += "groupId= \"" + project.getGroupId(  ) + "\" ";
   }
;
      ret += ">\n";
      if( project.getModules(  ) != null ){
      ret += toStringmodules( prefix + "   ", project.getModules(  ) );
   }
;
      if( project.getExternals(  ) != null ){
      ret += toStringexternals( prefix + "   ", project.getExternals(  ) );
   }
;
      if( project.getLicences(  ) != null ){
      ret += toStringlicences( prefix + "   ", project.getLicences(  ) );
   }
;
      ret += prefix + "</Project>\n";
      return ret ;
   }
   public static String toStringdepends( String prefix, List<ArtifactRef> depends ){
      if( depends.isEmpty() ){
      return "";
   }
;
      String ret = prefix;
      ret += "<depends>\n";
      for ( ArtifactRef vr : depends ) {
      ret += toStringArtifactRef( prefix + "   ", vr );
   }
;
      ret += prefix + "</depends>\n";
      return ret ;
   }
   public static String toStringDownload( String prefix, Download download ){
      String ret = prefix;
      ret += "<Download ";
      if( download.getScreenshot(  ) != null ){
      ret += "screenshot= \"" + download.getScreenshot(  ) + "\" ";
   }
;
      if( download.getIcon(  ) != null ){
      ret += "icon= \"" + download.getIcon(  ) + "\" ";
   }
;
      if( download.getDownload(  ) != null ){
      ret += "download= \"" + download.getDownload(  ) + "\" ";
   }
;
      ret += "/>\n";
      return ret ;
   }
   public static String toStringcontributors( String prefix, List<Author> contributors ){
      if( contributors.isEmpty() ){
      return "";
   }
;
      String ret = prefix;
      ret += "<contributors>\n";
      for ( Author vr : contributors ) {
      ret += toStringAuthor( prefix + "   ", vr );
   }
;
      ret += prefix + "</contributors>\n";
      return ret ;
   }
   public static String toStringArtifactRef( String prefix, ArtifactRef artifactRef ){
      String ret = prefix;
      ret += "<ArtifactRef ";
      if( artifactRef.getGroupId(  ) != null ){
      ret += "groupId= \"" + artifactRef.getGroupId(  ) + "\" ";
   }
;
      if( artifactRef.getName(  ) != null ){
      ret += "name= \"" + artifactRef.getName(  ) + "\" ";
   }
;
      if( artifactRef.getVersion(  ) != null ){
      ret += "version= \"" + artifactRef.getVersion(  ) + "\" ";
   }
;
      if( artifactRef.getScope(  ) != null ){
      ret += "scope= \"" + artifactRef.getScope(  ) + "\" ";
   }
;
      ret += "/>\n";
      return ret ;
   }
   public static String toStringArtifact( String prefix, Artifact artifact ){
      String ret = prefix;
      ret += "<Artifact ";
      if( artifact.getGroupId(  ) != null ){
      ret += "groupId= \"" + artifact.getGroupId(  ) + "\" ";
   }
;
      if( artifact.getName(  ) != null ){
      ret += "name= \"" + artifact.getName(  ) + "\" ";
   }
;
      if( artifact.getVersion(  ) != null ){
      ret += "version= \"" + artifact.getVersion(  ) + "\" ";
   }
;
      if( artifact.getLicence(  ) != null ){
      ret += "licence= \"" + artifact.getLicence(  ) + "\" ";
   }
;
      if( artifact.getSupport(  ) != null ){
      ret += "support= \"" + artifact.getSupport(  ) + "\" ";
   }
;
      ret += ">\n";
      if( artifact.getDepends(  ) != null ){
      ret += toStringdepends( prefix + "   ", artifact.getDepends(  ) );
   }
;
      if( artifact.getAuthors(  ) != null ){
      ret += toStringauthors( prefix + "   ", artifact.getAuthors(  ) );
   }
;
      if( artifact.getContributors(  ) != null ){
      ret += toStringcontributors( prefix + "   ", artifact.getContributors(  ) );
   }
;
      if( artifact.getAddress(  ) != null ){
      ret += toStringAddress( prefix + "   ", artifact.getAddress(  ) );
   }
;
      if( artifact.getLanguages(  ) != null ){
      ret += toStringlanguages( prefix + "   ", artifact.getLanguages(  ) );
   }
;
      if( artifact.getJava(  ) != null ){
      ret += toStringJava( prefix + "   ", artifact.getJava(  ) );
   }
;
      if( artifact.getRefs(  ) != null ){
      ret += toStringrefs( prefix + "   ", artifact.getRefs(  ) );
   }
;
      ret += prefix + "</Artifact>\n";
      return ret ;
   }
   public static String toStringApp( String prefix, App app ){
      String ret = prefix;
      ret += "<App ";
      if( app.getMainClass(  ) != null ){
      ret += "mainClass= \"" + app.getMainClass(  ) + "\" ";
   }
;
      ret += ">\n";
      if( app.getDownload(  ) != null ){
      ret += toStringDownload( prefix + "   ", app.getDownload(  ) );
   }
;
      ret += prefix + "</App>\n";
      return ret ;
   }
   public static String toStringrefs( String prefix, List<ArtifactRef> refs ){
      if( refs.isEmpty() ){
      return "";
   }
;
      String ret = prefix;
      ret += "<refs>\n";
      for ( ArtifactRef vr : refs ) {
      ret += toStringArtifactRef( prefix + "   ", vr );
   }
;
      ret += prefix + "</refs>\n";
      return ret ;
   }
   public static String toStringAddress( String prefix, Address address ){
      String ret = prefix;
      ret += "<Address ";
      if( address.getPage(  ) != null ){
      ret += "page= \"" + address.getPage(  ) + "\" ";
   }
;
      if( address.getShrt(  ) != null ){
      ret += "shrt= \"" + address.getShrt(  ) + "\" ";
   }
;
      ret += "/>\n";
      return ret ;
   }
   public static String toStringLanguage( String prefix, Language language ){
      String ret = prefix;
      ret += "<Language ";
      if( language.getName(  ) != null ){
      ret += "name= \"" + language.getName(  ) + "\" ";
   }
;
      ret += "/>\n";
      return ret ;
   }
   public static String toStringmodules( String prefix, List<ModuleRef> modules ){
      if( modules.isEmpty() ){
      return "";
   }
;
      String ret = prefix;
      ret += "<modules>\n";
      for ( ModuleRef vr : modules ) {
      ret += toStringModuleRef( prefix + "   ", vr );
   }
;
      ret += prefix + "</modules>\n";
      return ret ;
   }
   public static String toStringComplete( String prefix, Complete complete ){
      String ret = prefix;
      ret += "<Complete ";
      ret += ">\n";
      if( complete.getArtifact(  ) != null ){
      ret += toStringArtifact( prefix + "   ", complete.getArtifact(  ) );
   }
;
      if( complete.getDependencies(  ) != null ){
      ret += toStringdependencies( prefix + "   ", complete.getDependencies(  ) );
   }
;
      if( complete.getLicences(  ) != null ){
      ret += toStringlicences( prefix + "   ", complete.getLicences(  ) );
   }
;
      ret += prefix + "</Complete>\n";
      return ret ;
   }
}
