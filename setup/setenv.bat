echo off

echo "----before ------ "
echo %Path%
echo %JAVA_HOME%
echo ""

rem jdk
set Path=%Path%;C:\java\jdkee6\jdk\bin
set JAVA_HOME=C:\java\jdkee6\jdk

rem maven
set Path=%Path%;C:\java\apache-maven-3.0.1\bin

rem svn
set Path=%Path%;c:\java\svn\bin


echo "----after ------ "
echo %Path%
echo %JAVA_HOME%
echo ""

 
