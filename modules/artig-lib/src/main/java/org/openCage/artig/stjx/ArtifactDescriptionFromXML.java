package org.openCage.artig.stjx;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.events.EntityDeclaration;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
public class ArtifactDescriptionFromXML extends DefaultHandler {
   public class ListHelper<T> {
      private List<T> list;
      public  ListHelper( List<T> list ){
         this.list = list;
      }
      public void add( T elem  ){
         list.add( elem );
      }

   }
   private Stack stack = new Stack( );
   private Object goal;
   @Override public void startDocument(  ) throws SAXException {
      stack.clear();
   }
   @Override public void endDocument(  ) throws SAXException {
   }
   public ArtifactDescription getGoal(  ){
      return ((ArtifactDescription)goal);
   }
   @Override public void startElement( String uri, String localName, String qName, Attributes attributes ) throws SAXException {
      if( qName.equals( "licences" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "licences: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Project ){
            stack.push( ((Project)peek).getLicences() );
         }
         if( peek instanceof Deployed ){
            stack.push( ((Deployed)peek).getLicences() );
         }
         throw new IllegalArgumentException( "licences is not a member of " + peek.getClass() );
      }
      if( qName.equals( "dropIns" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "dropIns: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Project ){
            stack.push( ((Project)peek).getDropIns() );
         }
         throw new IllegalArgumentException( "dropIns is not a member of " + peek.getClass() );
      }
      if( qName.equals( "Author" ) ){
         Author elem = new Author( );
         if( attributes.getValue( "name" ) != null ){
            elem.setName( attributes.getValue( "name" ) );
         } else {
            throw new IllegalArgumentException( "Author: attribute name is required" );
         }
         if( attributes.getValue( "email" ) != null ){
            elem.setEmail( attributes.getValue( "email" ) );
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof ListHelper ){
               ((ListHelper<Author>)peek).add( elem );
            }
         }
         return;
      }
      if( qName.equals( "Licence" ) ){
         Licence elem = new Licence( );
         if( attributes.getValue( "name" ) != null ){
            elem.setName( attributes.getValue( "name" ) );
         } else {
            throw new IllegalArgumentException( "Licence: attribute name is required" );
         }
         if( attributes.getValue( "version" ) != null ){
            elem.setVersion( attributes.getValue( "version" ) );
         } else {
            throw new IllegalArgumentException( "Licence: attribute version is required" );
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof ListHelper ){
               ((ListHelper<Licence>)peek).add( elem );
            }
         }
         return;
      }
      if( qName.equals( "negatives" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "negatives: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Licence ){
            stack.push( ((Licence)peek).getNegatives() );
         }
         throw new IllegalArgumentException( "negatives is not a member of " + peek.getClass() );
      }
      if( qName.equals( "externals" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "externals: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Project ){
            stack.push( ((Project)peek).getExternals() );
         }
         throw new IllegalArgumentException( "externals is not a member of " + peek.getClass() );
      }
      if( qName.equals( "Module" ) ){
         Module elem = new Module( );
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof ArtifactDescription ){
               ((ArtifactDescription)peek).setKind( elem );
            }
         }
         return;
      }
      if( qName.equals( "Java" ) ){
         Java elem = new Java( );
         if( attributes.getValue( "min" ) != null ){
            elem.setMin( attributes.getValue( "min" ) );
         } else {
            throw new IllegalArgumentException( "Java: attribute min is required" );
         }
         if( attributes.getValue( "max" ) != null ){
            elem.setMax( attributes.getValue( "max" ) );
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Artifact ){
               ((Artifact)peek).setJava( elem );
            }
         }
         return;
      }
      if( qName.equals( "Kind" ) ){
         return;
      }
      if( qName.equals( "positives" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "positives: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Licence ){
            stack.push( ((Licence)peek).getPositives() );
         }
         throw new IllegalArgumentException( "positives is not a member of " + peek.getClass() );
      }
      if( qName.equals( "languages" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "languages: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Artifact ){
            stack.push( ((Artifact)peek).getLanguages() );
         }
         throw new IllegalArgumentException( "languages is not a member of " + peek.getClass() );
      }
      if( qName.equals( "authors" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "authors: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Artifact ){
            stack.push( ((Artifact)peek).getAuthors() );
         }
         throw new IllegalArgumentException( "authors is not a member of " + peek.getClass() );
      }
      if( qName.equals( "ModuleRef" ) ){
         ModuleRef elem = new ModuleRef( );
         if( attributes.getValue( "name" ) != null ){
            elem.setName( attributes.getValue( "name" ) );
         } else {
            throw new IllegalArgumentException( "ModuleRef: attribute name is required" );
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof ListHelper ){
               ((ListHelper<ModuleRef>)peek).add( elem );
            }
         }
         return;
      }
      if( qName.equals( "references" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "references: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof References ){
            stack.push( ((References)peek).getReferences() );
         }
         throw new IllegalArgumentException( "references is not a member of " + peek.getClass() );
      }
      if( qName.equals( "DropInFor" ) ){
         DropInFor elem = new DropInFor( );
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Artifact ){
               ((Artifact)peek).setDropInFor( elem );
            }
         }
         return;
      }
      if( qName.equals( "LicenceRef" ) ){
         LicenceRef elem = new LicenceRef( );
         if( attributes.getValue( "name" ) != null ){
            elem.setName( attributes.getValue( "name" ) );
         } else {
            throw new IllegalArgumentException( "LicenceRef: attribute name is required" );
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof ListHelper ){
               ((ListHelper<LicenceRef>)peek).add( elem );
            }
         }
         return;
      }
      if( qName.equals( "ArtifactDescription" ) ){
         ArtifactDescription elem = new ArtifactDescription( );
         if( attributes.getValue( "version" ) != null ){
            elem.setVersion( attributes.getValue( "version" ) );
         } else {
            throw new IllegalArgumentException( "ArtifactDescription: attribute version is required" );
         }
         return;
      }
      if( qName.equals( "dependencies" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "dependencies: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Deployed ){
            stack.push( ((Deployed)peek).getDependencies() );
         }
         throw new IllegalArgumentException( "dependencies is not a member of " + peek.getClass() );
      }
      if( qName.equals( "References" ) ){
         References elem = new References( );
         return;
      }
      if( qName.equals( "Project" ) ){
         Project elem = new Project( );
         if( attributes.getValue( "name" ) != null ){
            elem.setName( attributes.getValue( "name" ) );
         } else {
            throw new IllegalArgumentException( "Project: attribute name is required" );
         }
         if( attributes.getValue( "groupId" ) != null ){
            elem.setGroupId( attributes.getValue( "groupId" ) );
         } else {
            throw new IllegalArgumentException( "Project: attribute groupId is required" );
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof ArtifactDescription ){
               ((ArtifactDescription)peek).setKind( elem );
            }
         }
         return;
      }
      if( qName.equals( "depends" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "depends: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Artifact ){
            stack.push( ((Artifact)peek).getDepends() );
         }
         throw new IllegalArgumentException( "depends is not a member of " + peek.getClass() );
      }
      if( qName.equals( "Download" ) ){
         Download elem = new Download( );
         if( attributes.getValue( "screenshot" ) != null ){
            elem.setScreenshot( attributes.getValue( "screenshot" ) );
         } else {
            throw new IllegalArgumentException( "Download: attribute screenshot is required" );
         }
         if( attributes.getValue( "icon" ) != null ){
            elem.setIcon( attributes.getValue( "icon" ) );
         } else {
            throw new IllegalArgumentException( "Download: attribute icon is required" );
         }
         if( attributes.getValue( "download" ) != null ){
            elem.setDownload( attributes.getValue( "download" ) );
         } else {
            throw new IllegalArgumentException( "Download: attribute download is required" );
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof App ){
               ((App)peek).setDownload( elem );
            }
         }
         return;
      }
      if( qName.equals( "contributors" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "contributors: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Artifact ){
            stack.push( ((Artifact)peek).getContributors() );
         }
         throw new IllegalArgumentException( "contributors is not a member of " + peek.getClass() );
      }
      if( qName.equals( "Deployed" ) ){
         Deployed elem = new Deployed( );
         if( attributes.getValue( "icon" ) != null ){
            elem.setIcon( attributes.getValue( "icon" ) );
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof ArtifactDescription ){
               ((ArtifactDescription)peek).setKind( elem );
            }
         }
         return;
      }
      if( qName.equals( "ArtifactRef" ) ){
         ArtifactRef elem = new ArtifactRef( );
         if( attributes.getValue( "groupId" ) != null ){
            elem.setGroupId( attributes.getValue( "groupId" ) );
         }
         if( attributes.getValue( "name" ) != null ){
            elem.setName( attributes.getValue( "name" ) );
         } else {
            throw new IllegalArgumentException( "ArtifactRef: attribute name is required" );
         }
         if( attributes.getValue( "version" ) != null ){
            elem.setVersion( attributes.getValue( "version" ) );
         }
         if( attributes.getValue( "scope" ) != null ){
            elem.setScope( attributes.getValue( "scope" ) );
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof DropInFor ){
               ((DropInFor)peek).setArtifactRef( elem );
            }
            if( peek instanceof ListHelper ){
               ((ListHelper<ArtifactRef>)peek).add( elem );
            }
         }
         return;
      }
      if( qName.equals( "Artifact" ) ){
         Artifact elem = new Artifact( );
         if( attributes.getValue( "groupId" ) != null ){
            elem.setGroupId( attributes.getValue( "groupId" ) );
         } else {
            throw new IllegalArgumentException( "Artifact: attribute groupId is required" );
         }
         if( attributes.getValue( "name" ) != null ){
            elem.setName( attributes.getValue( "name" ) );
         } else {
            throw new IllegalArgumentException( "Artifact: attribute name is required" );
         }
         if( attributes.getValue( "version" ) != null ){
            elem.setVersion( attributes.getValue( "version" ) );
         } else {
            throw new IllegalArgumentException( "Artifact: attribute version is required" );
         }
         if( attributes.getValue( "licence" ) != null ){
            elem.setLicence( attributes.getValue( "licence" ) );
         } else {
            throw new IllegalArgumentException( "Artifact: attribute licence is required" );
         }
         if( attributes.getValue( "description" ) != null ){
            elem.setDescription( attributes.getValue( "description" ) );
         } else {
            throw new IllegalArgumentException( "Artifact: attribute description is required" );
         }
         if( attributes.getValue( "support" ) != null ){
            elem.setSupport( attributes.getValue( "support" ) );
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Module ){
               ((Module)peek).setArtifact( elem );
            }
            if( peek instanceof Deployed ){
               ((Deployed)peek).setArtifact( elem );
            }
            if( peek instanceof ListHelper ){
               ((ListHelper<Artifact>)peek).add( elem );
            }
         }
         return;
      }
      if( qName.equals( "App" ) ){
         App elem = new App( );
         if( attributes.getValue( "mainClass" ) != null ){
            elem.setMainClass( attributes.getValue( "mainClass" ) );
         } else {
            throw new IllegalArgumentException( "App: attribute mainClass is required" );
         }
         if( attributes.getValue( "icon" ) != null ){
            elem.setIcon( attributes.getValue( "icon" ) );
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Module ){
               ((Module)peek).setApp( elem );
            }
         }
         return;
      }
      if( qName.equals( "refs" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "refs: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Artifact ){
            stack.push( ((Artifact)peek).getRefs() );
         }
         throw new IllegalArgumentException( "refs is not a member of " + peek.getClass() );
      }
      if( qName.equals( "Address" ) ){
         Address elem = new Address( );
         if( attributes.getValue( "page" ) != null ){
            elem.setPage( attributes.getValue( "page" ) );
         } else {
            throw new IllegalArgumentException( "Address: attribute page is required" );
         }
         if( attributes.getValue( "shrt" ) != null ){
            elem.setShrt( attributes.getValue( "shrt" ) );
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Licence ){
               ((Licence)peek).setAddress( elem );
            }
            if( peek instanceof Artifact ){
               ((Artifact)peek).setAddress( elem );
            }
         }
         return;
      }
      if( qName.equals( "FullDescription" ) ){
         FullDescription elem = new FullDescription( );
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Artifact ){
               ((Artifact)peek).setFullDescription( elem );
            }
         }
         return;
      }
      if( qName.equals( "Language" ) ){
         Language elem = new Language( );
         if( attributes.getValue( "name" ) != null ){
            elem.setName( attributes.getValue( "name" ) );
         } else {
            throw new IllegalArgumentException( "Language: attribute name is required" );
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof ListHelper ){
               ((ListHelper<Language>)peek).add( elem );
            }
         }
         return;
      }
      if( qName.equals( "modules" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "modules: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Project ){
            stack.push( ((Project)peek).getModules() );
         }
         throw new IllegalArgumentException( "modules is not a member of " + peek.getClass() );
      }
      throw new IllegalArgumentException( "unknown tag " + qName );
   }
   @Override public void endElement( String uri, String localName, String qName ) throws SAXException {
      if( qName.equals( "licences" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "dropIns" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "Author" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "Licence" ) ){
         goal = stack.pop();
         if( ((Licence)goal).getAddress() != null ){
            throw new IllegalArgumentException( "Licence: required member Address not set" );
         }
      }
      if( qName.equals( "negatives" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "externals" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "Module" ) ){
         goal = stack.pop();
         if( ((Module)goal).getArtifact() != null ){
            throw new IllegalArgumentException( "Module: required member Artifact not set" );
         }
      }
      if( qName.equals( "Java" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "positives" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "languages" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "authors" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "ModuleRef" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "references" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "DropInFor" ) ){
         goal = stack.pop();
         if( ((DropInFor)goal).getArtifactRef() != null ){
            throw new IllegalArgumentException( "DropInFor: required member ArtifactRef not set" );
         }
      }
      if( qName.equals( "LicenceRef" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "ArtifactDescription" ) ){
         goal = stack.pop();
         if( ((ArtifactDescription)goal).getKind() != null ){
            throw new IllegalArgumentException( "ArtifactDescription: required member Kind not set" );
         }
      }
      if( qName.equals( "dependencies" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "References" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "Project" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "depends" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "Download" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "contributors" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "Deployed" ) ){
         goal = stack.pop();
         if( ((Deployed)goal).getArtifact() != null ){
            throw new IllegalArgumentException( "Deployed: required member Artifact not set" );
         }
      }
      if( qName.equals( "ArtifactRef" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "Artifact" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "App" ) ){
         goal = stack.pop();
         if( ((App)goal).getDownload() != null ){
            throw new IllegalArgumentException( "App: required member Download not set" );
         }
      }
      if( qName.equals( "refs" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "Address" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "FullDescription" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "Language" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "modules" ) ){
         goal = stack.pop();
      }
   }

}
