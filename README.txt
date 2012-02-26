stroy trunk

This is the toplevel directory for development of stroy and other tools.
For stable versions see under branches.

The project consists of several modules. Most are for library development.
    legacy    is stroy a smart dir diff
    goethepad is fausterize a simple crypto tool

IDE is intellij IDEA (community edition) but I tried eclipse and netbeans too.

The idea is that checkin out and starting idea or running ant should be enough but it is not there yet.
Note: The stable branch with only stroy has a working build.xml

compiler/resource pattern = !?.class

Intellij IDEA setup

* Checkout from Version Control / subversion
* repository: http://stroy.googlecode.com/svn/trunk/, select a target dir
* ok on dialog "would you like to create a Intellij Idea ... "
* next on "Create java project from existing sources"
* next on naming
* deselect any overly long paths, click next
* deselect all zip files, next
* next (accept all modules)
* finish
* ok "overwrite ..."

* settings: compiler/resource pattern = !?.class
* project structure: Project / Project SDK / Project language level: 6.0 


maven

	cd modules/parent
	mvn clean install
	   builds all modules
	mvn release:prepare -Dusername=<username> -Dpassword=<password>
         may need: svn list https://stroy.googlecode.com
         to allow checkin from here
	mvn relasee:perform 
	   makes a new release
	   
	   release schedule: build *.*.x releases until happy than make a relase only change to *.*+1 or *1.0
	   
	cd to some module with appassembler configured
	mvn package appassembler:assemble
	   builds a clt in target/appassembler
	   !! both goals need to be on the same line/call, it does not work iterative
	     or add it to assembly phase/goal 

build tool goals

  - write it once
    i.e. ide and build tool should be configured with the same method/text
 
  - standard
    others should be able to read/unterstand/modify it  
		 
		 
maven versus ...

pro maven
   - quasi standard (standard)
   - intellij works with it (write in once)   
   

intellij maven local repo
   a local file repo either needs poms and checksums or 
   configure intellij to ignore checksums (default is fail)
   

structure
    * parent project parallel to modules, opposed to parent dir
      - looks strange
      - works even if not called through parent
      - release plugin creates correct tags
      
    * parent pom has all the dependencies and plugin by version (only managmeent )
    * module pom inherits the versions
    * module to module dependency uses ${project.version} (not in parent)
    
    
plan: grow the poms, project by project, 
      work with commenting
      
Problem: appasmbler does not set x flag on unix shell script      

	
	
	

	

