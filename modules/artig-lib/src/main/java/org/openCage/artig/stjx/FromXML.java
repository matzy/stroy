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

   public class FromXML extends DefaultHandler {

       private static class ListHelper<T> {
           private final List<T> list;

           public ListHelper( List<T> list ) {
               this.list = list;
           }

           public void add( T elem) {
               list.add( elem );
           }
       }

       private Stack stack = new Stack();       private Object goal;
       @Override
       public void startDocument() throws SAXException {
           stack.clear();
       }

       @Override
       public void endDocument() throws SAXException{}           

       @Override
       public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
          if ( qName.equals( "licences"))  {
             if ( stack.empty() ) {
                throw new IllegalArgumentException( "licences needs to be in a complex type ");
             }
             Object peek =  stack.peek();

              if ( peek instanceof Project ) {
                  stack.push( new ListHelper<Licence>( ((Project)peek).getLicences() ));
                  return;
              }

              if ( peek instanceof Complete ) {
                  stack.push( new ListHelper<Licence>( ((Complete)peek).getLicences() ));
                  return;
              }
              throw new IllegalArgumentException( "licences is not member of " + peek.getClass() );
          }
           if ( qName.equals("Author" )) {
               Author elem = new Author();
               String name = attributes.getValue( "name" );
               if ( name != null ) {
                  elem.setName( name);
               } 
               else {
                  throw new IllegalArgumentException( "Author" + " attribute name is required" );
               }
               String email = attributes.getValue( "email" );
               if ( email != null ) {
                  elem.setEmail( email);
               } 
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof ListHelper ) {
                      ((ListHelper<Author>)peek).add( elem );
                  }

               }
               stack.push(elem );
               return;
           }
           if ( qName.equals("Licence" )) {
               Licence elem = new Licence();
               String name = attributes.getValue( "name" );
               if ( name != null ) {
                  elem.setName( name);
               } 
               else {
                  throw new IllegalArgumentException( "Licence" + " attribute name is required" );
               }
               String version = attributes.getValue( "version" );
               if ( version != null ) {
                  elem.setVersion( version);
               } 
               else {
                  throw new IllegalArgumentException( "Licence" + " attribute version is required" );
               }
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof ListHelper ) {
                      ((ListHelper<Licence>)peek).add( elem );
                  }

               }
               stack.push(elem );
               return;
           }
          if ( qName.equals( "negatives"))  {
             if ( stack.empty() ) {
                throw new IllegalArgumentException( "negatives needs to be in a complex type ");
             }
             Object peek =  stack.peek();

              if ( peek instanceof Licence ) {
                  stack.push( new ListHelper<LicenceRef>( ((Licence)peek).getNegatives() ));
                  return;
              }
              throw new IllegalArgumentException( "negatives is not member of " + peek.getClass() );
          }
          if ( qName.equals( "externals"))  {
             if ( stack.empty() ) {
                throw new IllegalArgumentException( "externals needs to be in a complex type ");
             }
             Object peek =  stack.peek();

              if ( peek instanceof Project ) {
                  stack.push( new ListHelper<Artifact>( ((Project)peek).getExternals() ));
                  return;
              }
              throw new IllegalArgumentException( "externals is not member of " + peek.getClass() );
          }
           if ( qName.equals("Module" )) {
               Module elem = new Module();
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof ArtifactDescription) {
                     ((ArtifactDescription)peek).setKind( elem );
                  };
               }
               stack.push(elem );
               return;
           }
           if ( qName.equals("Java" )) {
               Java elem = new Java();
               String min = attributes.getValue( "min" );
               if ( min != null ) {
                  elem.setMin( min);
               } 
               else {
                  throw new IllegalArgumentException( "Java" + " attribute min is required" );
               }
               String max = attributes.getValue( "max" );
               if ( max != null ) {
                  elem.setMax( max);
               } 
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof Artifact) {
                     ((Artifact)peek).setJava( elem );
                  };
               }
               stack.push(elem );
               return;
           }
           if ( qName.equals("Kind" )) {
              // ortype: nothing to do
              return;
           }
          if ( qName.equals( "positives"))  {
             if ( stack.empty() ) {
                throw new IllegalArgumentException( "positives needs to be in a complex type ");
             }
             Object peek =  stack.peek();

              if ( peek instanceof Licence ) {
                  stack.push( new ListHelper<LicenceRef>( ((Licence)peek).getPositives() ));
                  return;
              }
              throw new IllegalArgumentException( "positives is not member of " + peek.getClass() );
          }
          if ( qName.equals( "languages"))  {
             if ( stack.empty() ) {
                throw new IllegalArgumentException( "languages needs to be in a complex type ");
             }
             Object peek =  stack.peek();

              if ( peek instanceof Artifact ) {
                  stack.push( new ListHelper<Language>( ((Artifact)peek).getLanguages() ));
                  return;
              }
              throw new IllegalArgumentException( "languages is not member of " + peek.getClass() );
          }
          if ( qName.equals( "authors"))  {
             if ( stack.empty() ) {
                throw new IllegalArgumentException( "authors needs to be in a complex type ");
             }
             Object peek =  stack.peek();

              if ( peek instanceof Artifact ) {
                  stack.push( new ListHelper<Author>( ((Artifact)peek).getAuthors() ));
                  return;
              }
              throw new IllegalArgumentException( "authors is not member of " + peek.getClass() );
          }
           if ( qName.equals("ModuleRef" )) {
               ModuleRef elem = new ModuleRef();
               String name = attributes.getValue( "name" );
               if ( name != null ) {
                  elem.setName( name);
               } 
               else {
                  throw new IllegalArgumentException( "ModuleRef" + " attribute name is required" );
               }
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof ListHelper ) {
                      ((ListHelper<ModuleRef>)peek).add( elem );
                  }

               }
               stack.push(elem );
               return;
           }
          if ( qName.equals( "references"))  {
             if ( stack.empty() ) {
                throw new IllegalArgumentException( "references needs to be in a complex type ");
             }
             Object peek =  stack.peek();

              if ( peek instanceof References ) {
                  stack.push( new ListHelper<Artifact>( ((References)peek).getReferences() ));
                  return;
              }
              throw new IllegalArgumentException( "references is not member of " + peek.getClass() );
          }
           if ( qName.equals("LicenceRef" )) {
               LicenceRef elem = new LicenceRef();
               String name = attributes.getValue( "name" );
               if ( name != null ) {
                  elem.setName( name);
               } 
               else {
                  throw new IllegalArgumentException( "LicenceRef" + " attribute name is required" );
               }
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof ListHelper ) {
                      ((ListHelper<LicenceRef>)peek).add( elem );
                  }

               }
               stack.push(elem );
               return;
           }
           if ( qName.equals("ArtifactDescription" )) {
               ArtifactDescription elem = new ArtifactDescription();
               String version = attributes.getValue( "version" );
               if ( version != null ) {
                  elem.setVersion( version);
               } 
               else {
                  throw new IllegalArgumentException( "ArtifactDescription" + " attribute version is required" );
               }
               stack.push(elem );
               return;
           }
          if ( qName.equals( "dependencies"))  {
             if ( stack.empty() ) {
                throw new IllegalArgumentException( "dependencies needs to be in a complex type ");
             }
             Object peek =  stack.peek();

              if ( peek instanceof Complete ) {
                  stack.push( new ListHelper<Artifact>( ((Complete)peek).getDependencies() ));
                  return;
              }
              throw new IllegalArgumentException( "dependencies is not member of " + peek.getClass() );
          }
           if ( qName.equals("References" )) {
               References elem = new References();
               stack.push(elem );
               return;
           }
           if ( qName.equals("Project" )) {
               Project elem = new Project();
               String name = attributes.getValue( "name" );
               if ( name != null ) {
                  elem.setName( name);
               } 
               else {
                  throw new IllegalArgumentException( "Project" + " attribute name is required" );
               }
               String groupId = attributes.getValue( "groupId" );
               if ( groupId != null ) {
                  elem.setGroupId( groupId);
               } 
               else {
                  throw new IllegalArgumentException( "Project" + " attribute groupId is required" );
               }
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof ArtifactDescription) {
                     ((ArtifactDescription)peek).setKind( elem );
                  };
               }
               stack.push(elem );
               return;
           }
          if ( qName.equals( "depends"))  {
             if ( stack.empty() ) {
                throw new IllegalArgumentException( "depends needs to be in a complex type ");
             }
             Object peek =  stack.peek();

              if ( peek instanceof Artifact ) {
                  stack.push( new ListHelper<ArtifactRef>( ((Artifact)peek).getDepends() ));
                  return;
              }
              throw new IllegalArgumentException( "depends is not member of " + peek.getClass() );
          }
           if ( qName.equals("Download" )) {
               Download elem = new Download();
               String screenshot = attributes.getValue( "screenshot" );
               if ( screenshot != null ) {
                  elem.setScreenshot( screenshot);
               } 
               else {
                  throw new IllegalArgumentException( "Download" + " attribute screenshot is required" );
               }
               String icon = attributes.getValue( "icon" );
               if ( icon != null ) {
                  elem.setIcon( icon);
               } 
               else {
                  throw new IllegalArgumentException( "Download" + " attribute icon is required" );
               }
               String download = attributes.getValue( "download" );
               if ( download != null ) {
                  elem.setDownload( download);
               } 
               else {
                  throw new IllegalArgumentException( "Download" + " attribute download is required" );
               }
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof App) {
                     ((App)peek).setDownload( elem );
                  };
               }
               stack.push(elem );
               return;
           }
          if ( qName.equals( "contributors"))  {
             if ( stack.empty() ) {
                throw new IllegalArgumentException( "contributors needs to be in a complex type ");
             }
             Object peek =  stack.peek();

              if ( peek instanceof Artifact ) {
                  stack.push( new ListHelper<Author>( ((Artifact)peek).getContributors() ));
                  return;
              }
              throw new IllegalArgumentException( "contributors is not member of " + peek.getClass() );
          }
           if ( qName.equals("ArtifactRef" )) {
               ArtifactRef elem = new ArtifactRef();
               String groupId = attributes.getValue( "groupId" );
               if ( groupId != null ) {
                  elem.setGroupId( groupId);
               } 
               else {
                  throw new IllegalArgumentException( "ArtifactRef" + " attribute groupId is required" );
               }
               String name = attributes.getValue( "name" );
               if ( name != null ) {
                  elem.setName( name);
               } 
               else {
                  throw new IllegalArgumentException( "ArtifactRef" + " attribute name is required" );
               }
               String version = attributes.getValue( "version" );
               if ( version != null ) {
                  elem.setVersion( version);
               } 
               String scope = attributes.getValue( "scope" );
               if ( scope != null ) {
                  elem.setScope( scope);
               } 
               else {
                  throw new IllegalArgumentException( "ArtifactRef" + " attribute scope is required" );
               }
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof ListHelper ) {
                      ((ListHelper<ArtifactRef>)peek).add( elem );
                  }

               }
               stack.push(elem );
               return;
           }
           if ( qName.equals("Artifact" )) {
               Artifact elem = new Artifact();
               String groupId = attributes.getValue( "groupId" );
               if ( groupId != null ) {
                  elem.setGroupId( groupId);
               } 
               else {
                  throw new IllegalArgumentException( "Artifact" + " attribute groupId is required" );
               }
               String name = attributes.getValue( "name" );
               if ( name != null ) {
                  elem.setName( name);
               } 
               else {
                  throw new IllegalArgumentException( "Artifact" + " attribute name is required" );
               }
               String version = attributes.getValue( "version" );
               if ( version != null ) {
                  elem.setVersion( version);
               } 
               else {
                  throw new IllegalArgumentException( "Artifact" + " attribute version is required" );
               }
               String licence = attributes.getValue( "licence" );
               if ( licence != null ) {
                  elem.setLicence( licence);
               } 
               else {
                  throw new IllegalArgumentException( "Artifact" + " attribute licence is required" );
               }
               String support = attributes.getValue( "support" );
               if ( support != null ) {
                  elem.setSupport( support);
               } 
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof Module) {
                     ((Module)peek).setArtifact( elem );
                  };
                  if ( peek instanceof Complete) {
                     ((Complete)peek).setArtifact( elem );
                  };
                  if ( peek instanceof ListHelper ) {
                      ((ListHelper<Artifact>)peek).add( elem );
                  }

               }
               stack.push(elem );
               return;
           }
           if ( qName.equals("App" )) {
               App elem = new App();
               String mainClass = attributes.getValue( "mainClass" );
               if ( mainClass != null ) {
                  elem.setMainClass( mainClass);
               } 
               else {
                  throw new IllegalArgumentException( "App" + " attribute mainClass is required" );
               }
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof Module) {
                     ((Module)peek).setApp( elem );
                  };
               }
               stack.push(elem );
               return;
           }
          if ( qName.equals( "refs"))  {
             if ( stack.empty() ) {
                throw new IllegalArgumentException( "refs needs to be in a complex type ");
             }
             Object peek =  stack.peek();

              if ( peek instanceof Artifact ) {
                  stack.push( new ListHelper<ArtifactRef>( ((Artifact)peek).getRefs() ));
                  return;
              }
              throw new IllegalArgumentException( "refs is not member of " + peek.getClass() );
          }
           if ( qName.equals("Address" )) {
               Address elem = new Address();
               String page = attributes.getValue( "page" );
               if ( page != null ) {
                  elem.setPage( page);
               } 
               else {
                  throw new IllegalArgumentException( "Address" + " attribute page is required" );
               }
               String shrt = attributes.getValue( "shrt" );
               if ( shrt != null ) {
                  elem.setShrt( shrt);
               } 
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof Licence) {
                     ((Licence)peek).setAddress( elem );
                  };
                  if ( peek instanceof Artifact) {
                     ((Artifact)peek).setAddress( elem );
                  };
               }
               stack.push(elem );
               return;
           }
           if ( qName.equals("Language" )) {
               Language elem = new Language();
               String name = attributes.getValue( "name" );
               if ( name != null ) {
                  elem.setName( name);
               } 
               else {
                  throw new IllegalArgumentException( "Language" + " attribute name is required" );
               }
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof ListHelper ) {
                      ((ListHelper<Language>)peek).add( elem );
                  }

               }
               stack.push(elem );
               return;
           }
          if ( qName.equals( "modules"))  {
             if ( stack.empty() ) {
                throw new IllegalArgumentException( "modules needs to be in a complex type ");
             }
             Object peek =  stack.peek();

              if ( peek instanceof Project ) {
                  stack.push( new ListHelper<ModuleRef>( ((Project)peek).getModules() ));
                  return;
              }
              throw new IllegalArgumentException( "modules is not member of " + peek.getClass() );
          }
           if ( qName.equals("Complete" )) {
               Complete elem = new Complete();
               if ( !stack.empty() ) {
                  Object peek =  stack.peek();

                  if ( peek instanceof ArtifactDescription) {
                     ((ArtifactDescription)peek).setKind( elem );
                  };
               }
               stack.push(elem );
               return;
           }
             throw new IllegalArgumentException("unknown tag " + qName );

       }

       @Override
       public void endElement( String uri, String localName, String qName) throws SAXException {
           if ( qName.equals( "licences" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "Author" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "Licence" ) ) {
               goal = stack.pop();
               if ( ((Licence)goal).getAddress() == null ){
                  throw new IllegalArgumentException("Licence required member Address not set ");
               }
           }
           if ( qName.equals( "negatives" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "externals" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "Module" ) ) {
               goal = stack.pop();
               if ( ((Module)goal).getArtifact() == null ){
                  throw new IllegalArgumentException("Module required member Artifact not set ");
               }
           }
           if ( qName.equals( "Java" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "positives" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "languages" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "authors" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "ModuleRef" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "references" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "LicenceRef" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "ArtifactDescription" ) ) {
               goal = stack.pop();
               if ( ((ArtifactDescription)goal).getKind() == null ){
                  throw new IllegalArgumentException("ArtifactDescription required member Kind not set ");
               }
           }
           if ( qName.equals( "dependencies" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "References" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "Project" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "depends" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "Download" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "contributors" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "ArtifactRef" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "Artifact" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "App" ) ) {
               goal = stack.pop();
               if ( ((App)goal).getDownload() == null ){
                  throw new IllegalArgumentException("App required member Download not set ");
               }
           }
           if ( qName.equals( "refs" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "Address" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "Language" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "modules" ) ) {
               goal = stack.pop();
           }
           if ( qName.equals( "Complete" ) ) {
               goal = stack.pop();
               if ( ((Complete)goal).getArtifact() == null ){
                  throw new IllegalArgumentException("Complete required member Artifact not set ");
               }
           }
       }

       public Object getGoal() {
           return goal;
       }
}
