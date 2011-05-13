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
	mvn release:prepare
	mvn relasee:perform 
	   makes a new release
	   
	cd to some module with appassembler configured
	mvn package appassembler:assemble
	   builds a clt in target/appassembler
	!! both goals need to be on the same line/call, it does not work iterative
	
	? clt for non-snapshot?
	
	

	

