package org.openCage.jmidgard.main;

import org.openCage.io.IOUtils;
import org.openCage.io.Resource;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.jmidgard.core.Compiler;
import org.openCage.lang.functions.FE1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 6/26/11
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Create {

    public static void main(String[] args) {

        System.out.println( new File(".").getAbsolutePath());

        System.out.println(FSPathBuilder.getPath(new File(".")).getFileName());

        FSPath current = FSPathBuilder.getPath(new File("."));

        if (args.length < 1  )  {
            throw new IllegalArgumentException("need a project name");
        }

        String name = args[0];
        current = current.add( name );

        IOUtils.ensurePath( current.add( "modules", name, "src", "main", "java", "org", "openCage", name, "foo.java" ));
        IOUtils.ensurePath( current.add( "modules", name, "src", "test", "java", "org", "openCage", name, "fooTest.java" ));
        IOUtils.ensurePath( current.add( "modules", "parent", "pom.xml" ));
        IOUtils.ensurePath( current.add( "modules", "jmdg", "src", "main", "java", "modules" , name + ".java" ));

        writeParentPom( current.add( "modules", "parent", "pom.xml" ), name );
        writeModulePom( current.add( "modules", name, "pom.xml" ), name );
        writeJmdgPom( current.add( "modules", "jmdg", "pom.xml" ), name );

        writeHelloW( current.add( "modules", name, "src", "main", "java", "org", "openCage", name, "HelloWorld.java" ), name );
        writeConfi( current.add( "modules", "jmdg", "src", "main", "java", "modules", name + ".java" ), name);
    }

    private static void writeHelloW(FSPath to, String name) {
        String cl = "package org.openCage." + name +";\n" +
                "\n" +
                "/**\n" +
                " * Created by jmidgard.\n" +
                " */\n" +
                "public class HelloWorld {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello world, i am so generated!\");\n" +
                "    }\n" +
                "}";

        write( to, cl );
    }

    private static void writeConfi(FSPath to, String name) {
        String cl = "package modules;\n" +
                "\n" +
                "/**\n" +
                " * Created by jmidgard.\n" +
                " */\n" +
                "public class " + name + " /*implements BuildConf */{ \n" +
                "   public String getMain() {\n" +
                "       return \"org.openCage." + name + ".HelloWorld\";" +
                "   }\n" +
                "\n" +
                "}";

        write( to, cl );
    }

    private static void writeJmdgPom(FSPath to, String name) {
        final String pom = "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n" +
                "\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <parent>\n" +
                "        <groupId>openCage</groupId>\n" +
                "        <artifactId>"+ name + "-parent</artifactId>\n" +
                "        <version>0.1.0-SNAPSHOT</version>\n" +
                "        <relativePath>../parent</relativePath>\n" +
                "    </parent>\n" +
                "\n" +
                "    <groupId>openCage</groupId>\n" +
                "    <artifactId>jmdg</artifactId>\n" +
                "    <packaging>jar</packaging>\n" +
                "\n" +
                "\t<!--\n" +
                "    <properties>\n" +
                "        <mainclass>org.openCage.fausterize.Create</mainclass>\n" +
                "    </properties>\n" +
                "-->\n" +
                "\t\n" +
                "    <dependencies>\n" +
                "        <dependency>\n" +
                "            <groupId>javax</groupId>\n" +
                "            <artifactId>javaee-web-api</artifactId>\n" +
                "            <scope>provided</scope>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>junit</groupId>\n" +
                "            <artifactId>junit</artifactId>\n" +
                "            <scope>test</scope>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>openCage</groupId>\n" +
                "            <artifactId>openCage-io</artifactId>\n" +
                "            <scope>compile</scope>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n" +
                "\n" +
                "    <build>\n" +
                "        <plugins>\n" +
                "\n" +
                "            <plugin>\n" +
                "                <artifactId>maven-ejb-plugin</artifactId>\n" +
                "                <version>2.3</version>\n" +
                "                <configuration>\n" +
                "                    <archive>\n" +
                "                        <manifest>\n" +
                "                            <addClasspath>true</addClasspath>\n" +
                "                        </manifest>\n" +
                "                    </archive>\n" +
                "                    <ejbVersion>3.0</ejbVersion>\n" +
                "                    <generateClient>true</generateClient>\n" +
                "                </configuration>\n" +
                "            </plugin>\n" +
                "\n" +
                "            <!-- override the pom used for build jar -->\n" +
                "            <!-- maven uses the project pom, which is often useless without the parent pom -->\n" +
                "            <!-- this step here uses the effective pom, which is large but independent -->\n" +
                "            <!-- everything is expanded and filled in -->\n" +
                "            <plugin>\n" +
                "                <groupId>org.apache.maven.plugins</groupId>\n" +
                "                <artifactId>maven-help-plugin</artifactId>\n" +
                "                <executions>\n" +
                "                    <execution>\n" +
                "                        <phase>generate-resources</phase>\n" +
                "                        <goals>\n" +
                "                            <goal>effective-pom</goal>\n" +
                "                        </goals>\n" +
                "                        <configuration>\n" +
                "                            <output>\n" +
                "                                ${project.build.outputDirectory}/META-INF/maven/${project.groupId}/${project.artifactId}/pom.xml\n" +
                "                            </output>\n" +
                "                        </configuration>\n" +
                "                    </execution>\n" +
                "                </executions>\n" +
                "            </plugin>\n" +
                "\n" +
                "            <plugin>\n" +
                "                <groupId>org.apache.maven.plugins</groupId>\n" +
                "                <artifactId>maven-jar-plugin</artifactId>\n" +
                "                <configuration>\n" +
                "                    <archive>\n" +
                "                        <manifest>\n" +
                "                            <addClasspath>true</addClasspath>\n" +
                "                            <mainClass>${mainclass}</mainClass>\n" +
                "                        </manifest>\n" +
                "                        <!-- no pom in jar, this is written by the help plugin-->\n" +
                "                        <addMavenDescriptor>false</addMavenDescriptor>\n" +
                "                    </archive>\n" +
                "                </configuration>\n" +
                "            </plugin>\n" +
                "\n" +
                "\n" +
                "            <!--<plugin>-->\n" +
                "                <!--<groupId>org.codehaus.mojo</groupId>-->\n" +
                "                <!--<artifactId>appassembler-maven-plugin</artifactId>-->\n" +
                "                <!--<configuration>-->\n" +
                "                    <!--<platforms>-->\n" +
                "                        <!--<platform>windows</platform>-->\n" +
                "                        <!--<platform>unix</platform>-->\n" +
                "                    <!--</platforms>-->\n" +
                "                    <!--<programs>-->\n" +
                "                        <!--<program>-->\n" +
                "                            <!--<mainClass>${mainclass}</mainClass>-->\n" +
                "                            <!--<name>huffman</name>-->\n" +
                "                        <!--</program>-->\n" +
                "                    <!--</programs>-->\n" +
                "                <!--</configuration>-->\n" +
                "                <!--&lt;!&ndash;make it part of the standard build lifecycle&ndash;&gt;-->\n" +
                "                <!--&lt;!&ndash;hook it into the assemble phase (called with 'mvn package')&ndash;&gt;-->\n" +
                "                <!--<executions>-->\n" +
                "                    <!--<execution>-->\n" +
                "                        <!--<id>assemble</id>-->\n" +
                "                        <!--<goals>-->\n" +
                "                            <!--<goal>assemble</goal>-->\n" +
                "                        <!--</goals>-->\n" +
                "                    <!--</execution>-->\n" +
                "                <!--</executions>-->\n" +
                "            <!--</plugin>-->\n" +
                "\n" +
                "            <!--<plugin>-->\n" +
                "            <!--<groupId>org.apache.maven.plugins</groupId>-->\n" +
                "            <!--<artifactId>maven-assembly-plugin</artifactId>-->\n" +
                "            <!--<configuration>-->\n" +
                "            <!--<descriptors>-->\n" +
                "            <!--<descriptor>src/main/assembly/clt.xml</descriptor>-->\n" +
                "            <!--</descriptors>-->\n" +
                "            <!--</configuration>-->\n" +
                "            <!--<executions>-->\n" +
                "            <!--<execution>-->\n" +
                "            <!--<id>make-assembly</id>-->\n" +
                "            <!--&lt;!&ndash; this is used for inheritance merges &ndash;&gt;-->\n" +
                "            <!--<phase>package</phase>-->\n" +
                "            <!--&lt;!&ndash; bind to the packaging phase &ndash;&gt;-->\n" +
                "            <!--<goals>-->\n" +
                "            <!--<goal>single</goal>-->\n" +
                "            <!--</goals>-->\n" +
                "            <!--</execution>-->\n" +
                "            <!--</executions>-->\n" +
                "            <!--</plugin>-->\n" +
                "\n" +
                "            <!--<plugin>-->\n" +
                "            <!--<groupId>com.akathist.maven.plugins.launch4j</groupId>-->\n" +
                "            <!--<artifactId>launch4j-maven-plugin</artifactId>-->\n" +
                "            <!--<version>1.3</version>-->\n" +
                "            <!--<executions>-->\n" +
                "            <!--<execution>-->\n" +
                "            <!--<id>4j-gui</id>-->\n" +
                "            <!--<phase>package</phase>-->\n" +
                "            <!--<goals>-->\n" +
                "            <!--<goal>launch4j</goal>-->\n" +
                "            <!--</goals>-->\n" +
                "            <!--<configuration>-->\n" +
                "            <!--<headerType>console</headerType>-->\n" +
                "            <!--&lt;!&ndash;<outfile>target/two.exe</outfile>   &lt;!&ndash; var &ndash;&gt;&ndash;&gt;-->\n" +
                "            <!--&lt;!&ndash;<jar>target/two-0.2.jar</jar>&ndash;&gt;-->\n" +
                "            <!--<errTitle>fff</errTitle>-->\n" +
                "            <!--<classPath>-->\n" +
                "            <!--<mainClass>org.openCage.gpad.MvnMain</mainClass>-->\n" +
                "            <!--<addDependencies>true</addDependencies>-->\n" +
                "            <!--&lt;!&ndash;<preCp>anything</preCp>&ndash;&gt;-->\n" +
                "            <!--</classPath>-->\n" +
                "            <!--<jre>-->\n" +
                "            <!--<minVersion>1.6.0</minVersion>-->\n" +
                "            <!--</jre>-->\n" +
                "            <!--<versionInfo>-->\n" +
                "            <!--<fileVersion>1.2.3.4</fileVersion>-->\n" +
                "            <!--<txtFileVersion>txt file version?</txtFileVersion>-->\n" +
                "            <!--<fileDescription>a description</fileDescription>-->\n" +
                "            <!--<copyright>my copyright</copyright>-->\n" +
                "            <!--<productVersion>4.3.2.1</productVersion>-->\n" +
                "            <!--<txtProductVersion>txt product version</txtProductVersion>-->\n" +
                "            <!--<productName>M-T</productName>-->\n" +
                "            <!--<internalName>mt</internalName>-->\n" +
                "            <!--<originalFilename>original.exe</originalFilename>-->\n" +
                "            <!--</versionInfo>-->\n" +
                "            <!--</configuration>-->\n" +
                "            <!--</execution>-->\n" +
                "            <!--</executions>-->\n" +
                "            <!--</plugin>-->\n" +
                "        </plugins>\n" +
                "    </build>\n" +
                "\n" +
                "</project>";
        write( to, pom );

    }

    public static void writeParentPom( final FSPath to, String name ) {

        final String pom = "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n" +
                "\n" +
                "    <!-- ===== following elephants maven standards ======== -->\n" +
                "\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <!-- the parent pom -->\n" +
                "\n" +
                "    <groupId>openCage</groupId>\n" +
                "    <!-- project name-->\n" +
                "    <artifactId>" + name + "-parent</artifactId>\n" +
                "    <packaging>pom</packaging>\n" +
                "    <!-- version-->\n" +
                "    <version>0.1.0-SNAPSHOT</version>\n" +
                "    <!-- same as artifactId -->\n" +
                "    <name>" + name + "-parent</name>\n" +
                "\n" +
                "    <modules>\n" +
                "        <!-- list of modules -->\n" +
                "        <module>../" + name + "</module>\n" +
                "        <module>../jmdg</module>\n" +
                "    </modules>\n" +
                "\n" +
                "    <properties>\n" +
                "        <java.target>1.6</java.target>\n" +
                "\n" +
                "        <!-- same encoding on all machines-->\n" +
                "        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n" +
                "    </properties>\n" +
                "\n" +
                "    <licenses>\n" +
                "        <license>\n" +
                "            <name>BSD</name>\n" +
                "            <comments>A business-friendly OSS license</comments>\n" +
                "        </license>\n" +
                "    </licenses>\n" +
                "\n" +
                "    <developers>\n" +
                "        <developer>\n" +
                "            <id>Stephan</id>\n" +
                "            <name>Stephan Pfab</name>\n" +
                "            <email>openCage@gmail.com</email>\n" +
                "            <url>http://stroy.wikidot.com</url>\n" +
                "            <!--<properties>-->\n" +
                "            <!--<picUrl>http://tinyurl.com/prv4t</picUrl>-->\n" +
                "            <!--</properties>-->\n" +
                "        </developer>\n" +
                "    </developers>\n" +
                "\n" +
                "    <scm>\n" +
                "        <!-- needs to be the directory of this pom -->\n" +
                "        <developerConnection>scm:svn:https://stroy.googlecode.com/svn/trunk/projects/" + name + "/modules/parent\n" +
                "        </developerConnection>\n" +
                "    </scm>\n" +
                "\n" +
                "    <distributionManagement>\n" +
                "        <repository>\n" +
                "            <id>my-repo</id>\n" +
                "            <name>my-repo</name>\n" +
                "            <url>file://${basedir}/../../../../../../../repo</url>\n" +
                "        </repository>\n" +
                "    </distributionManagement>\n" +
                "\n" +
                "\n" +
                "    <repositories>\n" +
                "        <repository>\n" +
                "            <id>openCage-repo</id>\n" +
                "            <name>openCage-repo</name>\n" +
                "            <url>file://${basedir}/../../../../repo</url>\n" +
                "            <releases>\n" +
                "                <checksumPolicy>ignore</checksumPolicy>\n" +
                "            </releases>\n" +
                "            <snapshots>\n" +
                "                <checksumPolicy>ignore</checksumPolicy>\n" +
                "            </snapshots>\n" +
                "        </repository>\n" +
                "\n" +
                "        <repository>\n" +
                "            <id>java.net2</id>\n" +
                "            <name>Repository hosting the jee6 artifacts</name>\n" +
                "            <url>http://download.java.net/maven/2</url>\n" +
                "        </repository>\n" +
                "    </repositories>\n" +
                "\n" +
                "    <build>\n" +
                "        <!-- pin the versions of plugins -->\n" +
                "        <pluginManagement>\n" +
                "            <plugins>\n" +
                "                <plugin>\n" +
                "                    <groupId>org.apache.maven.plugins</groupId>\n" +
                "                    <artifactId>maven-help-plugin</artifactId>\n" +
                "                    <version>2.1.1</version>\n" +
                "                </plugin>\n" +
                "                <plugin>\n" +
                "                    <groupId>org.apache.maven.plugins</groupId>\n" +
                "                    <artifactId>maven-jar-plugin</artifactId>\n" +
                "                    <version>2.3.1</version>\n" +
                "                </plugin>\n" +
                "                <plugin>\n" +
                "                    <groupId>org.apache.maven.plugins</groupId>\n" +
                "                    <artifactId>maven-release-plugin</artifactId>\n" +
                "                    <version>2.0</version>\n" +
                "                </plugin>\n" +
                "                <plugin>\n" +
                "                    <groupId>org.codehaus.mojo</groupId>\n" +
                "                    <artifactId>appassembler-maven-plugin</artifactId>\n" +
                "                    <version>1.1.1</version>\n" +
                "                </plugin>\n" +
                "\n" +
                "                <plugin>\n" +
                "                    <groupId>org.apache.maven.plugins</groupId>\n" +
                "                    <artifactId>maven-assembly-plugin</artifactId>\n" +
                "                    <version>2.2.1</version>\n" +
                "                </plugin>\n" +
                "\n" +
                "                <plugin>\n" +
                "                    <artifactId>maven-ear-plugin</artifactId>\n" +
                "                    <version>2.5</version>\n" +
                "                </plugin>\n" +
                "\n" +
                "            </plugins>\n" +
                "        </pluginManagement>\n" +
                "\n" +
                "        <plugins>\n" +
                "            <plugin>\n" +
                "                <groupId>org.apache.maven.plugins</groupId>\n" +
                "                <artifactId>maven-compiler-plugin</artifactId>\n" +
                "                <version>2.2</version>\n" +
                "                <configuration>\n" +
                "                    <source>1.6</source>\n" +
                "                    <target>${java.target}</target>\n" +
                "                </configuration>\n" +
                "            </plugin>\n" +
                "\n" +
                "            <plugin>\n" +
                "                <groupId>org.apache.maven.plugins</groupId>\n" +
                "                <artifactId>maven-release-plugin</artifactId>\n" +
                "                <configuration>\n" +
                "                    <!-- project name-->\n" +
                "                    <tagBase>https://stroy.googlecode.com/svn/tags/jmidgard</tagBase>\n" +
                "                </configuration>\n" +
                "            </plugin>\n" +
                "\n" +
                "        </plugins>\n" +
                "    </build>\n" +
                "\n" +
                "    <!-- version pin the possible dependencies -->\n" +
                "    <dependencyManagement>\n" +
                "        <dependencies>\n" +
                "\n" +
                "            <dependency>\n" +
                "               <groupId>javax</groupId>\n" +
                "               <artifactId>javaee-web-api</artifactId>\n" +
                "               <version>6.0</version>\n" +
                "             </dependency>\n" +
                "\n" +
                "            <dependency>\n" +
                "                <groupId>openCage</groupId>\n" +
                "                <artifactId>openCage-huffman</artifactId>\n" +
                "                <version>[0.2.2,)</version>\n" +
                "            </dependency>\n" +
                "            <dependency>\n" +
                "                <groupId>openCage</groupId>\n" +
                "                <artifactId>openCage-lang</artifactId>\n" +
                "                <version>[0.9.10,)</version>\n" +
                "            </dependency>\n" +
                "            <dependency>\n" +
                "                <groupId>openCage</groupId>\n" +
                "                <artifactId>openCage-io</artifactId>\n" +
                "                <version>[0.5.5,)</version>\n" +
                "            </dependency>\n" +
                "\n" +
                "            <dependency>\n" +
                "                <groupId>openCage</groupId>\n" +
                "                <artifactId>osashosa</artifactId>\n" +
                "                <version>[0.3.1,)</version>\n" +
                "            </dependency>\n" +
                "\n" +
                "            <dependency>\n" +
                "                <groupId>com.intellij</groupId>\n" +
                "                <artifactId>annotations</artifactId>\n" +
                "                <version>7.0.3</version>\n" +
                "            </dependency>\n" +
                "            <dependency>\n" +
                "                <groupId>net.jcip</groupId>\n" +
                "                <artifactId>jcip-annotations</artifactId>\n" +
                "                <version>1.0</version>\n" +
                "            </dependency>\n" +
                "            <dependency>\n" +
                "                <groupId>junit</groupId>\n" +
                "                <artifactId>junit</artifactId>\n" +
                "                <version>[4.8.1,)</version>\n" +
                "            </dependency>\n" +
                "            <dependency>\n" +
                "                <groupId>org.apache.maven</groupId>\n" +
                "                <artifactId>maven-plugin-api</artifactId>\n" +
                "                <version>[2.0,)</version>\n" +
                "            </dependency>\n" +
                "            <dependency>\n" +
                "                <groupId>com.thoughtworks.xstream</groupId>\n" +
                "                <artifactId>xstream</artifactId>\n" +
                "                <version>[1.3.1,2.0)</version>\n" +
                "            </dependency>\n" +
                "            <dependency>\n" +
                "                <groupId>args4j</groupId>\n" +
                "                <artifactId>args4j</artifactId>\n" +
                "                <version>[2.0.12,)</version>\n" +
                "            </dependency>\n" +
                "            <dependency>\n" +
                "                <groupId>eu.medsea.mimeutil</groupId>\n" +
                "                <artifactId>mime-util</artifactId>\n" +
                "                <version>[2.1.3,)</version>\n" +
                "            </dependency>\n" +
                "\n" +
                "\n" +
                "        </dependencies>\n" +
                "    </dependencyManagement>\n" +
                "\n" +
                "</project>";


        write( to, pom );
    }

    public static void write( final FSPath to, final String content ) {
        Resource.tryWith( new FE1<Void, Resource>() {
            @Override
            public Void call(Resource resource) throws Exception {
                BufferedWriter out = resource.add( new BufferedWriter(new FileWriter(to.toFile() )));
                out.write(content );
                return null;
            }
        });

    }

    public static void writeModulePom( FSPath to, String name ) {
        String pom = "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n" +
                "\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <parent>\n" +
                "        <groupId>openCage</groupId>\n" +
                "        <artifactId>"+ name + "-parent</artifactId>\n" +
                "        <version>0.1.0-SNAPSHOT</version>\n" +
                "        <relativePath>../parent</relativePath>\n" +
                "    </parent>\n" +
                "\n" +
                "    <groupId>openCage</groupId>\n" +
                "    <artifactId>"+ name + "</artifactId>\n" +
                "    <packaging>jar</packaging>\n" +
                "\n" +
                "\t<!--\n" +
                "    <properties>\n" +
                "        <mainclass>org.openCage.fausterize.Create</mainclass>\n" +
                "    </properties>\n" +
                "-->\n" +
                "\t\n" +
                "    <dependencies>\n" +
                "        <dependency>\n" +
                "            <groupId>javax</groupId>\n" +
                "            <artifactId>javaee-web-api</artifactId>\n" +
                "            <scope>provided</scope>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>junit</groupId>\n" +
                "            <artifactId>junit</artifactId>\n" +
                "            <scope>test</scope>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>openCage</groupId>\n" +
                "            <artifactId>openCage-io</artifactId>\n" +
                "            <scope>compile</scope>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n" +
                "\n" +
                "    <build>\n" +
                "        <plugins>\n" +
                "\n" +
                "            <plugin>\n" +
                "                <artifactId>maven-ejb-plugin</artifactId>\n" +
                "                <version>2.3</version>\n" +
                "                <configuration>\n" +
                "                    <archive>\n" +
                "                        <manifest>\n" +
                "                            <addClasspath>true</addClasspath>\n" +
                "                        </manifest>\n" +
                "                    </archive>\n" +
                "                    <ejbVersion>3.0</ejbVersion>\n" +
                "                    <generateClient>true</generateClient>\n" +
                "                </configuration>\n" +
                "            </plugin>\n" +
                "\n" +
                "            <!-- override the pom used for build jar -->\n" +
                "            <!-- maven uses the project pom, which is often useless without the parent pom -->\n" +
                "            <!-- this step here uses the effective pom, which is large but independent -->\n" +
                "            <!-- everything is expanded and filled in -->\n" +
                "            <plugin>\n" +
                "                <groupId>org.apache.maven.plugins</groupId>\n" +
                "                <artifactId>maven-help-plugin</artifactId>\n" +
                "                <executions>\n" +
                "                    <execution>\n" +
                "                        <phase>generate-resources</phase>\n" +
                "                        <goals>\n" +
                "                            <goal>effective-pom</goal>\n" +
                "                        </goals>\n" +
                "                        <configuration>\n" +
                "                            <output>\n" +
                "                                ${project.build.outputDirectory}/META-INF/maven/${project.groupId}/${project.artifactId}/pom.xml\n" +
                "                            </output>\n" +
                "                        </configuration>\n" +
                "                    </execution>\n" +
                "                </executions>\n" +
                "            </plugin>\n" +
                "\n" +
                "            <plugin>\n" +
                "                <groupId>org.apache.maven.plugins</groupId>\n" +
                "                <artifactId>maven-jar-plugin</artifactId>\n" +
                "                <configuration>\n" +
                "                    <archive>\n" +
                "                        <manifest>\n" +
                "                            <addClasspath>true</addClasspath>\n" +
                "                            <mainClass>${mainclass}</mainClass>\n" +
                "                        </manifest>\n" +
                "                        <!-- no pom in jar, this is written by the help plugin-->\n" +
                "                        <addMavenDescriptor>false</addMavenDescriptor>\n" +
                "                    </archive>\n" +
                "                </configuration>\n" +
                "            </plugin>\n" +
                "\n" +
                "\n" +
                "            <!--<plugin>-->\n" +
                "                <!--<groupId>org.codehaus.mojo</groupId>-->\n" +
                "                <!--<artifactId>appassembler-maven-plugin</artifactId>-->\n" +
                "                <!--<configuration>-->\n" +
                "                    <!--<platforms>-->\n" +
                "                        <!--<platform>windows</platform>-->\n" +
                "                        <!--<platform>unix</platform>-->\n" +
                "                    <!--</platforms>-->\n" +
                "                    <!--<programs>-->\n" +
                "                        <!--<program>-->\n" +
                "                            <!--<mainClass>${mainclass}</mainClass>-->\n" +
                "                            <!--<name>huffman</name>-->\n" +
                "                        <!--</program>-->\n" +
                "                    <!--</programs>-->\n" +
                "                <!--</configuration>-->\n" +
                "                <!--&lt;!&ndash;make it part of the standard build lifecycle&ndash;&gt;-->\n" +
                "                <!--&lt;!&ndash;hook it into the assemble phase (called with 'mvn package')&ndash;&gt;-->\n" +
                "                <!--<executions>-->\n" +
                "                    <!--<execution>-->\n" +
                "                        <!--<id>assemble</id>-->\n" +
                "                        <!--<goals>-->\n" +
                "                            <!--<goal>assemble</goal>-->\n" +
                "                        <!--</goals>-->\n" +
                "                    <!--</execution>-->\n" +
                "                <!--</executions>-->\n" +
                "            <!--</plugin>-->\n" +
                "\n" +
                "            <!--<plugin>-->\n" +
                "            <!--<groupId>org.apache.maven.plugins</groupId>-->\n" +
                "            <!--<artifactId>maven-assembly-plugin</artifactId>-->\n" +
                "            <!--<configuration>-->\n" +
                "            <!--<descriptors>-->\n" +
                "            <!--<descriptor>src/main/assembly/clt.xml</descriptor>-->\n" +
                "            <!--</descriptors>-->\n" +
                "            <!--</configuration>-->\n" +
                "            <!--<executions>-->\n" +
                "            <!--<execution>-->\n" +
                "            <!--<id>make-assembly</id>-->\n" +
                "            <!--&lt;!&ndash; this is used for inheritance merges &ndash;&gt;-->\n" +
                "            <!--<phase>package</phase>-->\n" +
                "            <!--&lt;!&ndash; bind to the packaging phase &ndash;&gt;-->\n" +
                "            <!--<goals>-->\n" +
                "            <!--<goal>single</goal>-->\n" +
                "            <!--</goals>-->\n" +
                "            <!--</execution>-->\n" +
                "            <!--</executions>-->\n" +
                "            <!--</plugin>-->\n" +
                "\n" +
                "            <!--<plugin>-->\n" +
                "            <!--<groupId>com.akathist.maven.plugins.launch4j</groupId>-->\n" +
                "            <!--<artifactId>launch4j-maven-plugin</artifactId>-->\n" +
                "            <!--<version>1.3</version>-->\n" +
                "            <!--<executions>-->\n" +
                "            <!--<execution>-->\n" +
                "            <!--<id>4j-gui</id>-->\n" +
                "            <!--<phase>package</phase>-->\n" +
                "            <!--<goals>-->\n" +
                "            <!--<goal>launch4j</goal>-->\n" +
                "            <!--</goals>-->\n" +
                "            <!--<configuration>-->\n" +
                "            <!--<headerType>console</headerType>-->\n" +
                "            <!--&lt;!&ndash;<outfile>target/two.exe</outfile>   &lt;!&ndash; var &ndash;&gt;&ndash;&gt;-->\n" +
                "            <!--&lt;!&ndash;<jar>target/two-0.2.jar</jar>&ndash;&gt;-->\n" +
                "            <!--<errTitle>fff</errTitle>-->\n" +
                "            <!--<classPath>-->\n" +
                "            <!--<mainClass>org.openCage.gpad.MvnMain</mainClass>-->\n" +
                "            <!--<addDependencies>true</addDependencies>-->\n" +
                "            <!--&lt;!&ndash;<preCp>anything</preCp>&ndash;&gt;-->\n" +
                "            <!--</classPath>-->\n" +
                "            <!--<jre>-->\n" +
                "            <!--<minVersion>1.6.0</minVersion>-->\n" +
                "            <!--</jre>-->\n" +
                "            <!--<versionInfo>-->\n" +
                "            <!--<fileVersion>1.2.3.4</fileVersion>-->\n" +
                "            <!--<txtFileVersion>txt file version?</txtFileVersion>-->\n" +
                "            <!--<fileDescription>a description</fileDescription>-->\n" +
                "            <!--<copyright>my copyright</copyright>-->\n" +
                "            <!--<productVersion>4.3.2.1</productVersion>-->\n" +
                "            <!--<txtProductVersion>txt product version</txtProductVersion>-->\n" +
                "            <!--<productName>M-T</productName>-->\n" +
                "            <!--<internalName>mt</internalName>-->\n" +
                "            <!--<originalFilename>original.exe</originalFilename>-->\n" +
                "            <!--</versionInfo>-->\n" +
                "            <!--</configuration>-->\n" +
                "            <!--</execution>-->\n" +
                "            <!--</executions>-->\n" +
                "            <!--</plugin>-->\n" +
                "        </plugins>\n" +
                "    </build>\n" +
                "\n" +
                "</project>";
        write( to, pom );
    }
}
