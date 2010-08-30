package org.openCage.artig.stjx;

import java.util.ArrayList;
import java.util.List;
public class Artifact {
   private String groupId;
   private String name;
   private String version;
   private String licence;
   private String description;
   private String support;
   private List<ArtifactRef> depends = new ArrayList<ArtifactRef>( );
   private List<Author> authors = new ArrayList<Author>( );
   private List<Author> contributors = new ArrayList<Author>( );
   private Address address;
   private List<Language> languages = new ArrayList<Language>( );
   private Java java;
   private List<ArtifactRef> refs = new ArrayList<ArtifactRef>( );
   private DropInFor dropInFor;
   private FullDescription fullDescription;
   public String getGroupId(  ){
      return groupId;
   }
   public void setGroupId( String groupId ){
      this.groupId = groupId;
   }
   public String getName(  ){
      return name;
   }
   public void setName( String name ){
      this.name = name;
   }
   public String getVersion(  ){
      return version;
   }
   public void setVersion( String version ){
      this.version = version;
   }
   public String getLicence(  ){
      return licence;
   }
   public void setLicence( String licence ){
      this.licence = licence;
   }
   public String getDescription(  ){
      return description;
   }
   public void setDescription( String description ){
      this.description = description;
   }
   public String getSupport(  ){
      return support;
   }
   public void setSupport( String support ){
      this.support = support;
   }
   public List<ArtifactRef> getDepends(  ){
      return depends;
   }
   public void setDepends( List<ArtifactRef> depends ){
      this.depends = depends;
   }
   public List<Author> getAuthors(  ){
      return authors;
   }
   public void setAuthors( List<Author> authors ){
      this.authors = authors;
   }
   public List<Author> getContributors(  ){
      return contributors;
   }
   public void setContributors( List<Author> contributors ){
      this.contributors = contributors;
   }
   public Address getAddress(  ){
      return address;
   }
   public void setAddress( Address address ){
      this.address = address;
   }
   public List<Language> getLanguages(  ){
      return languages;
   }
   public void setLanguages( List<Language> languages ){
      this.languages = languages;
   }
   public Java getJava(  ){
      return java;
   }
   public void setJava( Java java ){
      this.java = java;
   }
   public List<ArtifactRef> getRefs(  ){
      return refs;
   }
   public void setRefs( List<ArtifactRef> refs ){
      this.refs = refs;
   }
   public DropInFor getDropInFor(  ){
      return dropInFor;
   }
   public void setDropInFor( DropInFor dropInFor ){
      this.dropInFor = dropInFor;
   }
   public FullDescription getFullDescription(  ){
      return fullDescription;
   }
   public void setFullDescription( FullDescription fullDescription ){
      this.fullDescription = fullDescription;
   }

}
