package org.openCage.artig;

import org.jetbrains.annotations.NotNull;
import org.openCage.fspath.protocol.FSPath;
import org.openCage.lang.artifact.*;

public class QuineGen {

    private final Project project;

    public QuineGen( @NotNull Project project ) {
        this.project = project;
    }

    public void generate( FSPath projectRoot ) {

    }


    public String gen( ArtifactProvider prov ) {
        Artifact arti = prov.getArtifact();

        if ( !prov.getProject().isModule( prov.getArtifact() )) {
            throw new IllegalStateException("oops");
        }

        String ret = "import org.openCage.lang.artifact.Artifact;\n" +
                "import org.openCage.lang.artifact.ArtifactProvider;\n" +
                "import org.openCage.lang.artifact.Project;\n" +
                "\n" +
                "@Immutable\n" +
                "public class " + quineName( prov.getArtifact()) + "Artifact implements ArtifactProvider {\n" +
                "\n" +
                "    private final Artifact lang;\n" +
                "    private final Project  proj;\n" +
                "\n" +
                "    public " + quineName( prov.getArtifact()) + "Artifact() {\n" +
                "        proj = new Project( stroy );\n" +
                "\n" +
                "        lang = ";

        ret += getModule( arti );


        return ret;
    }

    private String getArti( Artifact arti ) {
        if ( project.isModule( arti )) {
            return getModule( arti );
        }

        return getExternal( arti );
    }

    private String getModule( Artifact mod ) {
        return "proj.module( getClass(), \"" + mod.getGroupId()+ "\" , \"" + mod.gettName() + "\")\n" + getArtibase( mod );
    }

    private String getExternal( Artifact mod ) {
        return "proj.external( \"" + mod.getGroupId()+ "\" , \"" + mod.gettName() + "\")\n" + getArtibase( mod );
    }

    private String getArtibase( Artifact arti ) {

        String ret = "";

        ret += "            .licence( " + gen( arti.getLicence()) + ")\n";
        ret += "            .javaVersion( " + gen( arti.getJavaVersion()) + ")\n";
        ret += "            .version( " + arti.getVersion().getShort() + ")\n";

        for ( Author author : arti.getAuthors() ) {
            ret += "            .author( Author.valueOf( \"" +  author.getName() + "\" ))\n";
        }

        ret += "            .address( \"" + arti.getWebpage().gettPage().toString() + "\", \"" + arti.getWebpage().getShort()  +  "\" )\n";
        ret += "            .descriptionShort( \"" + arti.getDescriptionShort() + "\" )\n";

        for ( Artifact dep : arti.getCompileDependencies() ) {
            ret += ".depends( " +getArti( dep ) + ")";
        }

        return ret ;

    }

    private String gen(JavaVersion javaVersion) {
        return "new JavaVersion(" + JavaVersion.valueOf( javaVersion.toString() ) +")";
    }

    private String gen(Licence licence) {
        return "Licence.valueOf( \"" + licence.getName() + "\" )";
    }

    public String gen( Version ver ) {
        return "Version.valueOf( \"" + ver.getShort() + "\" )";
    }



    private String quineName( Artifact arti ) {
        return arti.gettName().substring(0,1).toUpperCase() + arti.gettName().substring(1);
    }



}
