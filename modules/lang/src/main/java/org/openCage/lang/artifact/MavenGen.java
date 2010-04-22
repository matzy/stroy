package org.openCage.lang.artifact;

import org.jetbrains.annotations.NotNull;

public class MavenGen {

    private final Project project;

    public MavenGen( @NotNull Project project ) {
        this.project = project;
    }

    public String getModulePom( Artifact arti ) {
        String pom = "<project>\n" +
                "   <modelVersion>4.0.0</modelVersion>\n";
        pom += "   " + leaf( "groupId", arti.getGroupId()) + "\n";
        pom += "   " + leaf( "artifactId", arti.gettName()) + "\n";
        pom += "   " + leaf( "version", arti.getVersion().getOriginal()) + "\n\n";

        pom += "    <repositories>\n" +
                "        <repository>\n" +
                "            <id>openCage-repo</id>\n" +
                "            <name>openCage-repo</name>\n" +
                "            <url>file://${basedir}/../../repo</url>\n" +
                "        </repository>\n" +
                "    </repositories>\n\n";

        pom += "    <build>\n" +
                "        <plugins>\n" +
                "            <plugin>\n" +
                "                <groupId>org.apache.maven.plugins</groupId>\n" +
                "                <artifactId>maven-compiler-plugin</artifactId>\n" +
                "                <version>2.2</version>\n";

        if ( arti.getJavaVersion().is6() ) {
            pom += "               <configuration>\n" +
                    "                    <source>1.6</source>\n" +
                    "                    <target>1.6</target>\n" +
                    "                </configuration>\n";
        }

        pom += "           </plugin>\n" +
                "\n" +
                "        </plugins>\n" +
                "    </build>\n";



        pom += "   <dependencies>\n";
        for ( Artifact dep : arti.getCompileDependencies() ) {
            pom += dependency( dep, Scope.COMPILE );
        }

        for ( Artifact dep : arti.getTestDependencies() ) {
            pom += dependency( dep, Scope.TEST );
        }


        pom += "   </dependencies>\n";

        pom += "</project>\n";


        return pom;
    }

    private String dependency(Artifact arti, Scope scope  ) {
        String dep = "      <dependency>\n";
        dep += "         " + leaf( "groupId", arti.getGroupId()  ) + "\n";
        dep += "         " + leaf( "artifactId", arti.gettName()  ) + "\n";
        dep += "         " + leaf( "version", arti.getVersion().getOriginal()  ) + "\n";

        switch ( scope ) {
            case TEST: dep += "         " + leaf( "scope", "test"  ) + "\n"; break;
            case COMPILE: dep += "         " + leaf( "scope", "compile"  ) + "\n"; break;
            default:
                throw new IllegalStateException( "(yet) unsupported scope " + scope );
        }

        dep += "      </dependency>\n";
        return dep;
    }


    private String leaf( String tag, String val ) {
        return "<" + tag + ">" + val + "</" + tag + ">";
    }
}
