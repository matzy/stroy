package org.openCage.artig.stjx;
import java.util.ArrayList;
import java.util.List;
public class Artifact {
   private String groupId;
   public String getGroupId() {
      return groupId;
   }
   public void setGroupId( String groupId ) {
      this.groupId = groupId;
   }
   private String name;
   public String getName() {
      return name;
   }
   public void setName( String name ) {
      this.name = name;
   }
   private String version;
   public String getVersion() {
      return version;
   }
   public void setVersion( String version ) {
      this.version = version;
   }
   private String licence;
   public String getLicence() {
      return licence;
   }
   public void setLicence( String licence ) {
      this.licence = licence;
   }
   private String email;
   public String getEmail() {
      return email;
   }
   public void setEmail( String email ) {
      this.email = email;
   }
   private List<ArtifactRef>  depends = new ArrayList<ArtifactRef>();
   public List<ArtifactRef> getDepends() {
      return depends;
   }
   private List<Author>  authors = new ArrayList<Author>();
   public List<Author> getAuthors() {
      return authors;
   }
   private List<Author>  contributors = new ArrayList<Author>();
   public List<Author> getContributors() {
      return contributors;
   }
   private Address address;
   public Address getAddress() {
      return address;
   }
   public void setAddress( Address address ) {
      this.address = address;
   }
   private List<Language>  languages = new ArrayList<Language>();
   public List<Language> getLanguages() {
      return languages;
   }
   private Java java;
   public Java getJava() {
      return java;
   }
   public void setJava( Java java ) {
      this.java = java;
   }
   private List<ArtifactRef>  refs = new ArrayList<ArtifactRef>();
   public List<ArtifactRef> getRefs() {
      return refs;
   }
    public String toString() {
       return "Artifact(groupId: " + getGroupId() +" name: " + getName() +" version: " + getVersion() +" licence: " + getLicence() +" email: " + getEmail() +" dependsauthorscontributorsAddresslanguagesJavarefs)";    }
}
