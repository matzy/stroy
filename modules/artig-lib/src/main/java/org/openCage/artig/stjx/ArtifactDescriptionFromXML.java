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
import java.util.Locale;
import java.io.InputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

/**
 * ** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * <p/>
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * <p/>
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * <p/>
 * The Original Code is stroy code
 * <p/>
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 * <p/>
 * Contributor(s):
 * **** END LICENSE BLOCK ****
*/
public class ArtifactDescriptionFromXML extends DefaultHandler {

   private static  class AttributedAttributes {
      private Attributes attis;
      private List<Integer> idxes = new ArrayList<Integer>();
      public  AttributedAttributes( Attributes attis ){
         this.attis = attis;
      }
      public String getValue( String name ){
         String val = attis.getValue( name );
         if( val != null ){
            idxes.add( attis.getIndex( name ) );
         }
         return val;
      }
      public void check(  ){
         for ( int idx = 0; idx < attis.getLength(); ++ idx){
            if( ! idxes.contains( idx ) ){
               throw new IllegalArgumentException( "Unknown Attribute: " + attis.getQName( idx ) );
            }
         }
      }

   }
   private Stack stack = new Stack();
   private Object goal;
   private boolean getCharacters;
   @Override public void startDocument(  ) throws SAXException {
      stack.clear();
   }
   @Override public void endDocument(  ) throws SAXException {
   }
   public ArtifactDescription getGoal(  ){
      return ((ArtifactDescription)goal);
   }
   public static  ArtifactDescription read( InputStream is ){
      ArtifactDescriptionFromXML from = new ArtifactDescriptionFromXML();
      SAXParserFactory factory = SAXParserFactory.newInstance();
      try {
         SAXParser parser = factory.newSAXParser();
         parser.parse( is, from );
      } catch( IOException exp ) {
         exp.printStackTrace();
      } catch( SAXException exp ) {
         exp.printStackTrace();
      } catch( ParserConfigurationException exp ) {
         exp.printStackTrace();
      } catch( IllegalArgumentException exp ) {
         exp.printStackTrace();
         System.err.println( "Problem parsing " );
      }
      return from.getGoal();
   }
//   public static  ArtifactDescription read( File file ){
//      return read( new FileInputStream( file) );
//   }
   @Override public void startElement( String uri, String localName, String qName, Attributes saxAttis ) throws SAXException {
      AttributedAttributes attributes = new AttributedAttributes( saxAttis);
      if( qName.equals( "CLT" ) ){
         CLT elem = new CLT();
         if( attributes.getValue( "mainClass" ) != null ){
            elem.setMainClass( attributes.getValue( "mainClass" ) );
         } else {
            throw new IllegalArgumentException( "CLT: attribute mainClass is required" );
         }
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Module ){
               ((Module)peek).setModuleKind( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "Lib" ) ){
         Lib elem = new Lib();
         if( attributes.getValue( "mainClass" ) != null ){
            elem.setMainClass( attributes.getValue( "mainClass" ) );
         }
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Module ){
               ((Module)peek).setModuleKind( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "licences" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "licences: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Project ){
            stack.push( ((Project)peek).getLicences() );
            return;
         }
         if( peek instanceof Deployed ){
            stack.push( ((Deployed)peek).getLicences() );
            return;
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
            return;
         }
         throw new IllegalArgumentException( "dropIns is not a member of " + peek.getClass() );
      }
      if( qName.equals( "Author" ) ){
         Author elem = new Author();
         if( attributes.getValue( "name" ) != null ){
            elem.setName( attributes.getValue( "name" ) );
         } else {
            throw new IllegalArgumentException( "Author: attribute name is required" );
         }
         if( attributes.getValue( "email" ) != null ){
            elem.setEmail( attributes.getValue( "email" ) );
         }
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof List ){
               ((List<Author>)peek).add( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "Licence" ) ){
         Licence elem = new Licence();
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
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof List ){
               ((List<Licence>)peek).add( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "negatives" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "negatives: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Licence ){
            stack.push( ((Licence)peek).getNegatives() );
            return;
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
            return;
         }
         throw new IllegalArgumentException( "externals is not a member of " + peek.getClass() );
      }
      if( qName.equals( "Module" ) ){
         Module elem = new Module();
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof ArtifactDescription ){
               ((ArtifactDescription)peek).setKind( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "Java" ) ){
         Java elem = new Java();
         if( attributes.getValue( "min" ) != null ){
            elem.setMin( attributes.getValue( "min" ) );
         } else {
            throw new IllegalArgumentException( "Java: attribute min is required" );
         }
         if( attributes.getValue( "max" ) != null ){
            elem.setMax( attributes.getValue( "max" ) );
         }
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Artifact ){
               ((Artifact)peek).setJava( elem );
            }
         }
         stack.push( elem );
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
            return;
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
            return;
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
            return;
         }
         throw new IllegalArgumentException( "authors is not a member of " + peek.getClass() );
      }
      if( qName.equals( "ModuleRef" ) ){
         ModuleRef elem = new ModuleRef();
         if( attributes.getValue( "name" ) != null ){
            elem.setName( attributes.getValue( "name" ) );
         } else {
            throw new IllegalArgumentException( "ModuleRef: attribute name is required" );
         }
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof List ){
               ((List<ModuleRef>)peek).add( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "DropInFor" ) ){
         DropInFor elem = new DropInFor();
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Artifact ){
               ((Artifact)peek).setDropInFor( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "LicenceRef" ) ){
         LicenceRef elem = new LicenceRef();
         if( attributes.getValue( "name" ) != null ){
            elem.setName( attributes.getValue( "name" ) );
         } else {
            throw new IllegalArgumentException( "LicenceRef: attribute name is required" );
         }
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof List ){
               ((List<LicenceRef>)peek).add( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "dependencies" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "dependencies: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Deployed ){
            stack.push( ((Deployed)peek).getDependencies() );
            return;
         }
         throw new IllegalArgumentException( "dependencies is not a member of " + peek.getClass() );
      }
      if( qName.equals( "ArtifactDescription" ) ){
         ArtifactDescription elem = new ArtifactDescription();
         if( attributes.getValue( "version" ) != null ){
            elem.setVersion( attributes.getValue( "version" ) );
         } else {
            throw new IllegalArgumentException( "ArtifactDescription: attribute version is required" );
         }
         attributes.check();
         stack.push( elem );
         return;
      }
      if( qName.equals( "Project" ) ){
         Project elem = new Project();
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
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof ArtifactDescription ){
               ((ArtifactDescription)peek).setKind( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "depends" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "depends: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Artifact ){
            stack.push( ((Artifact)peek).getDepends() );
            return;
         }
         throw new IllegalArgumentException( "depends is not a member of " + peek.getClass() );
      }
      if( qName.equals( "Download" ) ){
         Download elem = new Download();
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
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof App ){
               ((App)peek).setDownload( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "contributors" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "contributors: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Artifact ){
            stack.push( ((Artifact)peek).getContributors() );
            return;
         }
         throw new IllegalArgumentException( "contributors is not a member of " + peek.getClass() );
      }
      if( qName.equals( "Deployed" ) ){
         Deployed elem = new Deployed();
         if( attributes.getValue( "icon" ) != null ){
            elem.setIcon( attributes.getValue( "icon" ) );
         }
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof ArtifactDescription ){
               ((ArtifactDescription)peek).setKind( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "ArtifactRef" ) ){
         ArtifactRef elem = new ArtifactRef();
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
            elem.setScope( Scope.valueOf( attributes.getValue( "scope" ) ) );
         }
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof DropInFor ){
               ((DropInFor)peek).setArtifactRef( elem );
            }
            if( peek instanceof List ){
               ((List<ArtifactRef>)peek).add( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "Artifact" ) ){
         Artifact elem = new Artifact();
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
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Module ){
               ((Module)peek).setArtifact( elem );
            }
            if( peek instanceof Deployed ){
               ((Deployed)peek).setArtifact( elem );
            }
            if( peek instanceof List ){
               ((List<Artifact>)peek).add( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "App" ) ){
         App elem = new App();
         if( attributes.getValue( "mainClass" ) != null ){
            elem.setMainClass( attributes.getValue( "mainClass" ) );
         } else {
            throw new IllegalArgumentException( "App: attribute mainClass is required" );
         }
         if( attributes.getValue( "icon" ) != null ){
            elem.setIcon( attributes.getValue( "icon" ) );
         }
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Module ){
               ((Module)peek).setModuleKind( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "refs" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "refs: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Artifact ){
            stack.push( ((Artifact)peek).getRefs() );
            return;
         }
         throw new IllegalArgumentException( "refs is not a member of " + peek.getClass() );
      }
      if( qName.equals( "ModuleKind" ) ){
         return;
      }
      if( qName.equals( "Address" ) ){
         Address elem = new Address();
         if( attributes.getValue( "page" ) != null ){
            elem.setPage( attributes.getValue( "page" ) );
         } else {
            throw new IllegalArgumentException( "Address: attribute page is required" );
         }
         if( attributes.getValue( "shrt" ) != null ){
            elem.setShrt( attributes.getValue( "shrt" ) );
         }
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Licence ){
               ((Licence)peek).setAddress( elem );
            }
            if( peek instanceof Artifact ){
               ((Artifact)peek).setAddress( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "FullDescription" ) ){
         getCharacters = true;
         return;
      }
      if( qName.equals( "Language" ) ){
         Language elem = new Language();
         if( attributes.getValue( "name" ) != null ){
            elem.setName( attributes.getValue( "name" ) );
         } else {
            throw new IllegalArgumentException( "Language: attribute name is required" );
         }
         attributes.check();
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof List ){
               ((List<Language>)peek).add( elem );
            }
         }
         stack.push( elem );
         return;
      }
      if( qName.equals( "modules" ) ){
         if( stack.empty() ){
            throw new IllegalArgumentException( "modules: needs to be in complex type" );
         }
         Object peek = stack.peek();
         if( peek instanceof Project ){
            stack.push( ((Project)peek).getModules() );
            return;
         }
         throw new IllegalArgumentException( "modules is not a member of " + peek.getClass() );
      }
      throw new IllegalArgumentException( "unknown tag " + qName );
   }
   @Override public void endElement( String uri, String localName, String qName ) throws SAXException {
      if( qName.equals( "CLT" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "Lib" ) ){
         goal = stack.pop();
      }
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
         if( ((Licence)goal).getAddress() == null ){
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
         if( ((Module)goal).getArtifact() == null ){
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
      if( qName.equals( "DropInFor" ) ){
         goal = stack.pop();
         if( ((DropInFor)goal).getArtifactRef() == null ){
            throw new IllegalArgumentException( "DropInFor: required member ArtifactRef not set" );
         }
      }
      if( qName.equals( "LicenceRef" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "dependencies" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "ArtifactDescription" ) ){
         goal = stack.pop();
         if( ((ArtifactDescription)goal).getKind() == null ){
            throw new IllegalArgumentException( "ArtifactDescription: required member Kind not set" );
         }
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
         if( ((Deployed)goal).getArtifact() == null ){
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
         if( ((App)goal).getDownload() == null ){
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
         String str = "";
         while( stack.peek() instanceof String ){
            str = ((String)stack.pop()) + str;
         }
         if( ! stack.empty() ){
            Object peek = stack.peek();
            if( peek instanceof Artifact ){
               ((Artifact)peek).setFullDescription( str );
            }
         }
         getCharacters = false;
      }
      if( qName.equals( "Language" ) ){
         goal = stack.pop();
      }
      if( qName.equals( "modules" ) ){
         goal = stack.pop();
      }
   }
   public void characters( char[] ch, int start, int length ){
      if( getCharacters ){
         StringBuffer sb = new StringBuffer();
         for ( int idx = start; (idx < ch.length) && (idx < (start + length)); ++ idx){
            sb.append( ch[idx] );
         }
         stack.push( sb.toString() );
      }
   }

}
